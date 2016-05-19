package com.pliskin.api.controller;

import com.pliskin.api.dto.ApiResponse;
import com.pliskin.model.Doctor;
import com.pliskin.model.DoctorSchedule;
import com.pliskin.model.Office;
import com.pliskin.service.DoctorScheduleService;
import com.pliskin.service.DoctorService;
import com.pliskin.service.OfficeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collections;
import java.util.List;

/**
 * Created by aleksandrpliskin on 18.05.16.
 */
@RestController
@RequestMapping(value = "/api/v1")
public class DoctorsWatchRestController extends BaseApiController {

    @Autowired
    DoctorService doctorService;

    @Autowired
    DoctorScheduleService doctorScheduleService;

    @Autowired
    OfficeService officeService;

    @RequestMapping(value = "/medical_clinics/{id}/offices/{officeId}/doctors", method = RequestMethod.GET,
            produces = "application/json", consumes = "application/json")
    public ResponseEntity<ApiResponse<List<Doctor>>> getDoctors(@PathVariable("id") Long id,
                                                                @PathVariable("officeId") Long officeId) {
        Office office = officeService.getOffice(officeId);
        if (office == null) {
            return createBadResponse(Collections.singletonList("no such office"), HttpStatus.NOT_FOUND);
        }
        List<Doctor> doctors = doctorService.getAllByOfficeId(officeId);
        if (doctors == null || doctors.size() == 0) {
            return createBadResponse(Collections.singletonList("no doctors in that office"), HttpStatus.NOT_FOUND);
        }
        return createGoodResponse(doctors);

    }

    @RequestMapping(value = "/doctors/{id}", method = RequestMethod.GET,
            produces = "application/json", consumes = "application/json")
    public ResponseEntity<ApiResponse<Doctor>> getDoctor(@PathVariable("id") Long id) {
        Doctor doctor = doctorService.getDoctor(id);
        if (doctor == null) {
            return createBadResponse(Collections.singletonList("no such doctor"), HttpStatus.NOT_FOUND);
        }
        return createGoodResponse(doctor);
    }

    @RequestMapping(value = "/doctors/{id}/timetable", method = RequestMethod.GET,
            produces = "application/json", consumes = "application/json")
    public ResponseEntity<ApiResponse<List<DoctorSchedule>>> getDoctorTimeTable(@PathVariable("id") Long id) {
        Doctor doctor = doctorService.getDoctor(id);
        if (doctor == null) {
            return createBadResponse(Collections.singletonList("no such doctor"), HttpStatus.NOT_FOUND);
        }
        List<DoctorSchedule> doctorSchedules = doctorScheduleService.getDoctorSchedule(doctor);
        if (doctorSchedules == null || doctorSchedules.size() == 0) {
            return createBadResponse(Collections.singletonList("no timetable for that doctor"), HttpStatus.NOT_FOUND);
        }
        return createGoodResponse(doctorSchedules);
    }


}
