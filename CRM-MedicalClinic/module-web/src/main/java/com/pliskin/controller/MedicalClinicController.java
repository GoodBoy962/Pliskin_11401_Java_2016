package com.pliskin.controller;

import com.pliskin.model.MedicalClinic;
import com.pliskin.service.MedicalClinicService;
import com.pliskin.service.OfficeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Created by aleksandrpliskin on 06.04.16.
 */
@Controller
public class MedicalClinicController {

    @Autowired
    MedicalClinicService medicalClinicService;

    @Autowired
    OfficeService officeService;

    @RequestMapping(value = "medical_clinics")
    public String getMedClinics(Model model) {
        model.addAttribute("medical_clinics", medicalClinicService.getMedClinics());
        return "/medical_clinics_list";
    }

    @RequestMapping(value = "medical_clinics/{id}")
    public String getMedClinicPage(@PathVariable("id") Long id, Model model) {
        MedicalClinic medicalClinic = medicalClinicService.getMedClinic(id);
        model.addAttribute("offs", officeService.getMedClinicOffices(medicalClinic));
        model.addAttribute("mc", medicalClinic);
        return "/med_clinic";
    }

}
