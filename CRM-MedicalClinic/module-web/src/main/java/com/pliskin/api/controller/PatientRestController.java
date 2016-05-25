package com.pliskin.api.controller;

import com.pliskin.model.Patient;
import com.pliskin.repository.CredentialsRepository;
import com.pliskin.repository.PatientRepository;
import com.pliskin.service.PatientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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

}
