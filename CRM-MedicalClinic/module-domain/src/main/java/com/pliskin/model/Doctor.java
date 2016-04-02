package com.pliskin.model;

import javax.persistence.*;
import java.util.Date;

/**
 * Created by aleksandrpliskin on 01.04.16.
 */
@Entity
@Table(name = "doctors")
@SequenceGenerator(sequenceName = "doctors_id_seq", name = "doctors_gen", allocationSize = 1)
public class Doctor {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "doctors_gen")
    private Long id;

    private String fio;

    @OneToOne
    @JoinColumn(name = "credentials_id")
    private Credentials credentials;

    @Column(name = "birth_day")
    private Date birthDay;

    @Column(name = "employment_date")
    private Date employmentDate;

    @Column(name = "inception_date")
    private Date inceptionDate;

    @ManyToOne
    @JoinColumn(name = "office_id")
    private Office office;

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

    public Date getEmploymentDate() {
        return employmentDate;
    }

    public void setEmploymentDate(Date employmentDate) {
        this.employmentDate = employmentDate;
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

    public Date getInceptionDate() {
        return inceptionDate;
    }

    public void setInceptionDate(Date inceptionDate) {
        this.inceptionDate = inceptionDate;
    }

    public Office getOffice() {
        return office;
    }

    public void setOffice(Office office) {
        this.office = office;
    }
}
