package com.pliskin.service.impl;

import com.pliskin.forms.AppointmentCreationForm;
import com.pliskin.model.PatientHistory;
import com.pliskin.model.enums.WeekDay;
import com.pliskin.repository.DoctorScheduleRepository;
import com.pliskin.repository.PatientHistoryRepository;
import com.pliskin.service.DoctorService;
import com.pliskin.service.PatientHistoryService;
import com.pliskin.service.PatientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.sql.Time;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.function.Function;

/**
 * Created by aleksandrpliskin on 14.04.16.
 */
@Service
public class PatientHistoryServiceImpl implements PatientHistoryService {

    @Autowired
    PatientHistoryRepository patientHistoryRepository;

    @Autowired
    DoctorScheduleRepository doctorScheduleRepository;

    @Autowired
    PatientService patientService;

    @Autowired
    DoctorService doctorService;

    @Autowired
    Function<String, String> transformer;

    @Secured("hasRole('ROLE_PATIENT')")
    @Transactional
    @Override
    public void createHistory(AppointmentCreationForm form, String date) {
        PatientHistory patientHistory = new PatientHistory();
        DateFormat formatter = new SimpleDateFormat("dd.MM.yyyy");
        DateFormat dateFormat = new SimpleDateFormat("yyyy-mm-dd");
        try {
            patientHistory.setDate(formatter.parse(date));
        } catch (ParseException e) {
            e.printStackTrace();
        }
        String time = transformer.apply(form.getTime());
        long timeStart = 0;
        try {
            timeStart = formatter.parse(time.substring(0, 8)).getTime();
            long timeEnd = formatter.parse(time.substring(9, time.length())).getTime();
            Time startTime = new Time(timeStart);
            Time endTime = new Time(timeEnd);
            patientHistory.setStatus(Boolean.FALSE);
            patientHistory.setPatient(patientService.getPatient());
            patientHistory.setDoctorSchedule(doctorScheduleRepository.findByDoctorAndWeekDayAndStartTimeAndEndTime(
                    doctorService.getDoctor(form.getDoctorFio()),
                    WeekDay.valueOf(form.getWeekDay()),
                    startTime,
                    endTime
            ));
            patientHistoryRepository.save(patientHistory);
        } catch (ParseException e) {
            e.printStackTrace();
        }
    }
}
