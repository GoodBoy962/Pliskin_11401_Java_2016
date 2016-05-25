package com.pliskin.util.transformers;

import com.pliskin.model.Doctor;
import com.pliskin.model.DoctorSchedule;
import com.pliskin.model.enums.WeekDay;
import com.pliskin.repository.DoctorScheduleRepository;
import com.pliskin.repository.PatientHistoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.text.SimpleDateFormat;
import java.util.*;
import java.util.function.BiFunction;
import java.util.stream.Collectors;

import static com.pliskin.util.DateUtils.getEndDate;

/**
 * Created by aleksandrpliskin on 24.04.16.
 */
@Component
public class PeriodAndDoctorToMapOfDateListOfDoctorSchedulesTransformer implements BiFunction<String, Doctor, Map<Date, List<DoctorSchedule>>> {

    @Autowired
    DoctorScheduleRepository doctorScheduleRepository;

    @Autowired
    PatientHistoryRepository patientHistoryRepository;

    @Override
    public Map<Date, List<DoctorSchedule>> apply(String period, Doctor doctor) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("EEEEE", Locale.US);
        List<Date> dates = new ArrayList<>();
        Map<Date, List<DoctorSchedule>> map = new HashMap<>();
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
            List<DoctorSchedule> resultDates = doctorScheduleRepository
                    .findByDoctorAndWeekDay(doctor, WeekDay.valueOf(dateFormat.format(date).toUpperCase()))
                    .stream().filter(doctorSchedule -> patientHistoryRepository.findByDateAndDoctorSchedule(
                    date, doctorSchedule) == null).collect(Collectors.toList());
            map.put(date, resultDates);
        }
        return map;
    }

}
