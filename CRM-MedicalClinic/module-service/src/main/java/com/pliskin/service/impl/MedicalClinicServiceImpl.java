package com.pliskin.service.impl;

import com.pliskin.forms.MedicalClinicRegistrationForm;
import com.pliskin.model.Admin;
import com.pliskin.model.Credentials;
import com.pliskin.model.MedicalClinic;
import com.pliskin.repository.AdminRepository;
import com.pliskin.repository.MedicalClinicRepository;
import com.pliskin.service.MedicalClinicService;
import com.pliskin.util.SecurityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Service;

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
        return medicalClinicRepository.findOne(id);
    }

    @Secured("hasRole('ROLE_SYSTEM_ADMIN')")
    @Override
    public void createNewMedClinic(MedicalClinicRegistrationForm form) {
        MedicalClinic medicalClinic = new MedicalClinic();
        medicalClinic.setInfo(form.getInfo());
        medicalClinic.setName(form.getName());
        medicalClinicRepository.save(medicalClinic);
    }

    @Override
    public MedicalClinic getMedClinic(String name) {
        return medicalClinicRepository.findByName(name);
    }

}
