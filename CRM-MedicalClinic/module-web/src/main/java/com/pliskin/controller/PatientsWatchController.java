package com.pliskin.controller;

import com.pliskin.service.PatientHistoryService;
import com.pliskin.service.PatientService;
import com.pliskin.util.SecurityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Created by aleksandrpliskin on 20.04.16.
 */
@Controller
@RequestMapping(value = "/patients")
public class PatientsWatchController {

    @Autowired
    PatientService patientService;

    @Autowired
    PatientHistoryService patientHistoryService;

    @RequestMapping(value = "/{id}")
    public String getPatientPage(@PathVariable("id") Long id, Model model) {
        model.addAttribute("patient", patientService.getPatientById(id));
        return "/patient";
    }

    @RequestMapping(value = "/{id}/appointments")
    public String getPatientAppointments(@PathVariable("id") Long id, Model model) {
        model.addAttribute("appointments", patientHistoryService.getHistories(id));
        return "patient-history";
    }

}
