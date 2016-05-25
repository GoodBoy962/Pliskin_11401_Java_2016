package com.pliskin.model;

import javax.persistence.*;

/**
 * Created by aleksandrpliskin on 02.04.16.
 */
@Entity
@Table(name = "system_admins")
@SequenceGenerator(sequenceName = "system_admins_id_seq", name = "system_admins_gen", allocationSize = 1)
public class SystemAdmin {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "system_admins_gen")
    private Long id;

    @OneToOne
    @JoinColumn(name = "credentials_id")
    private Credentials credentials;

    public Credentials getCredentials() {
        return credentials;
    }

    public void setCredentials(Credentials credentials) {
        this.credentials = credentials;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "SystemAdmin{" +
                "credentials=" + credentials +
                ", id=" + id +
                '}';
    }
}
