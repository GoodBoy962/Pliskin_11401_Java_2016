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
