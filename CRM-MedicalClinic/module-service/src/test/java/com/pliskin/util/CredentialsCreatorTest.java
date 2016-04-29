package com.pliskin.util;

import com.pliskin.model.Credentials;
import com.pliskin.model.enums.Role;
import com.pliskin.repository.CredentialsRepository;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import static org.mockito.Matchers.anyString;
import static org.mockito.Mockito.*;

/**
 * Created by aleksandrpliskin on 21.04.16.
 */
public class CredentialsCreatorTest {

    private static CredentialsCreator credentialsCreator;
    private static Credentials credentials;

    @Before
    public void setUp() {
        credentialsCreator = new CredentialsCreator();
        credentialsCreator.encoder = mock(BCryptPasswordEncoder.class);
        when(credentialsCreator.encoder.encode(anyString())).thenReturn("654321");
        credentialsCreator.credentialsRepository = mock(CredentialsRepository.class);
        when(credentialsCreator.credentialsRepository.save(any(Credentials.class))).thenReturn(credentials);
        credentials = credentialsCreator.createAndSaveCredentialsForUser("123456", "login", "email@sample.com", Role.ROLE_PATIENT);
    }

    @Test
    public void credentialsShouldBeRight() {
        Assert.assertEquals(credentials.getEmail(), "email@sample.com");
        Assert.assertEquals(credentials.getLogin(), "login");
        Assert.assertEquals(credentials.getPassword(), "654321");
        Assert.assertEquals(credentials.getRole(), Role.ROLE_PATIENT);
    }

}
