package com.pliskin.controller;

import com.pliskin.service.DoctorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Created by aleksandrpliskin on 06.04.16.
 */
@Controller
@RequestMapping(value = "/doctor")
public class DoctorController {

    @Autowired
    DoctorService doctorService;

    @RequestMapping(value = "")
    public String getDoctorIndex(Model model) {
        model.addAttribute("doctor", doctorService.getDoctor());
        return "/doctor";
    }

}
