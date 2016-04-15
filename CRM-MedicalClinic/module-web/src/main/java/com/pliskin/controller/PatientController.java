package com.pliskin.controller;

import com.pliskin.service.PatientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Created by aleksandrpliskin on 06.04.16.
 */
@Controller
@RequestMapping(value = "/patient")
public class PatientController {

    @Autowired
    PatientService patientService;

    @RequestMapping(value = "")
    public String getPatientIndex(Model model) {
        model.addAttribute("patient", patientService.getPatient());
        return "/patient";
    }

}
