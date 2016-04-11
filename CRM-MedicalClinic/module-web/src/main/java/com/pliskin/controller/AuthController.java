package com.pliskin.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;

/**
 * Created by aleksandrpliskin on 06.04.16.
 */
@Controller
public class AuthController {

    @RequestMapping(value = "/login")
    public String getLoginPage(@RequestParam(value = "error", required = false) Boolean error,
                               Model model) {
        if (Boolean.TRUE.equals(error)) {
            model.addAttribute("error", error);
        }
        return "login";
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
            return "login";
        }
    }

    @RequestMapping("/registration")
    public String getRegistrationPage(Model model) {
        return "registration";
    }

}
