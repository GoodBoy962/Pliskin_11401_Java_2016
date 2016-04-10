package com.pliskin.model;

import javax.persistence.*;

/**
 * Created by aleksandrpliskin on 02.04.16.
 */
@Entity
@Table(name = "admins")
@SequenceGenerator(sequenceName = "admins_id_seq", name = "admins_gen", allocationSize = 1)
public class Admin {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "admins_gen")
    private Long id;

    @OneToOne
    @JoinColumn(name = "office_id")
    private Office office;

    private String fio;

    @OneToOne
    @JoinColumn(name = "credentials_id")
    private Credentials credentials;

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

    public Office getOffice() {
        return office;
    }

    public void setOffice(Office office) {
        this.office = office;
    }

    public Credentials getCredentials() {
        return credentials;
    }

    public void setCredentials(Credentials credentials) {
        this.credentials = credentials;
    }
}
