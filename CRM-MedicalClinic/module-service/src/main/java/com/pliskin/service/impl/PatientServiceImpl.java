package com.pliskin.service.impl;

import com.pliskin.forms.PatientRegistrationForm;
import com.pliskin.model.Patient;
import com.pliskin.repository.PatientRepository;
import com.pliskin.service.PatientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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

    @Override
    public void saveNewPatient(PatientRegistrationForm form) {
        Patient patient = patientFunction.apply(form);
        patientRepository.save(patient);
    }
}
