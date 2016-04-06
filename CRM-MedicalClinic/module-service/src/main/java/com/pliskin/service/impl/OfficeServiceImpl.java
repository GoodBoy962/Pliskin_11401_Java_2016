package com.pliskin.service.impl;

import com.pliskin.model.MedicalClinic;
import com.pliskin.model.Office;
import com.pliskin.repository.OfficeRepository;
import com.pliskin.service.OfficeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by aleksandrpliskin on 06.04.16.
 */
@Service
public class OfficeServiceImpl implements OfficeService{

    @Autowired
    OfficeRepository officeRepository;

    @Override
    public List<Office> getMedClinicOffices(MedicalClinic medicalClinic) {
        return officeRepository.findByMedicalClinic(medicalClinic);
    }
}
