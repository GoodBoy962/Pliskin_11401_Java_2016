package com.pliskin.util.transformers;

import com.pliskin.forms.OfficeAdminCreationForm;
import com.pliskin.model.Admin;
import com.pliskin.model.Credentials;
import com.pliskin.repository.CredentialsRepository;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.stubbing.Answer;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import static org.mockito.Matchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

/**
 * Created by aleksandrpliskin on 24.05.16.
 */
public class OfficeAdminFormToAdminTransformerTest {

    private OfficeAdminCreationForm form;
    private OfficeAdminFormToAdminTransformer officeAdminFormToAdminTransformer;

    @Before
    public void setUp() {
        officeAdminFormToAdminTransformer = new OfficeAdminFormToAdminTransformer();
        officeAdminFormToAdminTransformer.credentialsRepository = mock(CredentialsRepository.class);
        OfficeAdminFormToAdminTransformer.encoder = mock(BCryptPasswordEncoder.class);
        form = new OfficeAdminCreationForm();
        form.setLogin("login");
        form.setPassword("1234");
        form.setEmail("email");
        form.setSurname("surname");
        form.setName("name");
        form.setLastname("lastname");
        when(officeAdminFormToAdminTransformer.credentialsRepository.save(any(Credentials.class))).thenAnswer(
                (Answer<Credentials>) invocation -> {
                    Object[] args = invocation.getArguments();
                    return (Credentials) args[0];
                });
        when(OfficeAdminFormToAdminTransformer.encoder.encode(form.getPassword())).thenReturn("1234");
    }

    @Test
    public void applyShouldTransformFormToAdmin() {
        Admin admin = officeAdminFormToAdminTransformer.apply(form);
        Assert.assertEquals(admin.getCredentials().getPassword(), "1234");
    }

}
