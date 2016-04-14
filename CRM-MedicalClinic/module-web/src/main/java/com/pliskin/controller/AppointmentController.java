package com.pliskin.controller;

import com.pliskin.service.DoctorScheduleService;
import com.pliskin.service.DoctorService;
import com.pliskin.service.PatientHistoryService;
import com.pliskin.util.SecurityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;

/**
 * Created by aleksandrpliskin on 13.04.16.
 */
@Controller
@RequestMapping("/appointment")
public class AppointmentController {

    @Autowired
    DoctorService doctorService;

    @Autowired
    DoctorScheduleService doctorScheduleService;

    @Autowired
    PatientHistoryService patientHistoryService;

    @RequestMapping(value = "/new", method = RequestMethod.GET)
    public String getFormToCreateAppointment(HttpServletRequest request, Model model) {
        model.addAttribute("doctor", doctorService.getDoctor(Long.valueOf(request.getParameter("doctor_id"))));//TODO if no such
        model.addAttribute("time", request.getParameter("time"));//TODO here too
        model.addAttribute("w_day", request.getParameter("w_day"));//TODO here too
        return "new-appointment";
    }

    @RequestMapping(value = "/dates", method = RequestMethod.GET)
    public String getDates(Model model,
                           @RequestParam("period") String period,
                           @RequestParam("w_day") String weekDay,
                           @RequestParam("time") String time,
                           @RequestParam("doctorFio") String doctorFio) {
        model.addAttribute("dates", doctorScheduleService.getPossibleDates(period, weekDay, time, doctorFio));
        return "appointment_dates";
    }

    @RequestMapping(value = "", method = RequestMethod.POST)
    public String createAppointment(@RequestParam("date") String date,
                                    @RequestParam("doctorFio") String doctorFio,
                                    @RequestParam("weekDay") String weekDay,
                                    @RequestParam("time") String time) {
        patientHistoryService.createHistory(doctorFio, weekDay, time, date);
        return "redirect:/patient";
    }

    @RequestMapping(value = "", method = RequestMethod.GET)
    public String getAppointmentsHistory(Model model) {
        model.addAttribute("appointments",patientHistoryService.getHistories(SecurityUtils.getCurrentUser()));
        return "appointments";
    }

}
