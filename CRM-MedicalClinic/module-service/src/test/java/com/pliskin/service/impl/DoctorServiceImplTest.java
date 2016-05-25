package com.pliskin.service.impl;

import com.pliskin.exceptions.NoSuchDoctorException;
import com.pliskin.forms.DoctorCreationForm;
import com.pliskin.model.Credentials;
import com.pliskin.model.Doctor;
import com.pliskin.model.Office;
import com.pliskin.model.Specialization;
import com.pliskin.repository.DoctorRepository;
import com.pliskin.repository.SpecializationRepository;
import com.pliskin.service.OfficeService;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;

import static org.mockito.Matchers.any;
import static org.mockito.Matchers.anyLong;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

/**
 * Created by aleksandrpliskin on 23.04.16.
 */
public class DoctorServiceImplTest {

    private static DoctorServiceImpl doctorService;
    private static Doctor doctor;
    private static Doctor doctorCur;
    private static Office office;
    private static List<Doctor> doctors;
    private static List<Doctor> doctorsByOffice;
    private static Credentials credentials;
    private static Specialization specialization;

    @BeforeClass
    public static void setUp() {
        doctorService = new DoctorServiceImpl();
        doctor = new Doctor();
        doctorCur = new Doctor();
        office = new Office();
        credentials = new Credentials();
        doctors = new ArrayList<>();
        specialization = new Specialization();
        doctorsByOffice = new ArrayList<>();
        doctorService.doctorRepository = mock(DoctorRepository.class);
        doctorService.specializationRepository = mock(SpecializationRepository.class);
        doctorService.officeService = mock(OfficeService.class);
        doctorService.doctorFunction = (Function<DoctorCreationForm, Doctor>) mock(Function.class);
        when(doctorService.doctorFunction.apply(any(DoctorCreationForm.class))).thenReturn(doctor);
        when(doctorService.doctorRepository.save(doctor)).thenReturn(doctor);
        when(doctorService.officeService.getOffice(anyLong())).thenReturn(office);
        when(doctorService.doctorRepository.findOne(2L)).thenReturn(null);
        when(doctorService.doctorRepository.findOne(1L)).thenReturn(doctor);
        when(doctorService.doctorRepository.findOneByFio("fio")).thenReturn(doctor);
        when(doctorService.doctorRepository.findOneByFio("abra-kadabra")).thenReturn(null);
        when(doctorService.doctorRepository.findAllByOffice(office)).thenReturn(doctors);
        when(doctorService.doctorRepository.findAllByOffice(office)).thenReturn(doctorsByOffice);
        when(doctorService.doctorRepository.findByCredentials(any(Credentials.class))).thenReturn(doctorCur);
        when(doctorService.doctorRepository.findByOfficeAndSpecialization(office, specialization)).thenReturn(doctors);
        when(doctorService.specializationRepository.findByName("dentist")).thenReturn(specialization);
    }

    @Test
    public void CreateDoctorShouldCreateCorrectDoctor() {
        doctorService.createDoctor(new DoctorCreationForm());
    }

    @Test
    public void getAllByOfficeIdShouldReturnCorrectDoctors() {
        Assert.assertEquals(doctorService.getAllByOfficeId(1L), doctors);
    }

    @Test
    public void getDoctorShouldReturnCorrectDoctor() {
        Assert.assertEquals(doctorService.getDoctor(1L), doctor);
    }

    @Test(expected = NoSuchDoctorException.class)
    public void getDoctorShouldThrowNoSuchDoctorExceptionIfNoDoctorWithSuchId() {
        doctorService.getDoctor(2L);
    }

    @Test
    public void getDoctorsByOfficeShouldReturnCorrectDoctors() {
        Assert.assertEquals(doctorService.getDoctorsByOffice(office), doctorsByOffice);
    }

    @Test
    public void getDoctorByIdShouldReturnCorrectDoctor() {
        Assert.assertEquals(doctor, doctorService.getDoctor(1L));
    }

    @Test(expected = NoSuchDoctorException.class)
    public void getDoctorByIDShouldThrowExceptionIfNoSuchDoctor() {
        doctorService.getDoctor(2L);
    }

    @Test
    public void getDoctorByFioShouldReturnCorrectDoctor() {
        Assert.assertEquals(doctor, doctorService.getDoctor("fio"));
    }

    @Test(expected = NoSuchDoctorException.class)
    public void getDoctorByFioShouldThrowExceptionIfNoSuchDoctor() {
        doctorService.getDoctor("abra-kadabra");
    }

    @Test
    public void getDoctorsByOfficeAndSpecializationShouldReturnCorrectDoctors() {
        Assert.assertEquals(doctors, doctorService.getDoctorsByOfficeAndSpecialization(office, "dentist"));
    }

    @Test(expected = NullPointerException.class)
    public void getDoctorShouldWork() {
        doctorService.getDoctor();
    }

}
