package com.pliskin.util.transformers;

import com.pliskin.forms.DoctorCreationForm;
import com.pliskin.model.*;
import com.pliskin.model.enums.Role;
import com.pliskin.repository.AdminRepository;
import com.pliskin.repository.OfficeRepository;
import com.pliskin.repository.SpecializationRepository;
import com.pliskin.util.CredentialsCreator;
import com.pliskin.util.SecurityUtils;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import static org.mockito.Matchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

/**
 * Created by aleksandrpliskin on 21.04.16.
 */
public class DoctorCreationFormToDoctorTransformerTest {

    private DoctorCreationFormToDoctorTransformer transformer;
    private Specialization specialization;
    private Admin admin;
    private Office office;
    private Credentials credentials;
    private DoctorCreationForm form;

    @Before
    public void setUp() {
        transformer = new DoctorCreationFormToDoctorTransformer();
        transformer.adminRepository = mock(AdminRepository.class);
        transformer.specializationRepository = mock(SpecializationRepository.class);
        transformer.officeRepository = mock(OfficeRepository.class);
        transformer.credentialsCreator = mock(CredentialsCreator.class);
        when(transformer.adminRepository.findOneByCredentials(any(Credentials.class))).thenReturn(admin);
        when(transformer.specializationRepository.findByName(any(String.class))).thenReturn(specialization);
        when(transformer.credentialsCreator.createAndSaveCredentialsForUser("123456", "login", "login@sample.com", Role.ROLE_ADMIN))
                .thenReturn(credentials);
//        when(SecurityUtils.getCurrentUser()).thenReturn(credentials);
    }

    @Before
    public void setUpForm() {
        form = new DoctorCreationForm();
        form.setName("Alex");
        form.setPassword("123456");
        form.setLogin("login");
        form.setEmail("login@sample.com");
        form.setBirthDay("04-09-1996");
        form.setEmploymentDate("20-01-2014");
        form.setInceptionDate("20-01-2014");
        form.setSurname("Pliskin");
        form.setSpecialization("dentist");
        form.setLastname("Markovich");
    }

    @Before
    public void setUpCredentials() {
        credentials = new Credentials();
        credentials.setEmail("login@sample.com");
        credentials.setLogin("login");
        credentials.setPassword("123456");
        credentials.setRole(Role.ROLE_ADMIN);
    }

    @Before
    public void setUpSpecialization() {
        specialization = new Specialization();
        specialization.setName("dentist");
    }

    @Before
    public void setUpAdmin() {
        admin = new Admin();
        admin.setOffice(office);
    }

    @Before
    public void setUpOffice() {
        office = new Office();
    }

    @Test
    public void transformerShouldCreateCorrectDoctor() {
//        Doctor doctor = transformer.apply(form);
//        Assert.assertEquals(doctor.getFio(), form.getName() + " " + form.getSurname() + " " + form.getLastname());
    }


}
