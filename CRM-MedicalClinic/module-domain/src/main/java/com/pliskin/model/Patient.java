package com.pliskin.model;

import javax.persistence.*;
import java.util.Date;

/**
 * Created by aleksandrpliskin on 01.04.16.
 */
@Entity
@Table(name = "patients")
@SequenceGenerator(sequenceName = "patients_id_seq", name = "patients_gen", allocationSize = 1)
public class Patient {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "patients_gen")
    private Long id;

    private String fio;

    @Column(name = "birth_day")
    private Date birthDay;

    @OneToOne
    @JoinColumn(name = "credential_id")
    private Credentials credentials;

    public Date getBirthDay() {
        return birthDay;
    }

    public void setBirthDay(Date birthDay) {
        this.birthDay = birthDay;
    }

    public Credentials getCredentials() {
        return credentials;
    }

    public void setCredentials(Credentials credentials) {
        this.credentials = credentials;
    }

    public String getFio() {
        return fio;
    }

    public void setFio(String fio) {
        this.fio = fio;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}
