package com.pliskin.util.transformers;

import com.pliskin.model.DoctorSchedule;
import com.pliskin.model.enums.WeekDay;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.sql.Time;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.function.Function;

/**
 * Created by aleksandrpliskin on 12.04.16.
 */
@Component
public class AttachmentToDoctorScheduleTransformer implements Function<Object, DoctorSchedule> {

    @Autowired
    Function<String, String> timeTransformer;

    @Override
    public DoctorSchedule apply(Object o) {
        String att = (String) o;
        WeekDay weekDay = WeekDay.valueOf(att.substring(0, att.indexOf('-')));
        DateFormat formatter = new SimpleDateFormat("HH:mm:ss");
        DoctorSchedule doctorSchedule = new DoctorSchedule();
        try {
            String time = timeTransformer.apply(att.substring(att.indexOf('-') + 1, att.length()));
            long timeStart = formatter.parse(time.substring(0, 8)).getTime();
            long timeEnd = formatter.parse(time.substring(9, time.length())).getTime();
            doctorSchedule.setStartTime(new Time(timeStart));
            doctorSchedule.setEndTime(new Time(timeEnd));
            doctorSchedule.setWeekDay(weekDay);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return doctorSchedule;
    }
}
