package com.pliskin.service;

import com.pliskin.forms.MedicalClinicRegistrationForm;
import com.pliskin.model.MedicalClinic;

import java.util.List;

/**
 * Created by aleksandrpliskin on 06.04.16.
 */
public interface MedicalClinicService {

    List<MedicalClinic> getMedClinics();

    MedicalClinic getMedClinic(Long id);

    void createNewMedClinic(MedicalClinicRegistrationForm form);

    MedicalClinic getMedClinic(String name);

}
