package com.pliskin.model;

import javax.persistence.*;
import java.sql.Time;
import java.time.DayOfWeek;

/**
 * Created by aleksandrpliskin on 02.04.16.
 */
@Entity
@Table(name = "doctor_schedule")
@SequenceGenerator(sequenceName = "doctor_schedule_id_seq", name = "doctor_schedule_gen", allocationSize = 1)
public class DoctorSchedule {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "doctor_schedule_gen")
    private Long id;

    @ManyToOne
    @JoinColumn(name = "doctor_id")
    private Doctor doctor;

    @Column(name = "week_day")
    private DayOfWeek weekDay;

    @Column(name = "start_time")
    private Time startTime;

    @Column(name = "end_time")
    private Time endTime;

    public Doctor getDoctor() {
        return doctor;
    }

    public void setDoctor(Doctor doctor) {
        this.doctor = doctor;
    }

    public Time getEndTime() {
        return endTime;
    }

    public void setEndTime(Time endTime) {
        this.endTime = endTime;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Time getStartTime() {
        return startTime;
    }

    public void setStartTime(Time startTime) {
        this.startTime = startTime;
    }

    public DayOfWeek getWeekDay() {
        return weekDay;
    }

    public void setWeekDay(DayOfWeek weekDay) {
        this.weekDay = weekDay;
    }
}
