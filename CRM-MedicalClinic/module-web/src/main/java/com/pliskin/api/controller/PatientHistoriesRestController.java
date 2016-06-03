package com.pliskin.api.controller;

import com.pliskin.api.mapper.PatientHistoriesToStringMapper;
import com.pliskin.repository.CredentialsRepository;
import com.pliskin.service.PatientHistoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by aleksandrpliskin on 27.05.16.
 */
@RestController
@RequestMapping(value = "api/v1/patient_histories")
public class PatientHistoriesRestController {

    @Autowired
    PatientHistoryService patientHistoryService;

    @Autowired
    CredentialsRepository credentialsRepository;

    @Autowired
    PatientHistoriesToStringMapper mapper;

    @RequestMapping(value = "/{login}", method = RequestMethod.GET)
    public List<String> getPatientHistories(@PathVariable("login") String login) {
        return patientHistoryService.getHistories(credentialsRepository.findOneByLogin(login))
                .stream().map(patientHistory -> mapper.apply(patientHistory))
                .collect(Collectors.toList());
    }

}
