package com.pliskin.service.impl;

import com.pliskin.forms.SpecializationCreationForm;
import com.pliskin.model.Doctor;
import com.pliskin.model.Specialization;
import com.pliskin.repository.SpecializationRepository;
import com.pliskin.service.DoctorService;
import com.pliskin.service.OfficeService;
import com.pliskin.service.SpecializationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * Created by aleksandrpliskin on 10.04.16.
 */
@Service
public class SpecializationServiceImpl implements SpecializationService {

    @Autowired
    SpecializationRepository specializationRepository;

    @Autowired
    OfficeService officeService;

    @Autowired
    DoctorService doctorService;

    @Override
    public List<Specialization> getAllSpecializations() {
        return specializationRepository.findAll();
    }

    @Override
    public Set<Specialization> getSpecializationsOfDoctorsInOfficeByCityLikeAndAddress(String city, String address) {
        return doctorService.getDoctorsByOffice(
                officeService.getOfficeByCityLikeAndAddress(city, address)).
                stream().map(Doctor::getSpecialization).
                collect(Collectors.toSet());
    }

    @Override
    public Set<Specialization> getSpecializationsOfDoctorsInOfficeByCityAndAddress(String city, String address) {
        return doctorService.getDoctorsByOffice(
                officeService.getOfficeByCityAndAddress(city, address)).
                stream().map(Doctor::getSpecialization).
                collect(Collectors.toSet());
    }

    @Override
    public void createNew(SpecializationCreationForm form) {
        Specialization specialization = new Specialization();
        specialization.setName(form.getName());
        specializationRepository.save(specialization);
    }
}
