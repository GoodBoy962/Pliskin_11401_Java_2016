package com.pliskin.controller;

import com.pliskin.model.Office;
import com.pliskin.model.Specialization;
import com.pliskin.service.*;
import com.pliskin.util.SecurityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

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

    @Autowired
    OfficeService officeService;

    @Autowired
    SpecializationService specializationService;

    @RequestMapping(value = "/new", method = RequestMethod.GET)
    public String getFormToCreateAppointment(HttpServletRequest request, Model model) {
        if (request.getParameter("doctor_id") != null) {
            model.addAttribute("doctor", doctorService.getDoctor(Long.valueOf(request.getParameter("doctor_id"))));
            model.addAttribute("time", request.getParameter("time"));
            model.addAttribute("w_day", request.getParameter("w_day"));
            return "/new-appointment";
        } else {
            return "/new-appointment-cool";
        }
    }

    @RequestMapping(value = "/dates", method = RequestMethod.GET)
    public String getDates(Model model,
                           @RequestParam("period") String period,
                           @RequestParam("w_day") String weekDay,
                           @RequestParam("time") String time,
                           @RequestParam("doctorFio") String doctorFio) {
        model.addAttribute("dates", doctorScheduleService.getPossibleDates(period, weekDay, time, doctorFio));
        return "/appointment_dates";
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
        model.addAttribute("appointments", patientHistoryService.getHistories(SecurityUtils.getCurrentUser()));
        return "/appointments";
    }

    @RequestMapping(value = "/offices", method = RequestMethod.GET)
    public String getOffices(@RequestParam("city") String city, Model model) {
        List<Office> offices = officeService.getOfficesByCity(city);
        model.addAttribute("offices", offices);
        return "/offices-list";
    }

    @RequestMapping(value = "/specializations", method = RequestMethod.GET)
    public String getSpecializationsOfDoctorsInOffice(@RequestParam("city") String city,
                                                      @RequestParam("address") String address,
                                                      Model model) {
        Set<Specialization> specializations = specializationService.getSpecializationsOfDoctorsInOfficeByCityAndAddress(city, address);
        model.addAttribute("specializations", new ArrayList<>(specializations));
        return "/specializations-list";
    }

    @RequestMapping(value = "/dates_doctors", method = RequestMethod.GET)
    public String getPossibleDatesAndDoctors(@RequestParam("city") String city,
                                             @RequestParam("address") String address,
                                             @RequestParam("specialization") String specialization,
                                             @RequestParam("period") String period,
                                             Model model) {
        model.addAttribute("dates_doctors", doctorScheduleService.getAllPossibleDates(city, address, specialization, period));
        return "/doctors_dates";
    }

}
