package com.pliskin.util;

import com.pliskin.model.Credentials;
import com.pliskin.model.enums.Role;
import com.pliskin.repository.CredentialsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

/**
 * Created by aleksandrpliskin on 10.04.16.
 */
@Component
public class CredentialsCreator {

    static BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();

    @Autowired
    CredentialsRepository credentialsRepository;

    public Credentials createAndSaveCredentialsForUser(String password, String login, String email, Role role) {
        Credentials credentials = new Credentials();
        credentials.setPassword(encoder.encode(password));
        credentials.setRole(role);
        credentials.setLogin(login);
        credentials.setEmail(email);
        credentialsRepository.save(credentials);
        return credentials;
    }

}
