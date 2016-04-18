package com.pliskin.controller;

import com.pliskin.model.Doctor;
import com.pliskin.service.DoctorScheduleService;
import com.pliskin.service.DoctorService;
import com.pliskin.service.PatientHistoryService;
import com.pliskin.util.SecurityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * Created by aleksandrpliskin on 06.04.16.
 */
@Controller
@RequestMapping(value = "/doctor")
public class DoctorController {

    @Autowired
    DoctorService doctorService;

    @Autowired
    DoctorScheduleService doctorScheduleService;

    @Autowired
    PatientHistoryService patientHistoryService;

    @RequestMapping(value = "", method = RequestMethod.GET)
    public String getDoctorIndex(Model model) {
        Doctor doctor = doctorService.getDoctor();
        model.addAttribute("doctor", doctor);
        model.addAttribute("isTimetable", doctorScheduleService.getDoctorSchedule(doctor));
        return "/doctor";
    }

    @RequestMapping(value = "/appointments", method = RequestMethod.GET)
    public String getAppointments(Model model) {
        Doctor doctor = doctorService.getDoctor();
        model.addAttribute("doctor", doctor);
        model.addAttribute("appointments", patientHistoryService.getHistoriesByDoctor(doctor));
        return "/appointments";
    }

    @RequestMapping(value = "/appointments/{id}/change", method = RequestMethod.GET)
    public String getFormToChangeAppointment(Model model, @PathVariable("id") String id) {
        //TODO
        return "change-appointment";
    }
}
