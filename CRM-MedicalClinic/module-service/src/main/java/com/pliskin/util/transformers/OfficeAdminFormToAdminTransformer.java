package com.pliskin.util.transformers;

import com.pliskin.forms.OfficeAdminCreationForm;
import com.pliskin.model.Admin;
import com.pliskin.model.Credentials;
import com.pliskin.model.enums.Role;
import com.pliskin.repository.CredentialsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.function.Function;

/**
 * Created by aleksandrpliskin on 10.04.16.
 */
@Component
public class OfficeAdminFormToAdminTransformer implements Function<OfficeAdminCreationForm, Admin> {

    @Autowired
    CredentialsRepository credentialsRepository;

    static BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();

    @Override
    public Admin apply(OfficeAdminCreationForm form) {
        Admin admin = new Admin();
        Credentials credentials = new Credentials();
        credentials.setEmail(form.getEmail());
        credentials.setPassword(encoder.encode(form.getPassword()));
        credentials.setRole(Role.ROLE_ADMIN);
        credentials.setLogin(form.getLogin());
        credentialsRepository.save(credentials);
        admin.setCredentials(credentials);
        String fio = form.getName() + " " + form.getSurname();
        if (form.getLastname().length() > 0) {
            fio += " " + form.getLastname();
        }
        admin.setFio(fio);
        return admin;
    }
}
