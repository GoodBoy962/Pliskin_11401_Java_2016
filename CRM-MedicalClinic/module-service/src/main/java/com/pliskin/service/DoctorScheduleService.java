package com.pliskin.service;

import com.pliskin.model.Doctor;
import com.pliskin.model.DoctorSchedule;

import java.util.List;

/**
 * Created by aleksandrpliskin on 12.04.16.
 */
public interface DoctorScheduleService {

    List<DoctorSchedule> getDoctorSchedule(Doctor doctor);

}
