package com.pliskin.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * Created by aleksandrpliskin on 13.04.16.
 */
@Controller
@RequestMapping(value = "/appointment")
public class PatientHistoryController {

    @RequestMapping(value = "/new", method = RequestMethod.GET)
    public String getFormToCreateAppointmentReport(Model model) {
        return null;
    }

}
