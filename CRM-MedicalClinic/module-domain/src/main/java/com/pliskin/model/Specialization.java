package com.pliskin.model;

import javax.persistence.*;

/**
 * Created by aleksandrpliskin on 06.04.16.
 */
@Entity
@Table(name = "specializations")
@SequenceGenerator(sequenceName = "specializations_id_seq", name = "spec_gen")
public class Specialization {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "spec_gen")
    private Long id;

    private String name;

    @ManyToOne
    @JoinTable(name = "specializations_medical_clinics",
            joinColumns = @JoinColumn(name = "specialization_id"),
            inverseJoinColumns = @JoinColumn(name = "medical_clinic_id")
    )
    private MedicalClinic medicalClinic;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public MedicalClinic getMedicalClinic() {
        return medicalClinic;
    }

    public void setMedicalClinic(MedicalClinic medicalClinic) {
        this.medicalClinic = medicalClinic;
    }
}
