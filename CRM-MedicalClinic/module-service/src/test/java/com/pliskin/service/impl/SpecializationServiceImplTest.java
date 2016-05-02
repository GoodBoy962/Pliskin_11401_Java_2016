package com.pliskin.service.impl;

import com.pliskin.forms.SpecializationCreationForm;
import com.pliskin.model.Doctor;
import com.pliskin.model.Office;
import com.pliskin.model.Specialization;
import com.pliskin.repository.SpecializationRepository;
import com.pliskin.service.DoctorService;
import com.pliskin.service.OfficeService;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;
import org.mockito.stubbing.Answer;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import static org.mockito.Matchers.any;
import static org.mockito.Matchers.anyString;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

/**
 * Created by aleksandrpliskin on 23.04.16.
 */
public class SpecializationServiceImplTest {

    private static SpecializationServiceImpl specializationService;
    private static List<Specialization> specializations;
    private static Set<Specialization> specializationSet;
    private static Office office;
    private static List<Doctor> doctors;

    @BeforeClass
    public static void setUp() {
        office = new Office();
        doctors = new ArrayList<>();
        specializationSet = new HashSet<>();
        specializations = new ArrayList<>();
        specializationService = new SpecializationServiceImpl();
        specializationService.specializationRepository = mock(SpecializationRepository.class);
        specializationService.officeService = mock(OfficeService.class);
        specializationService.doctorService = mock(DoctorService.class);
        when(specializationService.getAllSpecializations()).thenReturn(specializations);
        when(specializationService.officeService.getOfficeByCityAndAddress(anyString(), anyString())).thenReturn(office);
        when(specializationService.officeService.getOfficeByCityLikeAndAddress(anyString(), anyString())).thenReturn(office);
        when(specializationService.doctorService.getDoctorsByOffice(office)).thenReturn(doctors);
        when(specializationService.specializationRepository.save(any(Specialization.class))).thenAnswer(
                (Answer<Specialization>) invocation -> {
                    Object[] args = invocation.getArguments();
                    return (Specialization) args[0];
                });
    }

    @Test
    public void getAllSpecializationsShouldReturnCorrectSpecialization() {
        Assert.assertEquals(specializations, specializationService.getAllSpecializations());
    }

    @Test
    public void getSpecializationsOfDoctorsInOfficeByCityAndAddressShouldReturnCorrectSpecializations() {
        Assert.assertEquals(specializationSet, specializationService.getSpecializationsOfDoctorsInOfficeByCityAndAddress("", ""));
    }

    @Test
    public void getSpecializationsOfDoctorsInOfficeByCityLikeAndAddressShouldReturnCorrectSpecializations() {
        Assert.assertEquals(specializationSet, specializationService.getSpecializationsOfDoctorsInOfficeByCityLikeAndAddress("", ""));
    }

    @Test
    public void createNewShouldCreateNewSpecialization() {
        specializationService.createNew(new SpecializationCreationForm());
    }

}
