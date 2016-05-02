package com.pliskin.service.impl;

import com.pliskin.exceptions.NoSuchMedicalClinicException;
import com.pliskin.exceptions.NoSuchOfficeException;
import com.pliskin.forms.OfficeAdminCreationForm;
import com.pliskin.model.Admin;
import com.pliskin.model.MedicalClinic;
import com.pliskin.model.Office;
import com.pliskin.repository.AdminRepository;
import com.pliskin.repository.CredentialsRepository;
import com.pliskin.repository.MedicalClinicRepository;
import com.pliskin.repository.OfficeRepository;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;
import org.mockito.stubbing.Answer;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;

import static org.mockito.Matchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

/**
 * Created by aleksandrpliskin on 23.04.16.
 */
public class OfficeServiceImplTest {

    private static OfficeServiceImpl officeService;
    private static List<Office> medClinicOffices;
    private static MedicalClinic medicalClinic;
    private static Office office;

    @BeforeClass
    public static void SetUp() {
        officeService = new OfficeServiceImpl();
        officeService.officeRepository = mock(OfficeRepository.class);
        officeService.medicalClinicRepository = mock(MedicalClinicRepository.class);
        officeService.adminRepository = mock(AdminRepository.class);
        officeService.credentialsRepository = mock(CredentialsRepository.class);
        officeService.adminFunction = (Function<OfficeAdminCreationForm, Admin>) mock(Function.class);
        medClinicOffices = new ArrayList<>();
        medicalClinic = new MedicalClinic();
        office = new Office();
        when(officeService.officeRepository.findByMedicalClinic(medicalClinic)).thenReturn(medClinicOffices);
        when(officeService.officeRepository.findOne(1L)).thenReturn(office);
        when(officeService.officeRepository.findByCity("NY")).thenReturn(medClinicOffices);
        when(officeService.officeRepository.findByCity("%NY%")).thenReturn(medClinicOffices);
        when(officeService.officeRepository.findOneByCityAndAddress("NY", "TS")).thenReturn(office);
        when(officeService.officeRepository.findOneByCityLikeAndAddress("%NY%", "TS")).thenReturn(office);
        when(officeService.medicalClinicRepository.findOne(1L)).thenReturn(medicalClinic);
        when(officeService.adminFunction.apply(any(OfficeAdminCreationForm.class))).thenReturn(new Admin());
        when(officeService.adminRepository.save(any(Admin.class))).thenAnswer(
                (Answer<Admin>) invocation -> {
                    Object[] args = invocation.getArguments();
                    return (Admin) args[0];
                });
    }

    @Test
    public void getMedClinicOfficesShouldReturnCorrectOffices() {
        Assert.assertEquals(medClinicOffices, officeService.getMedClinicOffices(medicalClinic));
    }

    @Test(expected = NoSuchMedicalClinicException.class)
    public void getMedClinicOfficesShouldThrowExceptionIfMedClinicIsNull() {
        officeService.getMedClinicOffices(null);
    }

    @Test
    public void getOfficeByIdShouldReturnCorrectOffice() {
        Assert.assertEquals(officeService.getOffice(1L), office);
    }

    @Test(expected = NoSuchOfficeException.class)
    public void getOfficeByIdShouldThrowExceptionIfNoSuchOfficeId() {
        officeService.getOffice(2L);
    }

    @Test
    public void getOfficeByCityShouldReturnCorrectOffices() {
        Assert.assertEquals(officeService.getOfficesByCity("NY"), medClinicOffices);
    }

    @Test
    public void getOfficeByCityAndAddressShouldReturnCorrectOffice() {
        Assert.assertEquals(officeService.getOfficeByCityAndAddress("NY", "TS"), office);
    }

    @Test
    public void getOfficeByCityLikeAndAddressShouldReturnCorrectOffice() {
        Assert.assertEquals(officeService.getOfficeByCityLikeAndAddress("NY", "TS"), office);
    }

    @Test
    public void createOfficeAdminShouldCreateCorrectOfficeAndAdmin() {
        officeService.createOfficeAndAdmin(new OfficeAdminCreationForm(), 1L);
    }

    @Test(expected = NoSuchMedicalClinicException.class)
    public void createOfficeAdminShouldThrowExceptionIfNoSuchOffice() {
        officeService.createOfficeAndAdmin(new OfficeAdminCreationForm(), 666L);
    }

    @Test(expected = NullPointerException.class)
    public void getOfficeByAdminCredentialsShouldWork() {
        officeService.getOfficeByAdminCredentials();
    }

    @Test
    public void getOfficesLikeCityShouldReturnCorrectOffices() {
        Assert.assertEquals(officeService.getOfficesLikeCity("NY"), medClinicOffices);
    }
}
