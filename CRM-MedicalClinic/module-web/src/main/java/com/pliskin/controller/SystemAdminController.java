package com.pliskin.controller;

import com.pliskin.forms.MedicalClinicRegistrationForm;
import com.pliskin.service.MedicalClinicService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.validation.Valid;

/**
 * Created by aleksandrpliskin on 06.04.16.
 */
@Controller
@RequestMapping(value = "/system")
public class SystemAdminController {

    @Autowired
    MedicalClinicService medicalClinicService;

    @RequestMapping(value = "")
    public String getSystemAdminIndex() {
        return "system";
    }

    @RequestMapping(value = "medical_clinics/new")
    public String getFormToCreateMedicalClinic() {
        return "new-mc";
    }

    @RequestMapping(value = "medical_clinics", method = RequestMethod.POST)
    public String createMedicalClinic(@ModelAttribute("medical_clinic_form") @Valid MedicalClinicRegistrationForm form,
                                      BindingResult result,
                                      Model model) {
        if (result.hasErrors()) {
            return "new-mc";
        } else {
            medicalClinicService.createNewMedClinic(form);
            return "redirect:/medical_clinics/" + medicalClinicService.getMedClinic(form.getName()).getId();
        }
    }
}
