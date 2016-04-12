package com.pliskin.util.validators;

import com.pliskin.forms.PatientRegistrationForm;
import com.pliskin.repository.CredentialsRepository;
import com.pliskin.repository.SpecializationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by aleksandrpliskin on 13.04.16.
 */
@Component
public class PatientRegistrationFormValidator implements Validator {

    @Autowired
    CredentialsRepository credentialsRepository;

    @Autowired
    SpecializationRepository specializationRepository;

    @Override
    public boolean supports(Class<?> aClass) {
        return PatientRegistrationForm.class.equals(aClass);
    }

    @Override
    public void validate(Object o, Errors errors) {
        PatientRegistrationForm form = (PatientRegistrationForm) o;
        if (credentialsRepository.findOneByLogin(form.getLogin()) != null) {
            errors.rejectValue("login", "", "пользователь с таким логином уже существует");
        }
        if (credentialsRepository.findOneByEmail(form.getEmail()) != null) {
            errors.rejectValue("email", "", "пользователь с таким email уже существует");
        }
        if (!form.getPassword().equals(form.getRepassword())) {
            errors.rejectValue("repassword", "", "пароль не подтвержден");
        }
        SimpleDateFormat format = new SimpleDateFormat("yyyy-mm-dd");
        try {
            Long curTime = format.parse(format.format(new Date())).getTime();
            if (format.parse(form.getBirthDay()).getTime() >= curTime) {
                errors.rejectValue("birthDay", "", "дата дня рождения не может быть больше текущей даты");
            }
        } catch (ParseException e) {
            e.printStackTrace();
        }
    }

}
