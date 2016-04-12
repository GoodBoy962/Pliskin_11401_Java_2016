package com.pliskin.service.impl;

import com.pliskin.model.Doctor;
import com.pliskin.model.DoctorSchedule;
import com.pliskin.repository.DoctorScheduleRepository;
import com.pliskin.service.DoctorScheduleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by aleksandrpliskin on 12.04.16.
 */
@Service
public class DoctorScheduleServiceImpl implements DoctorScheduleService {

    @Autowired
    DoctorScheduleRepository doctorScheduleRepository;

    @Override
    public List<DoctorSchedule> getDoctorSchedule(Doctor doctor) {
        return doctorScheduleRepository.findByDoctor(doctor);
    }
}
