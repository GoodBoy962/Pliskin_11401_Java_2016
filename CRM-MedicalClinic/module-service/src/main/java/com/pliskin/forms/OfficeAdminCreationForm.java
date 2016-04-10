package com.pliskin.forms;

import org.hibernate.validator.constraints.Email;

import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

/**
 * Created by aleksandrpliskin on 07.04.16.
 */
public class OfficeAdminCreationForm {

    @Size(min = 5, max = 50, message = "неверный адрес")
    private String address;

    @Size(min = 1, max = 30, message = "имя неправильно введено")
    private String name;

    @Size(min = 1, max = 30, message = "имя неправильно введено")
    private String surname;

    @Size(max = 32, message = "отчество не длинее 32 символов")
    private String lastname;

    @Size(min = 4, max = 50, message = "логин не менее 4, не более 50 символов")
    private String login;

    @Size(min = 6, max = 32, message = "пароль должен состоять не менее, чем из 6, не более, чем из 32 символов")
    private String password;

    @Email(regexp = ".+@.+", message = "невалидный email")
    private String email;

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
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
}
