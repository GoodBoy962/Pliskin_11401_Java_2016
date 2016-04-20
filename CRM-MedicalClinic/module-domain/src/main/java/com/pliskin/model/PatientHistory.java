package com.pliskin.model;

import javax.persistence.*;
import java.util.Date;

/**
 * Created by aleksandrpliskin on 01.04.16.
 */
@Entity
@Table(name = "patient_histories")
@SequenceGenerator(sequenceName = "patient_histories_id_seq", name = "patient_histories_gen", allocationSize = 1)
public class PatientHistory {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "patient_histories_gen")
    private Long id;

    @OneToOne
    @JoinColumn(name = "patient_id")
    private Patient patient;

    @OneToOne
    @JoinColumn(name = "doctor_schedule_id")
    private DoctorSchedule doctorSchedule;

    private Integer cost;

    private Boolean status;

    private String description;

    private Date date;

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Integer getCost() {
        return cost;
    }

    public void setCost(Integer cost) {
        this.cost = cost;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Patient getPatient() {
        return patient;
    }

    public void setPatient(Patient patient) {
        this.patient = patient;
    }

    public Boolean getStatus() {
        return status;
    }

    public void setStatus(Boolean status) {
        this.status = status;
    }

    public DoctorSchedule getDoctorSchedule() {
        return doctorSchedule;
    }

    public void setDoctorSchedule(DoctorSchedule doctorSchedule) {
        this.doctorSchedule = doctorSchedule;
    }

    @Override
    public String toString() {
        return "PatientHistory{" +
                "cost=" + cost +
                ", id=" + id +
                ", patient=" + patient +
                ", doctorSchedule=" + doctorSchedule +
                ", status=" + status +
                ", description='" + description + '\'' +
                ", date=" + date +
                '}';
    }
}
