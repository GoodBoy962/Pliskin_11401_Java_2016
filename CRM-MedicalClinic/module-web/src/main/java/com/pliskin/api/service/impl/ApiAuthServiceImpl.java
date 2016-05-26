package com.pliskin.api.service.impl;

import com.pliskin.api.service.ApiAuthService;
import com.pliskin.model.Credentials;
import com.pliskin.model.enums.Role;
import com.pliskin.repository.CredentialsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

/**
 * Created by aleksandrpliskin on 25.05.16.
 */
@Service
public class ApiAuthServiceImpl implements ApiAuthService {

    @Autowired
    CredentialsRepository credentialsRepository;

    private static BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();

    public boolean auth(String password, String login) {
        Credentials user = credentialsRepository.findOneByLogin(login);
        return user != null && !(!encoder.matches(password, user.getPassword()) && !password.equals(user.getPassword()))
                && user.getRole().equals(Role.ROLE_PATIENT);
    }

}
