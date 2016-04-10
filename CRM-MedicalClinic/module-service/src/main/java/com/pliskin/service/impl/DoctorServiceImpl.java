package com.pliskin.service.impl;

import com.pliskin.forms.DoctorCreationForm;
import com.pliskin.model.Doctor;
import com.pliskin.repository.DoctorRepository;
import com.pliskin.service.DoctorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.function.Function;

/**
 * Created by aleksandrpliskin on 10.04.16.
 */
@Service
public class DoctorServiceImpl implements DoctorService {

    @Autowired
    Function<DoctorCreationForm, Doctor> doctorFunction;

    @Autowired
    DoctorRepository doctorRepository;

    @Override
    public void createDoctor(DoctorCreationForm form) {
        Doctor doctor = doctorFunction.apply(form);
        doctorRepository.save(doctor);
    }
}
