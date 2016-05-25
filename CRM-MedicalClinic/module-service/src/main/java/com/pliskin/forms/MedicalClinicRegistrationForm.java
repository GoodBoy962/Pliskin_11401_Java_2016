package com.pliskin.forms;

import org.hibernate.validator.constraints.NotEmpty;

import javax.validation.constraints.Size;

/**
 * Created by aleksandrpliskin on 06.04.16.
 */
public class MedicalClinicRegistrationForm {

    @Size(min = 3, max = 30, message = "название должно состоять не менее, чем из 3 символов, но не более, чем из 30 символов")
    private String name;

    @NotEmpty(message = "должна присутствовать информация")
    private String info;

    public String getInfo() {
        return info;
    }

    public void setInfo(String info) {
        this.info = info;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
