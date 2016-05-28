package com.pliskin.api.controller;

import com.pliskin.model.Credentials;
import com.pliskin.model.Patient;
import com.pliskin.repository.CredentialsRepository;
import com.pliskin.repository.PatientRepository;
import com.pliskin.service.PatientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Objects;

/**
 * Created by aleksandrpliskin on 18.05.16.
 */
@RestController
@RequestMapping(value = "/api/v1/patient")
public class PatientRestController extends BaseApiController {

    @Autowired
    PatientRepository patientRepository;

    @Autowired
    CredentialsRepository credentialsRepository;

    @Autowired
    PatientService patientService;

    @RequestMapping
    public ResponseEntity<Patient> getPatientIndex(String login) {
        return new ResponseEntity<>(patientService.getPatient(credentialsRepository.findOneByLogin(login)), HttpStatus.OK);
    }

    @RequestMapping(value = "/info", method = RequestMethod.POST)
    public ResponseEntity<Boolean> changePatientInfo(
            @RequestParam("oldLogin") String oldLogin,
            @RequestParam("login") String login,
            @RequestParam("fio") String fio,
            @RequestParam("email") String email) {
        Patient patient = patientService.getPatient(credentialsRepository.findOneByLogin(oldLogin));
        if (credentialsRepository.findOneByEmail(email) != null
                && !Objects.equals(patient.getId(), patientService.getPatient(credentialsRepository.findOneByEmail(email)).getId())
                || (credentialsRepository.findOneByLogin(login) != null
                && !Objects.equals(patient.getId(), patientService.getPatient(credentialsRepository.findOneByLogin(login)).getId()))) {
            return createGoodResponse(Boolean.FALSE);
        }
        Credentials credentials = patient.getCredentials();
        credentials.setLogin(login);
        credentials.setEmail(email);
        patient.setFio(fio);
        patient.setCredentials(credentials);
        credentialsRepository.save(credentials);
        patientRepository.save(patient);
        return createGoodResponse(Boolean.TRUE);
    }

}
