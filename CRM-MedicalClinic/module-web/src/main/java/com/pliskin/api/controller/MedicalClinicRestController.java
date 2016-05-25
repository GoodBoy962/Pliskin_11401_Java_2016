package com.pliskin.api.controller;

import com.pliskin.model.MedicalClinic;
import com.pliskin.model.Office;
import com.pliskin.service.MedicalClinicService;
import com.pliskin.service.OfficeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * Created by aleksandrpliskin on 18.05.16.
 */
@RestController
@RequestMapping(value = "/api/v1")
public class MedicalClinicRestController extends BaseApiController {

    @Autowired
    MedicalClinicService medicalClinicService;

    @Autowired
    OfficeService officeService;

    @RequestMapping(value = "/medical_clinics")
    public ResponseEntity<List<MedicalClinic>> getMedClinics() {
        List<MedicalClinic> medicalClinics = medicalClinicService.getMedClinics();
        if (medicalClinics == null || medicalClinics.size() == 0) {
            return createBadResponse(HttpStatus.NOT_FOUND);
        }
        return createGoodResponse(medicalClinics);
    }

    @RequestMapping(value = "/medical_clinics/{id}")
    public ResponseEntity<List<Office>> getMedClinicPage(@PathVariable("id") Long id) {
        MedicalClinic medicalClinic = medicalClinicService.getMedClinic(id);
        if (medicalClinic == null) {
            return createBadResponse(HttpStatus.NOT_FOUND);
        }
        List<Office> offices = officeService.getMedClinicOffices(medicalClinic);
        if (offices == null || offices.size() == 0) {
            createBadResponse(HttpStatus.NOT_FOUND);
        }
        return createGoodResponse(offices);
    }

}
