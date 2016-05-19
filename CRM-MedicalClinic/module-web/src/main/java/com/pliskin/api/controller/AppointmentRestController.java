package com.pliskin.api.controller;

import com.pliskin.api.dto.ApiResponse;
import com.pliskin.model.*;
import com.pliskin.service.*;
import com.pliskin.util.SecurityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Set;

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

    @RequestMapping(value = "/dates", method = RequestMethod.GET)
    public ResponseEntity<ApiResponse<List<Date>>> getDates(@RequestParam("period") String period,
                                                            @RequestParam("w_day") String weekDay,
                                                            @RequestParam("time") String time,
                                                            @RequestParam("doctorFio") String doctorFio) {
        return createGoodResponse(doctorScheduleService.getPossibleDates(period, weekDay, time, doctorFio));
    }

    @RequestMapping(value = "", method = RequestMethod.POST)
    @ResponseStatus(HttpStatus.OK)
    public void createAppointment(@RequestParam("date") String date,
                                  @RequestParam("doctorFio") String doctorFio,
                                  @RequestParam("weekDay") String weekDay,
                                  @RequestParam("time") String time) {
        patientHistoryService.createHistory(doctorFio, weekDay, time, date);
    }

    @RequestMapping(value = "", method = RequestMethod.GET)
    public ResponseEntity<ApiResponse<List<PatientHistory>>> getAppointmentsHistory() {
        return createGoodResponse(patientHistoryService.getHistories(SecurityUtils.getCurrentUser()));
    }

    @RequestMapping(value = "/offices", method = RequestMethod.GET)
    public ResponseEntity<ApiResponse<List<Office>>> getOffices(@RequestParam("city") String city) {
        return createGoodResponse(officeService.getOfficesLikeCity(city));
    }

    @RequestMapping(value = "/specializations", method = RequestMethod.GET)
    public ResponseEntity<ApiResponse<Set<Specialization>>> getSpecializationsOfDoctorsInOffice(
            @RequestParam("city") String city,
            @RequestParam("address") String address) {
        return createGoodResponse(specializationService.getSpecializationsOfDoctorsInOfficeByCityLikeAndAddress(city, address));
    }

    @RequestMapping(value = "/dates_doctors", method = RequestMethod.GET)
    public ResponseEntity<ApiResponse<Map<Doctor, Map<Date, List<DoctorSchedule>>>>> getPossibleDatesAndDoctors(
            @RequestParam("city") String city,
            @RequestParam("address") String address,
            @RequestParam("specialization") String specialization,
            @RequestParam("period") String period) {
        return createGoodResponse(doctorScheduleService.getAllPossibleDates(city, address, specialization, period));
    }

    @RequestMapping(value = "/new", method = RequestMethod.POST)
    @ResponseStatus(HttpStatus.OK)
    public void createCoolAppointment(Model model,
                                      @RequestParam("appointmentDate") String appointmentDate,
                                      @RequestParam("city") String city,
                                      @RequestParam("address") String address,
                                      @RequestParam("specialization") String specialization
    ) {
        patientHistoryService.createHistoryFromCoolForm(appointmentDate);
    }

}
