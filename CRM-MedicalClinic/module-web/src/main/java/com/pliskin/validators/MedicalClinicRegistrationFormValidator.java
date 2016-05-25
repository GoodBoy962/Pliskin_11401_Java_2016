package com.pliskin.validators;


import com.pliskin.forms.MedicalClinicRegistrationForm;
import com.pliskin.repository.MedicalClinicRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

/**
 * Created by aleksandrpliskin on 12.04.16.
 */
@Component
@Qualifier(value = "mcForm")
public class MedicalClinicRegistrationFormValidator implements Validator {

    @Autowired
    MedicalClinicRepository medicalClinicRepository;

    @Override
    public boolean supports(Class<?> aClass) {
        return MedicalClinicRegistrationForm.class.equals(aClass);
    }

    @Override
    public void validate(Object o, Errors errors) {
        MedicalClinicRegistrationForm form = (MedicalClinicRegistrationForm) o;
        if (form.getName() == null || medicalClinicRepository.findByName(form.getName()) != null) {
            errors.rejectValue("name", "", "такое название мед клиники уже существует");
        }
    }
}
