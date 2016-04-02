package com.pliskin.model;

import javax.persistence.*;

/**
 * Created by aleksandrpliskin on 01.04.16.
 */
@Entity
@Table(name = "medical_clinics")
@SequenceGenerator(sequenceName = "medical_clinics_id_seq", name = "medical_clinics_gen", allocationSize = 1)
public class MedicalClinic {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "medical_clinics_gen")
    private Long id;

    private String name;

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
}
