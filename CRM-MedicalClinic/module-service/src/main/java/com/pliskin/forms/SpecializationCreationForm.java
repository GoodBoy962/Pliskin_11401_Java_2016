package com.pliskin.forms;

import javax.validation.constraints.NotNull;

/**
 * Created by aleksandrpliskin on 21.04.16.
 */
public class SpecializationCreationForm {

    @NotNull
    private String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
