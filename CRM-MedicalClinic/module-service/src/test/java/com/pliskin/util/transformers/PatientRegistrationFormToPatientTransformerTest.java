package com.pliskin.util.transformers;

import com.pliskin.forms.PatientRegistrationForm;
import com.pliskin.model.Credentials;
import com.pliskin.model.Patient;
import com.pliskin.model.enums.Role;
import com.pliskin.util.CredentialsCreator;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import static org.mockito.Matchers.any;
import static org.mockito.Matchers.anyString;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

/**
 * Created by aleksandrpliskin on 24.05.16.
 */
public class PatientRegistrationFormToPatientTransformerTest {

    private PatientRegistrationFormToPatientTransformer patientRegistrationFormToPatientTransformer;
    private Credentials credentials;
    private PatientRegistrationForm form;

    @Before
    public void setUp() {
        patientRegistrationFormToPatientTransformer = new PatientRegistrationFormToPatientTransformer();
        credentials = new Credentials();
        form = new PatientRegistrationForm();
        form.setName("name");
        form.setSurname("surname");
        form.setEmail("email");
        form.setPassword("1234");
        form.setBirthDay("04-09-1996");
        form.setLogin("login");
        form.setLastname("lastname");
        patientRegistrationFormToPatientTransformer.credentialsCreator = mock(CredentialsCreator.class);
        when(patientRegistrationFormToPatientTransformer.credentialsCreator
                .createAndSaveCredentialsForUser(anyString(), anyString(), anyString(), any(Role.class)))
                .thenReturn(credentials);
    }

    @Test
    public void applyShouldTransformFormToPatient() {
        Patient patient = patientRegistrationFormToPatientTransformer.apply(form);
        Assert.assertEquals(credentials, patient.getCredentials());
    }

}
