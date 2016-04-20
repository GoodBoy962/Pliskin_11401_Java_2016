package com.pliskin.service;

import com.pliskin.forms.SpecializationCreationForm;
import com.pliskin.model.Specialization;

import java.util.List;
import java.util.Set;

/**
 * Created by aleksandrpliskin on 10.04.16.
 */
public interface SpecializationService {

    public List<Specialization> getAllSpecializations();

    Set<Specialization> getSpecializationsOfDoctorsInOfficeByCityAndAddress(String city, String address);

    void createNew(SpecializationCreationForm form);
}
