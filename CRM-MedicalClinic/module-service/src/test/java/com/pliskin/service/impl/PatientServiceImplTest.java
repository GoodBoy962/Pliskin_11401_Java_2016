package com.pliskin.service.impl;

import com.pliskin.forms.PatientRegistrationForm;
import com.pliskin.model.Credentials;
import com.pliskin.model.Patient;
import com.pliskin.repository.PatientRepository;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;
import org.mockito.stubbing.Answer;

import java.util.function.Function;

import static org.mockito.Matchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

/**
 * Created by aleksandrpliskin on 23.04.16.
 */
public class PatientServiceImplTest {

    private static PatientServiceImpl patientService;
    private static Patient patient;
    private static Credentials credentials;

    @BeforeClass
    public static void setUp() {
        patient = new Patient();
        credentials = new Credentials();
        patientService = new PatientServiceImpl();
        patientService.patientRepository = mock(PatientRepository.class);
        patientService.patientFunction = (Function<PatientRegistrationForm, Patient>) mock(Function.class);
        when(patientService.patientRepository.save(any(Patient.class))).thenAnswer(
                (Answer<Patient>) invocation -> {
                    Object[] args = invocation.getArguments();
                    return (Patient) args[0];
                });
        when(patientService.patientFunction.apply(any(PatientRegistrationForm.class))).thenReturn(patient);
        when(patientService.patientRepository.findByCredentials(credentials)).thenReturn(patient);
        when(patientService.patientRepository.findOne(1L)).thenReturn(patient);
    }

    @Test
    public void saneNewPatientShouldCreateNewPatient() {
        patientService.saveNewPatient(new PatientRegistrationForm());
    }

    @Test
    public void getPatientByCredentialsShouldReturnCorrectPatient() {
        Assert.assertEquals(patient, patientService.getPatient(credentials));
    }

    @Test
    public void getPatientByIdShouldReturnCorrectPatient() {
        Assert.assertEquals(patientService.getPatientById(1L), patient);
    }

    @Test(expected = NullPointerException.class)
    public void getPatientShouldWork() {
        patientService.getPatient();
    }

}
