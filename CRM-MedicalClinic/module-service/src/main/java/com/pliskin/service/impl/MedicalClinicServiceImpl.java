package com.pliskin.service.impl;

import com.pliskin.exceptions.NoSuchMedicalClinicException;
import com.pliskin.forms.MedicalClinicRegistrationForm;
import com.pliskin.model.MedicalClinic;
import com.pliskin.repository.AdminRepository;
import com.pliskin.repository.MedicalClinicRepository;
import com.pliskin.service.MedicalClinicService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by aleksandrpliskin on 06.04.16.
 */
@Service
public class MedicalClinicServiceImpl implements MedicalClinicService {

    @Autowired
    MedicalClinicRepository medicalClinicRepository;

    @Autowired
    AdminRepository adminRepository;

    public List<MedicalClinic> getMedClinics() {
        return medicalClinicRepository.findAll();
    }

    @Override
    public MedicalClinic getMedClinic(Long id) {
        MedicalClinic medicalClinic = medicalClinicRepository.findOne(id);
        if (medicalClinic == null) {
            throw new NoSuchMedicalClinicException();
        }
        return medicalClinic;
    }

    @Secured("hasRole('ROLE_SYSTEM_ADMIN')")
    @Transactional
    @Override
    public void createNewMedClinic(MedicalClinicRegistrationForm form) {
        MedicalClinic medicalClinic = new MedicalClinic();
        medicalClinic.setInfo(form.getInfo());
        medicalClinic.setName(form.getName());
        medicalClinicRepository.save(medicalClinic);
    }

    @Override
    public MedicalClinic getMedClinic(String name) {
        MedicalClinic medicalClinic = medicalClinicRepository.findByName(name);
        if (medicalClinic == null) {
            throw new NoSuchMedicalClinicException();
        }
        return medicalClinic;
    }

}
