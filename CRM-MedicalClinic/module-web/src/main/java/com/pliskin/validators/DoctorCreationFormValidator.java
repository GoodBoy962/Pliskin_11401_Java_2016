package com.pliskin.validators;

import com.pliskin.forms.DoctorCreationForm;
import com.pliskin.repository.CredentialsRepository;
import com.pliskin.repository.SpecializationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by aleksandrpliskin on 10.04.16.
 */
@Component
@Qualifier(value = "doctorForm")
public class DoctorCreationFormValidator implements Validator {

    @Autowired
    CredentialsRepository credentialsRepository;

    @Autowired
    SpecializationRepository specializationRepository;

    @Override
    public boolean supports(Class<?> aClass) {
        return DoctorCreationForm.class.equals(aClass);
    }

    @Override
    public void validate(Object o, Errors errors) {
        DoctorCreationForm form = (DoctorCreationForm) o;
        if (credentialsRepository.findOneByLogin(form.getLogin()) != null) {
            errors.rejectValue("login", "", "пользователь с таким логином уже существует");
        }
        if (credentialsRepository.findOneByEmail(form.getEmail()) != null) {
            errors.rejectValue("email", "", "пользователь с таким email уже существует");
        }
        if (specializationRepository.findByName(form.getSpecialization()) == null) {
            errors.rejectValue("specialization", "", "нет такой специализации");
        }
        SimpleDateFormat format = new SimpleDateFormat("yyyy-mm-dd");
        try {
            Long curTime = format.parse(format.format(new Date())).getTime();
            if (format.parse(form.getBirthDay()).getTime() >= curTime) {
                errors.rejectValue("birthDay", "", "дата дня рождения не может быть больше текущей даты");
            }
            if (format.parse(form.getInceptionDate()).getTime() <= format.parse(form.getBirthDay()).getTime() ||
                    format.parse(form.getInceptionDate()).getTime() > curTime) {
                errors.rejectValue("inceptionDate", "", "дата начала практики не может быть меньше даты рождения или больше текущей даты");
            }
            if (format.parse(form.getEmploymentDate()).getTime() > curTime ||
                    format.parse(form.getEmploymentDate()).getTime() < format.parse(form.getInceptionDate()).getTime() ||
                    format.parse(form.getEmploymentDate()).getTime() < format.parse(form.getBirthDay()).getTime()) {
                errors.rejectValue("employmentDate", "", "дата устройства на работу больше или равна даты начала практики, но меньше текущей даты");
            }
        } catch (ParseException e) {
            e.printStackTrace();
        }
    }
}
