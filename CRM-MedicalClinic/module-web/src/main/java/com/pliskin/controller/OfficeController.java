package com.pliskin.controller;

import com.pliskin.model.Office;
import com.pliskin.service.DoctorService;
import com.pliskin.service.OfficeService;
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
public class OfficeController {

    @Autowired
    OfficeService officeService;

    @Autowired
    DoctorService doctorService;

    @RequestMapping(value = "/offices/{id}", method = RequestMethod.GET)
    public String getOffice(@PathVariable("id") Long id, Model model) {
        Office office = officeService.getOffice(id);
        model.addAttribute("office", office);
        model.addAttribute("doctors", doctorService.getDoctorsByOffice(office));
        return "office";
    }

}
