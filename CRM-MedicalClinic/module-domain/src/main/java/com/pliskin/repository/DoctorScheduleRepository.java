package com.pliskin.repository;

import com.pliskin.model.Doctor;
import com.pliskin.model.DoctorSchedule;
import com.pliskin.model.enums.WeekDay;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.sql.Time;
import java.util.List;

/**
 * Created by aleksandrpliskin on 02.04.16.
 */
@Repository
public interface DoctorScheduleRepository extends JpaRepository<DoctorSchedule, Long> {
    List<DoctorSchedule> findByDoctor(Doctor doctor);

    DoctorSchedule findByDoctorAndWeekDayAndStartTimeAndEndTime(Doctor doctor, WeekDay weekDay, Time startTime, Time endTime);

}
