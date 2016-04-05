package com.pliskin.model;

import javax.persistence.*;

/**
 * Created by aleksandrpliskin on 02.04.16.
 */
@Entity
@Table(name = "offices")
@SequenceGenerator(sequenceName = "offices_id_seq", name = "offices_gen", allocationSize = 1)
public class Office {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "offices_gen")
    private Long id;

    private String address;

    @ManyToOne
    @JoinTable(name = "offices_medical_clinics",
            joinColumns = @JoinColumn(name = "office_id"),
            inverseJoinColumns = @JoinColumn(name = "medical_clinic_id")
    )
    private MedicalClinic medicalClinic;

    public Long getId() {
        return id;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public MedicalClinic getMedicalClinic() {
        return medicalClinic;
    }

    public void setMedicalClinic(MedicalClinic medicalClinic) {
        this.medicalClinic = medicalClinic;
    }
}
