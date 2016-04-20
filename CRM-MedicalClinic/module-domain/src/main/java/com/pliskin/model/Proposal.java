package com.pliskin.model;

import javax.persistence.*;

/**
 * Created by aleksandrpliskin on 20.04.16.
 */
@Entity
@Table(name = "proposals")
@SequenceGenerator(sequenceName = "proposals_id_seq", name = "proposals_gen",allocationSize = 1)
public class Proposal {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "proposals_gen")
    private Long id;

    private String fio;

    private String email;

    private String message;

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
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

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
