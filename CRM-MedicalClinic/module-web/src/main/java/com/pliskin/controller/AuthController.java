package com.pliskin.controller;

import com.pliskin.forms.PatientRegistrationForm;
import com.pliskin.service.PatientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.Validator;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

/**
 * Created by aleksandrpliskin on 06.04.16.
 */
@Controller
public class AuthController {

    @Autowired
    PatientService patientService;

    @Qualifier("patientRegistrationFormValidator")
    @Autowired
    Validator validator;

    @RequestMapping(value = "/login")
    public String getLoginPage(@RequestParam(value = "error", required = false) Boolean error,
                               Model model) {
        if (Boolean.TRUE.equals(error)) {
            model.addAttribute("error", error);
        }
        return "/login";
    }

    @RequestMapping("/default")
    public String defaultAfterLogin(HttpServletRequest request) {
        if (request.isUserInRole("ROLE_ADMIN")) {
            return "redirect:/admin";
        } else if (request.isUserInRole("ROLE_PATIENT")) {
            return "redirect:/patient";
        } else if (request.isUserInRole("ROLE_SYSTEM_ADMIN")) {
            return "redirect:/system";
        } else if (request.isUserInRole("ROLE_DOCTOR")) {
            return "redirect:/doctor";
        } else {
            return "/login";
        }
    }

    @RequestMapping(value = "/registration", method = RequestMethod.GET)
    public String getRegistrationPage(Model model) {
        model.addAttribute("userform", new PatientRegistrationForm());
        return "/registration";
    }

    @RequestMapping(value = "/registration", method = RequestMethod.POST)
    public String registerUser(@ModelAttribute("userform") @Valid PatientRegistrationForm form, BindingResult result) {
        validator.validate(form, result);
        if (result.hasErrors()) {
            return "registration";
        }
        patientService.saveNewPatient(form);
        return "redirect:/";
    }

}
