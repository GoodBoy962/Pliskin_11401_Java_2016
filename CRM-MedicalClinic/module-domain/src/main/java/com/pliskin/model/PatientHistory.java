package com.pliskin.model;

import javax.persistence.*;

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

    @ManyToOne
    @JoinColumn(name = "patient_id")
    private Patient patient;

    @ManyToOne
    @JoinColumn(name = "doctor_id")
    private Doctor doctor;

    @OneToOne
    @JoinColumn(name = "doctor_schedule_id")
    private DoctorSchedule doctorSchedule;

    private Double cost;

    private Boolean status;

    public Double getCost() {
        return cost;
    }

    public void setCost(Double cost) {
        this.cost = cost;
    }

    public Doctor getDoctor() {
        return doctor;
    }

    public void setDoctor(Doctor doctor) {
        this.doctor = doctor;
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
}
