package com.pliskin.service;

import com.pliskin.model.MedicalClinic;
import com.pliskin.model.Office;

import java.util.List;

/**
 * Created by aleksandrpliskin on 06.04.16.
 */
public interface OfficeService {

    List<Office> getMedClinicOffices(MedicalClinic medicalClinic);

}
