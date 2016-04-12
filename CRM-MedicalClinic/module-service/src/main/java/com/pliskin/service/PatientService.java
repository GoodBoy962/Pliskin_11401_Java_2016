package com.pliskin.service;

import com.pliskin.forms.PatientRegistrationForm;

/**
 * Created by aleksandrpliskin on 13.04.16.
 */
public interface PatientService {
    void saveNewPatient(PatientRegistrationForm form);
}
