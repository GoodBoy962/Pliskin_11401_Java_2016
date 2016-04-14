package com.pliskin.service;

import com.pliskin.forms.PatientRegistrationForm;
import com.pliskin.model.Credentials;
import com.pliskin.model.Patient;

/**
 * Created by aleksandrpliskin on 13.04.16.
 */
public interface PatientService {
    void saveNewPatient(PatientRegistrationForm form);

    Patient getPatient();

    Patient getPatient(Credentials credentials);
}
