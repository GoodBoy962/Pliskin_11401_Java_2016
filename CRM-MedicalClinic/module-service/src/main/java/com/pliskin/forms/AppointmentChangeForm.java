package com.pliskin.forms;

import com.pliskin.model.PatientHistory;

import javax.validation.constraints.NotNull;
import java.sql.Time;

/**
 * Created by aleksandrpliskin on 19.04.16.
 */
public class AppointmentChangeForm {

    private String patient;

    private Time time;

    @NotNull
    private Integer cost;

    @NotNull
    private String description;

    public AppointmentChangeForm() {
    }

    public AppointmentChangeForm(PatientHistory patientHistory) {
        this.patient = patientHistory.getPatient().getFio();
        this.time = patientHistory.getDoctorSchedule().getStartTime();
        this.cost = patientHistory.getCost();
        this.description = patientHistory.getDescription();
    }

    public Integer getCost() {
        return cost;
    }

    public void setCost(Integer cost) {
        this.cost = cost;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getPatient() {
        return patient;
    }

    public void setPatient(String patient) {
        this.patient = patient;
    }

    public Time getTime() {
        return time;
    }

    public void setTime(Time time) {
        this.time = time;
    }
}
