package com.pliskin.controller;

import com.pliskin.service.DoctorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * Created by aleksandrpliskin on 12.04.16.
 */
@Controller
public class DoctorsWatchController {

    @Autowired
    DoctorService doctorService;

    @RequestMapping(value = "/medical_clinics/{id}/offices/{officeId}/doctors", method = RequestMethod.GET)
    public String getAllDoctors(Model model,
                                @PathVariable("id") String id,
                                @PathVariable("officeId") String officeId) {
        model.addAttribute("doctors", doctorService.getAll());
        return "doctors";
    }


}
