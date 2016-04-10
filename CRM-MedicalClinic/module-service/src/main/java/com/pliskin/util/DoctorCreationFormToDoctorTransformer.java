package com.pliskin.util;

import com.pliskin.forms.DoctorCreationForm;
import com.pliskin.model.Doctor;
import com.pliskin.model.enums.Role;
import com.pliskin.repository.AdminRepository;
import com.pliskin.repository.OfficeRepository;
import com.pliskin.repository.SpecializationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.function.Function;

/**
 * Created by aleksandrpliskin on 10.04.16.
 */
@Component
public class DoctorCreationFormToDoctorTransformer implements Function<DoctorCreationForm, Doctor> {

    @Autowired
    CredentialsCreator credentialsCreator;

    @Autowired
    SpecializationRepository specializationRepository;

    @Autowired
    OfficeRepository officeRepository;

    @Autowired
    AdminRepository adminRepository;

    @Override
    public Doctor apply(DoctorCreationForm form) {
        Doctor doctor = new Doctor();
        doctor.setCredentials(credentialsCreator.createAndSaveCredentialsForUser(
                form.getPassword(),
                form.getLogin(),
                form.getEmail(),
                Role.ROLE_DOCTOR)
        );
        String fio = form.getName() + " " + form.getSurname();
        if (form.getLastname().length() > 0) {
            fio += " " + form.getLastname();
        }
        SimpleDateFormat format = new SimpleDateFormat("yyyy-mm-dd");
        doctor.setFio(fio);
        try {
            doctor.setBirthDay(format.parse(form.getBirthDay()));
            doctor.setEmploymentDate(format.parse(form.getEmploymentDate()));
            doctor.setInceptionDate(format.parse(form.getInceptionDate()));
        } catch (ParseException e) {
            e.printStackTrace();
        }
        doctor.setOffice(adminRepository.findOneByCredentials(SecurityUtils.getCurrentUser()).getOffice());
        doctor.setSpecialization(specializationRepository.findByName(form.getSpecialization()));
        return doctor;
    }
}
