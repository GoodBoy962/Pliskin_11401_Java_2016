package com.pliskin.controller;

import com.pliskin.forms.DoctorCreationForm;
import com.pliskin.model.Office;
import com.pliskin.repository.AdminRepository;
import com.pliskin.service.*;
import com.pliskin.util.SecurityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.Validator;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.validation.Valid;

/**
 * Created by aleksandrpliskin on 06.04.16.
 */
@Controller
@RequestMapping(value = "/admin")
public class AdminController {

    @Autowired
    DoctorService doctorService;

    @Autowired
    SpecializationService specializationService;

    @Autowired
    AdminService adminService;

    @Autowired
    OfficeService officeService;

    @Autowired
    MedicalClinicService medicalClinicService;

    @Qualifier("doctorCreationFormValidator")
    @Autowired
    Validator validator;

    @RequestMapping(value = "")
    public String getAdminIndex(Model model) {
        model.addAttribute("admin", adminService.findOneByCredentials(SecurityUtils.getCurrentUser()));
        Office office = officeService.getOfficeByAdminCredentials();
        model.addAttribute("id", office.getMedicalClinic().getId());
        model.addAttribute("officeId", office.getId());
        return "admin";
    }

    @RequestMapping(value = "/doctors/new", method = RequestMethod.GET)
    public String getFormToCreateNewDoctor(Model model) {
        model.addAttribute("specializations", specializationService.getAllSpecializations());
        model.addAttribute("doctor_form", new DoctorCreationForm());
        return "new-doctor";
    }

    @RequestMapping(value = "/doctors", method = RequestMethod.POST)
    public String createNewDoctor(@ModelAttribute("doctor_form") @Valid DoctorCreationForm form,
                                  BindingResult result,
                                  Model model) {
        validator.validate(form, result);
        if (result.hasErrors()) {
            model.addAttribute("specializations", specializationService.getAllSpecializations());
            return "new-doctor";
        }
        doctorService.createDoctor(form);
        return "redirect:/admin";
    }

}
