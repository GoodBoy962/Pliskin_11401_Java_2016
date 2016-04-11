package com.pliskin.service.impl;

import com.pliskin.exceptions.NoSuchMedicalClinicException;
import com.pliskin.forms.OfficeAdminCreationForm;
import com.pliskin.model.Admin;
import com.pliskin.model.MedicalClinic;
import com.pliskin.model.Office;
import com.pliskin.repository.AdminRepository;
import com.pliskin.repository.CredentialsRepository;
import com.pliskin.repository.MedicalClinicRepository;
import com.pliskin.repository.OfficeRepository;
import com.pliskin.service.OfficeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.function.Function;

/**
 * Created by aleksandrpliskin on 06.04.16.
 */
@Service
public class OfficeServiceImpl implements OfficeService {

    @Autowired
    OfficeRepository officeRepository;

    @Autowired
    MedicalClinicRepository medicalClinicRepository;

    @Autowired
    AdminRepository adminRepository;

    @Autowired
    CredentialsRepository credentialsRepository;

    @Autowired
    Function<OfficeAdminCreationForm, Admin> adminFunction;

    @Override
    public List<Office> getMedClinicOffices(MedicalClinic medicalClinic) {
        if (medicalClinic == null) {
            throw new NoSuchMedicalClinicException();
        }
        return officeRepository.findByMedicalClinic(medicalClinic);
    }

    @Secured(value = "hasRole('ROLE_STUDENT_ADMIN')")
    @Override
    public void createOfficeAndAdmin(OfficeAdminCreationForm form, Long medicalClinicId) {
        MedicalClinic medicalClinic = medicalClinicRepository.findOne(medicalClinicId);
        if (medicalClinic == null) {
            throw new NoSuchMedicalClinicException();
        }
        Office office = new Office();
        office.setAddress(form.getAddress());
        office.setMedicalClinic(medicalClinic);
        officeRepository.save(office);
        Admin admin = adminFunction.apply(form);
        admin.setOffice(office);
        adminRepository.save(admin);
    }
}
