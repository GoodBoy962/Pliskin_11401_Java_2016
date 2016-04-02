package com.pliskin.model;

import com.pliskin.model.enums.Role;

import javax.persistence.*;

/**
 * Created by aleksandrpliskin on 01.04.16.
 */
@Entity
@Table(name = "credentials")
@SequenceGenerator(sequenceName = "credentials_id_seq", name = "credentials_gen", allocationSize = 1)
public class Credentials {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "credentials_gen")
    private Long id;

    @Enumerated(EnumType.STRING)
    private Role role;

    private String login;

    private String email;

    private String password;

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }
}
