package com.pliskin.service.impl;

import com.pliskin.model.Doctor;
import com.pliskin.model.DoctorSchedule;
import com.pliskin.repository.DoctorScheduleRepository;
import com.pliskin.service.DoctorScheduleService;
import com.pliskin.service.DoctorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;
import java.util.function.Function;
import java.util.stream.Collectors;

/**
 * Created by aleksandrpliskin on 12.04.16.
 */
@Service
public class DoctorScheduleServiceImpl implements DoctorScheduleService {

    @Autowired
    DoctorScheduleRepository doctorScheduleRepository;

    @Autowired
    Function<Object, DoctorSchedule> transformer;

    @Autowired
    DoctorService doctorService;

    @Override
    public List<DoctorSchedule> getDoctorSchedule(Doctor doctor) {
        return doctorScheduleRepository.findByDoctor(doctor);
    }

    @Override
    public void createDoctorSchedule(Long id, Set attachments) {
        Doctor doctor = doctorService.getDoctor(id);
        List<DoctorSchedule> doctorSchedules = (List<DoctorSchedule>) attachments.stream().map(transformer).collect(Collectors.toList());
        doctorSchedules.forEach(doctorSchedule -> doctorSchedule.setDoctor(doctor));
        doctorScheduleRepository.save(doctorSchedules);
    }

}
