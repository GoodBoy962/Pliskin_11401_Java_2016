package com.pliskin.service.impl;

import com.pliskin.model.Doctor;
import com.pliskin.model.DoctorSchedule;
import com.pliskin.repository.DoctorScheduleRepository;
import com.pliskin.repository.PatientHistoryRepository;
import com.pliskin.service.DoctorService;
import com.pliskin.service.OfficeService;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

/**
 * Created by aleksandrpliskin on 23.04.16.
 */
public class DoctorScheduleServiceImplTest {

    private static List<DoctorSchedule> doctorSchedules;
    private static Doctor doctor;
    private static DoctorScheduleServiceImpl doctorScheduleService;

    @BeforeClass
    public static void setUp() {
        doctorScheduleService = new DoctorScheduleServiceImpl();
        doctorScheduleService.doctorScheduleRepository = mock(DoctorScheduleRepository.class);
        doctorScheduleService.patientHistoryRepository = mock(PatientHistoryRepository.class);
        doctorScheduleService.doctorService = mock(DoctorService.class);
        doctorScheduleService.officeService = mock(OfficeService.class);
        doctorScheduleService.transformer = (Function<Object, DoctorSchedule>) mock(Function.class);
        doctorScheduleService.timeTransformer = (Function<String, String>) mock(Function.class);
        doctor = new Doctor();
        doctorSchedules = new ArrayList<>();
        when(doctorScheduleService.doctorScheduleRepository.findByDoctor(doctor)).thenReturn(doctorSchedules);
        when(doctorScheduleService.doctorService.getDoctor(1L)).thenReturn(doctor);
    }

    @Test
    public void getDoctorScheduleShouldReturnCorrectListOfDoctorScheduleByDoctor() {
        Assert.assertEquals(doctorScheduleService.getDoctorSchedule(doctor), doctorSchedules);
    }

    @Test
    public void createDoctorScheduleShouldCreateCorrectSchedule() {

    }

}
