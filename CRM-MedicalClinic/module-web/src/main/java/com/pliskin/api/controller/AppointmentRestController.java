package com.pliskin.api.controller;

import com.pliskin.model.Doctor;
import com.pliskin.model.DoctorSchedule;
import com.pliskin.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.datetime.DateFormatter;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * Created by aleksandrpliskin on 18.05.16.
 */
@RestController
@RequestMapping("/api/v1/appointment")
public class AppointmentRestController extends BaseApiController {

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

    @RequestMapping(value = "/dates/{city}/{specialization}", method = RequestMethod.GET)
    @ResponseBody
    public ResponseEntity<List<String>> getPossibleDatesAndDoctors(
            @PathVariable("city") String city,
            @PathVariable("specialization") String specialization) {
        String period = "2w";
        Map<Doctor, Map<Date, List<DoctorSchedule>>> map = doctorScheduleService.getAllPossibleDates(city, specialization, period);
        return createGoodResponse(createListOfDates(map));
    }

    @RequestMapping(value = "/new", method = RequestMethod.POST)
    @ResponseStatus(HttpStatus.OK)
    public void createCoolAppointment(
            @RequestParam("appointmentDate") String appointmentDate, @RequestParam("login") String login) {
        patientHistoryService.createHistoryFromCoolForm(appointmentDate, login);
    }

    private List<String> createListOfDates(Map<Doctor, Map<Date, List<DoctorSchedule>>> map) {
        SimpleDateFormat sdf = new SimpleDateFormat("dd.MM.yyyy");
        List<String> list = new ArrayList<>();
        for (Doctor doctor : map.keySet()) {
            for (Date date : map.get(doctor).keySet()) {
                for (DoctorSchedule doctorSchedule : map.get(doctor).get(date)) {
                    String s = doctor.getFio() + " // " + sdf.format(date) + " "+ doctorSchedule.getWeekDay() + " " + doctorSchedule.getStartTime();
                    list.add(s);
                }
            }
        }
        return list;
    }

}
