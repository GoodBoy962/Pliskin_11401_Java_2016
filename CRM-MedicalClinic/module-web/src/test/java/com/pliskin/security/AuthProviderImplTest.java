package com.pliskin.security;

import com.pliskin.model.Credentials;
import com.pliskin.model.enums.Role;
import com.pliskin.repository.CredentialsRepository;
import org.junit.BeforeClass;
import org.junit.Test;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

/**
 * Created by aleksandrpliskin on 21.04.16.
 */
public class AuthProviderImplTest {

    private static AuthProviderImpl authProvider;
    private static Authentication authentication, authenticationBad, authenticationBad2;
    private static Credentials credentials;

    @BeforeClass
    public static void setUp() {
        credentials = new Credentials();
        credentials.setPassword("1234");
        credentials.setLogin("login");
        credentials.setRole(Role.ROLE_ADMIN);
        authProvider = new AuthProviderImpl();
        authProvider.encoder = mock(BCryptPasswordEncoder.class);
        authProvider.credentialsRepository = mock(CredentialsRepository.class);
        authentication = mock(Authentication.class);
        authenticationBad = mock(Authentication.class);
        authenticationBad2 = mock(Authentication.class);
        when(authentication.getName()).thenReturn("login");
        when(authenticationBad.getName()).thenReturn("no login");
        when(authenticationBad2.getName()).thenReturn("login");
        when(authentication.getCredentials()).thenReturn("1234");
        when(authenticationBad2.getCredentials()).thenReturn("4321");
        when(authProvider.credentialsRepository.findOneByLogin("login")).thenReturn(credentials);
        when(authProvider.encoder.matches("1234", "1234")).thenReturn(true);
        when(authProvider.encoder.matches("1234", "4321")).thenReturn(true);
    }

    @Test(timeout = 30L)
    public void authenticateShouldWorkWithCorrectCredentials() {
        authProvider.authenticate(authentication);
    }

    @Test(expected = UsernameNotFoundException.class)
    public void authenticationShouldThrowExceptionIfNoUserWithSuchLogin() {
        authProvider.authenticate(authenticationBad);
    }

    @Test(expected = BadCredentialsException.class)
    public void authenticationShouldThrowExceptionIfPasswordIncorrect() {
        authProvider.authenticate(authenticationBad2);
    }

    @Test(timeout = 20L)
    public void supportsShouldWorkGood() {
        authProvider.supports(UsernamePasswordAuthenticationToken.class);
    }


}
