package com.pliskin.service.impl;

import com.pliskin.exceptions.NoSuchMedicalClinicException;
import com.pliskin.forms.MedicalClinicRegistrationForm;
import com.pliskin.model.MedicalClinic;
import com.pliskin.repository.AdminRepository;
import com.pliskin.repository.MedicalClinicRepository;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;
import org.mockito.stubbing.Answer;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.Matchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

/**
 * Created by aleksandrpliskin on 23.04.16.
 */
public class MedicalClinicServiceImplTest {

    private static MedicalClinicServiceImpl medicalClinicService;
    private static List<MedicalClinic> medicalClinics;
    private static MedicalClinic medicalClinic;

    @BeforeClass
    public static void setUp() {
        medicalClinic = new MedicalClinic();
        medicalClinics = new ArrayList<>();
        medicalClinicService = new MedicalClinicServiceImpl();
        medicalClinicService.medicalClinicRepository = mock(MedicalClinicRepository.class);
        medicalClinicService.adminRepository = mock(AdminRepository.class);
        when(medicalClinicService.medicalClinicRepository.findAll()).thenReturn(medicalClinics);
        when(medicalClinicService.medicalClinicRepository.findOne(1L)).thenReturn(medicalClinic);
        when(medicalClinicService.medicalClinicRepository.findOne(2L)).thenReturn(null);
        when(medicalClinicService.medicalClinicRepository.save(any(MedicalClinic.class))).thenAnswer((Answer<MedicalClinic>) invocation -> {
            Object[] args = invocation.getArguments();
            return (MedicalClinic) args[0];
        });
        when(medicalClinicService.medicalClinicRepository.findByName("name")).thenReturn(medicalClinic);
        when(medicalClinicService.medicalClinicRepository.findByName("no name")).thenReturn(null);
    }

    @Test
    public void getMedClinicsShouldReturnCorrectMedClinics() {
        Assert.assertEquals(medicalClinicService.getMedClinics(), medicalClinics);
    }

    @Test
    public void getMedClinicByIdShouldReturnCorrectMedClinic() {
        Assert.assertEquals(medicalClinicService.getMedClinic(1L), medicalClinic);
    }

    @Test(expected = NoSuchMedicalClinicException.class)
    public void getMedClinicByIdShouldThrowExceptionIfNoSuchIdForMedClinic() {
        medicalClinicService.getMedClinic(2L);
    }

    @Test
    public void createNewMedClinicShouldCreateCorrectClinic() {
        medicalClinicService.createNewMedClinic(new MedicalClinicRegistrationForm());
    }

    @Test
    public void getMedClinicByNameShouldReturnCorrectMedClinic() {
        Assert.assertEquals(medicalClinicService.getMedClinic("name"), medicalClinic);
    }

    @Test(expected = NoSuchMedicalClinicException.class)
    public void getMedClinicByNameShouldThrowExceptionIfNoMedClinicWithSuchName() {
        medicalClinicService.getMedClinic("no name");
    }

}
