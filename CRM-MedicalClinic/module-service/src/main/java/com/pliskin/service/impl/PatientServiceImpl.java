package com.pliskin.service.impl;

import com.pliskin.forms.PatientRegistrationForm;
import com.pliskin.model.Credentials;
import com.pliskin.model.Patient;
import com.pliskin.repository.PatientRepository;
import com.pliskin.service.PatientService;
import com.pliskin.util.SecurityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.function.Function;

/**
 * Created by aleksandrpliskin on 13.04.16.
 */
@Service
public class PatientServiceImpl implements PatientService {

    @Autowired
    Function<PatientRegistrationForm, Patient> patientFunction;

    @Autowired
    PatientRepository patientRepository;

    @Transactional
    @Override
    public void saveNewPatient(PatientRegistrationForm form) {
        Patient patient = patientFunction.apply(form);
        patientRepository.save(patient);
    }

    @Override
    public Patient getPatient() {
        return patientRepository.findByCredentials(SecurityUtils.getCurrentUser());
    }

    @Override
    public Patient getPatient(Credentials credentials) {
        return patientRepository.findByCredentials(credentials);
    }
}
