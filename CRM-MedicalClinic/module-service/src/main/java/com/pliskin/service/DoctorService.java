package com.pliskin.service;

import com.pliskin.forms.DoctorCreationForm;
import com.pliskin.model.Doctor;

import java.util.List;

/**
 * Created by aleksandrpliskin on 10.04.16.
 */
public interface DoctorService {

    void createDoctor(DoctorCreationForm form);

    List<Doctor> getAll();
}
