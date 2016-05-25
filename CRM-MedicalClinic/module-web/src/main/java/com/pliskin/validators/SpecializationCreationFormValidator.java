package com.pliskin.validators;

import com.pliskin.forms.SpecializationCreationForm;
import com.pliskin.repository.SpecializationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

/**
 * Created by aleksandrpliskin on 21.04.16.
 */
@Component
@Qualifier(value = "specializationValidator")
public class SpecializationCreationFormValidator implements Validator {

    @Autowired
    SpecializationRepository specializationRepository;

    @Override
    public boolean supports(Class<?> aClass) {
        return SpecializationCreationForm.class.equals(aClass);
    }

    @Override
    public void validate(Object o, Errors errors) {
        SpecializationCreationForm form = (SpecializationCreationForm) o;
        if (form.getName() == null || specializationRepository.findByName(form.getName()) != null) {
            errors.rejectValue("name", "", "такая специализация уже существует");
        }
    }
}
