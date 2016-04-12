package com.pliskin.util.transformers;

import com.pliskin.forms.PatientRegistrationForm;
import com.pliskin.model.Patient;
import com.pliskin.model.enums.Role;
import com.pliskin.util.CredentialsCreator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.function.Function;

/**
 * Created by aleksandrpliskin on 13.04.16.
 */
@Component
public class PatientRegistrationFormToPatientTransformer implements Function<PatientRegistrationForm, Patient> {

    @Autowired
    CredentialsCreator credentialsCreator;

    @Override
    public Patient apply(PatientRegistrationForm form) {
        Patient patient = new Patient();
        patient.setCredentials(credentialsCreator.createAndSaveCredentialsForUser(
                form.getPassword(),
                form.getLogin(),
                form.getEmail(),
                Role.ROLE_PATIENT
        ));
        String fio = form.getName() + " " + form.getSurname();
        if (form.getLastname().length() > 0) {
            fio += " " + form.getLastname();
        }
        SimpleDateFormat format = new SimpleDateFormat("yyyy-mm-dd");
        patient.setFio(fio);
        try {
            patient.setBirthDay(format.parse(form.getBirthDay()));
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return patient;
    }
}
