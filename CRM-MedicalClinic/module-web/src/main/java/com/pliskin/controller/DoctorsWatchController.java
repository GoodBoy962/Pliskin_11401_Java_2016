package com.pliskin.controller;

import com.pliskin.model.Doctor;
import com.pliskin.service.DoctorScheduleService;
import com.pliskin.service.DoctorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletRequest;

/**
 * Created by aleksandrpliskin on 12.04.16.
 */
@Controller
public class DoctorsWatchController {

    @Autowired
    DoctorService doctorService;

    @Autowired
    DoctorScheduleService doctorScheduleService;

    @RequestMapping(value = "/medical_clinics/{id}/offices/{officeId}/doctors", method = RequestMethod.GET)
    public String getAllDoctors(Model model,
                                @PathVariable("id") Long id,
                                @PathVariable("officeId") Long officeId) {
        model.addAttribute("doctors", doctorService.getAllByOfficeId(officeId));
        return "/doctors";
    }

    @RequestMapping(value = "/doctors/{id}", method = RequestMethod.GET)
    public String getDoctorPage(@PathVariable("id") Long id, Model model) {
        Doctor doctor = doctorService.getDoctor(id);
        model.addAttribute("doctor", doctor);
        model.addAttribute("isTimetable", doctorScheduleService.getDoctorSchedule(doctor));
        return "/doctor";
    }

    @RequestMapping(value = "/doctors/{id}/timetable", method = RequestMethod.GET)
    public String getDoctorTimeTable(@PathVariable("id") Long id, Model model) {
        Doctor doctor = doctorService.getDoctor(id);
        model.addAttribute("doctor", doctor);
        model.addAttribute("timetable", doctorScheduleService.getDoctorSchedule(doctor));
        return "/doctor_timetable";
    }

    @RequestMapping(value = "/doctors/{id}/timetable/new", method = RequestMethod.GET)
    public String getFormToCreateDoctorTimeTable(@PathVariable("id") Long id, Model model) {
        Doctor doctor = doctorService.getDoctor(id);
        model.addAttribute("doctor", doctor);
        return "/new-dt";
    }

    @RequestMapping(value = "/doctors/{id}/timetable", method = RequestMethod.POST)
    public String createDoctorTimeTable(@PathVariable("id") Long id, HttpServletRequest request) {
        doctorScheduleService.createDoctorSchedule(id, request.getParameterMap().keySet());
        return "redirect:/doctors/{id}";
    }


}
