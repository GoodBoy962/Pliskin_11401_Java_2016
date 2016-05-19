package com.pliskin.api.controller;

import com.pliskin.api.dto.ApiResponse;
import com.pliskin.model.Patient;
import com.pliskin.service.PatientService;
import org.springframework.beans.factory.annotation.Autowired;
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
    PatientService patientService;

    @RequestMapping(produces = "application/json", consumes = "application/json")
    public ResponseEntity<ApiResponse<Patient>> getPatientIndex() {
        return createGoodResponse(patientService.getPatient());
    }

}
