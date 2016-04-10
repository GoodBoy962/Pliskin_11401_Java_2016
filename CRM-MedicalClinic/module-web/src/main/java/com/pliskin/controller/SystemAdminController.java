package com.pliskin.controller;

import com.pliskin.forms.MedicalClinicRegistrationForm;
import com.pliskin.forms.OfficeAdminCreationForm;
import com.pliskin.service.MedicalClinicService;
import com.pliskin.service.OfficeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.Validator;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
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

    @Autowired
    OfficeService officeService;

    @Autowired
    @Qualifier(value = "officeAdminForm")
    Validator validator;

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

    @RequestMapping(value = "/medical_clinics/{id}/offices/new", method = RequestMethod.GET)
    public String getFormToAddOfficeAndAdminForMedClinic(@PathVariable("id") String medicalClinicId,
                                                         Model model) {
        model.addAttribute("office_admin_form", new OfficeAdminCreationForm());
        model.addAttribute("id", medicalClinicId);
        return "new-office_admin";
    }

    @RequestMapping(value = "/medical_clinics/{id}/offices", method = RequestMethod.POST)
    public String createOfficeAndMedClinicForIt(@PathVariable("id") Long medicalClinicId,
                                                @ModelAttribute("office_admin_form") @Valid OfficeAdminCreationForm form,
                                                BindingResult result) {
        validator.validate(form, result);
        if (result.hasErrors()) {
            return "new-office_admin";
        }
        officeService.createOfficeAndAdmin(form, medicalClinicId);
        return "redirect:/medical_clinics/" + medicalClinicId + "/";
    }
}
