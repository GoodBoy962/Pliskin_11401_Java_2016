package com.pliskin.forms;

/**
 * Created by aleksandrpliskin on 13.04.16.
 */
public class AppointmentCreationForm {

    private String weekDay;

    private String time;

    private String doctorFio;

    public String getDoctorFio() {
        return doctorFio;
    }

    public void setDoctorFio(String doctorFio) {
        this.doctorFio = doctorFio;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getWeekDay() {
        return weekDay;
    }

    public void setWeekDay(String weekDay) {
        this.weekDay = weekDay;
    }
}
