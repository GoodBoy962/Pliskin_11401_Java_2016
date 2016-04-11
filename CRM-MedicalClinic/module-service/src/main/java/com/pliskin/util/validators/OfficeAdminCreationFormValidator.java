package com.pliskin.util.validators;

import com.pliskin.forms.OfficeAdminCreationForm;
import com.pliskin.repository.AdminRepository;
import com.pliskin.repository.CredentialsRepository;
import com.pliskin.repository.OfficeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

/**
 * Created by aleksandrpliskin on 07.04.16.
 */
@Component
@Qualifier(value = "officeAdminForm")
public class OfficeAdminCreationFormValidator implements Validator {

    @Autowired
    OfficeRepository officeRepository;

    @Autowired
    AdminRepository adminRepository;

    @Autowired
    CredentialsRepository credentialsRepository;

    @Override
    public boolean supports(Class<?> aClass) {
        return OfficeAdminCreationForm.class.equals(aClass);
    }

    @Override
    public void validate(Object o, Errors errors) {
        OfficeAdminCreationForm form = (OfficeAdminCreationForm) o;
        if (officeRepository.findByAddress(form.getAddress()) != null) {
            errors.rejectValue("address", "", "офис с таким адресом уже существует");
        }
        if (credentialsRepository.findOneByLogin(form.getLogin()) != null) {
            errors.rejectValue("login", "", "пользователь с таким логином уже существует");
        }
        if (credentialsRepository.findOneByEmail(form.getEmail()) != null) {
            errors.rejectValue("email", "", "пользователь с таким email уже существует");
        }
    }
}
