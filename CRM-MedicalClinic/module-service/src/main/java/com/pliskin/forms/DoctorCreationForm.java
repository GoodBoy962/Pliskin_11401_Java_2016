package com.pliskin.forms;

import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.NotEmpty;

import javax.validation.constraints.Size;

/**
 * Created by aleksandrpliskin on 10.04.16.
 */
public class DoctorCreationForm {

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

    @NotEmpty(message = "поле не дожно быть пустым")
//    @DateTimeFormat(pattern = "yyyy-mm-dd")
    private String birthDay;

    @NotEmpty(message = "поле не дожно быть пустым")
//    @DateTimeFormat(pattern = "yyyy-mm-dd")
    private String employmentDate;

    @NotEmpty(message = "поле не дожно быть пустым")
//    @DateTimeFormat(pattern = "yyyy-mm-dd")
    private String inceptionDate;

    private String specialization;

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getBirthDay() {
        return birthDay;
    }

    public void setBirthDay(String birthDay) {
        this.birthDay = birthDay;
    }

    public String getEmploymentDate() {
        return employmentDate;
    }

    public void setEmploymentDate(String employmentDate) {
        this.employmentDate = employmentDate;
    }

    public String getInceptionDate() {
        return inceptionDate;
    }

    public void setInceptionDate(String inceptionDate) {
        this.inceptionDate = inceptionDate;
    }

    public String getSpecialization() {
        return specialization;
    }

    public void setSpecialization(String specialization) {
        this.specialization = specialization;
    }
}
