package com.pliskin.service.impl;

import com.pliskin.model.Doctor;
import com.pliskin.model.DoctorSchedule;
import com.pliskin.model.enums.WeekDay;
import com.pliskin.repository.DoctorScheduleRepository;
import com.pliskin.repository.PatientHistoryRepository;
import com.pliskin.service.DoctorScheduleService;
import com.pliskin.service.DoctorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.sql.Time;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;

/**
 * Created by aleksandrpliskin on 12.04.16.
 */
@Service
public class DoctorScheduleServiceImpl implements DoctorScheduleService {

    @Autowired
    DoctorScheduleRepository doctorScheduleRepository;

    @Autowired
    Function<Object, DoctorSchedule> transformer;

    @Autowired
    PatientHistoryRepository patientHistoryRepository;

    @Autowired
    DoctorService doctorService;

    @Autowired
    Function<String, String> timeTransformer;

    @Override
    public List<DoctorSchedule> getDoctorSchedule(Doctor doctor) {
        return doctorScheduleRepository.findByDoctor(doctor);
    }

    @Transactional
    @Override
    public void createDoctorSchedule(Long id, Set attachments) {
        Doctor doctor = doctorService.getDoctor(id);
        List<DoctorSchedule> doctorSchedules = (List<DoctorSchedule>) attachments.stream().map(transformer).collect(Collectors.toList());
        doctorSchedules.forEach(doctorSchedule -> doctorSchedule.setDoctor(doctor));
        doctorScheduleRepository.save(doctorSchedules);
    }

    @Override
    public List<Date> getPossibleDates(String period, String weekDay, String time, String doctorFio) {
        Doctor doctor = doctorService.getDoctor(doctorFio);
        String timeValue = timeTransformer.apply(time);
        List<Date> dates = getRightDays(period, weekDay, doctor, timeValue);
        return dates;
    }

    private Date getEndDate(Date sDate, String period) {
        Calendar resultDate = Calendar.getInstance();
        resultDate.setTime(sDate);
        switch (period) {
            case "w":
                resultDate.add(Calendar.WEEK_OF_MONTH, 2);
                break;
            case "m":
                resultDate.add(Calendar.MONTH, 1);
                break;
            default:
                resultDate.add(Calendar.MONTH, 2);
        }
        return resultDate.getTime();
    }

    private List<Date> getRightDays(String period, String weekDay, Doctor doctor, String time) {
        DateFormat formatter = new SimpleDateFormat("HH:mm:ss");
        List<Date> dates = new ArrayList<>();
        List<Date> resultDates = new ArrayList<>();
        try {
            long timeStart = formatter.parse(time.substring(0, 8)).getTime();
            long timeEnd = formatter.parse(time.substring(9, time.length())).getTime();
            Time startTime = new Time(timeStart);
            Time eTime = new Time(timeEnd);
            Date startDate = new Date();
            Date endDate = getEndDate(startDate, period);
            final long interval = 24 * 1000 * 60 * 60;
            long endTime = endDate.getTime();
            long curTime = startDate.getTime();
            while (curTime <= endTime) {
                dates.add(new Date(curTime));
                curTime += interval;
            }
            for (Date date : dates) {
                Calendar c = Calendar.getInstance();
                c.setTime(date);
                SimpleDateFormat dateFormat = new SimpleDateFormat("EEEEE", Locale.US);
                String dayOfWeek = dateFormat.format(date);
                if (weekDay.toUpperCase().equals(dayOfWeek.toUpperCase())) {
                    if (patientHistoryRepository.findByDateAndDoctorSchedule(
                            date, doctorScheduleRepository.findByDoctorAndWeekDayAndStartTimeAndEndTime(
                                    doctor, WeekDay.valueOf(weekDay), startTime, eTime)
                    ) == null) {
                        resultDates.add(date);
                    }

                }
            }
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return resultDates;
    }

}
