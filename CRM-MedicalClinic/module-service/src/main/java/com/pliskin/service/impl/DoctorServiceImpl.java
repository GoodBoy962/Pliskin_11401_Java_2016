package com.pliskin.service.impl;

import com.pliskin.exceptions.NoSuchDoctorException;
import com.pliskin.forms.DoctorCreationForm;
import com.pliskin.model.Doctor;
import com.pliskin.model.Office;
import com.pliskin.repository.DoctorRepository;
import com.pliskin.repository.SpecializationRepository;
import com.pliskin.service.DoctorService;
import com.pliskin.service.OfficeService;
import com.pliskin.util.SecurityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.function.Function;

/**
 * Created by aleksandrpliskin on 10.04.16.
 */
@Service
public class DoctorServiceImpl implements DoctorService {

    @Autowired
    Function<DoctorCreationForm, Doctor> doctorFunction;

    @Autowired
    DoctorRepository doctorRepository;

    @Autowired
    SpecializationRepository specializationRepository;

    @Autowired
    OfficeService officeService;

    @Transactional
    @Override
    public void createDoctor(DoctorCreationForm form) {
        Doctor doctor = doctorFunction.apply(form);
        doctorRepository.save(doctor);
    }

    @Override
    public List<Doctor> getAllByOfficeId(Long officeId) {
        Office office = officeService.getOffice(officeId);
        return doctorRepository.findAllByOffice(office);
    }

    @Override
    public Doctor getDoctor(Long id) {
        Doctor doctor = doctorRepository.findOne(id);
        if (doctor == null) {
            throw new NoSuchDoctorException();
        }
        return doctor;
    }

    @Override
    public List<Doctor> getDoctorsByOffice(Office office) {
        return doctorRepository.findByOffice(office);
    }

    @Override
    public Doctor getDoctor(String doctorFio) {
        Doctor doctor = doctorRepository.findOneByFio(doctorFio);
        if (doctor == null) {
            throw new NoSuchDoctorException();
        }
        return doctor;
    }

    @Secured("hasRole('ROLE_DOCTOR')")
    @Override
    public Doctor getDoctor() {
        return doctorRepository.findByCredentials(SecurityUtils.getCurrentUser());

    }

    @Override
    public List<Doctor> getDoctorsByOfficeAndSpecialization(Office office, String specialization) {
        return doctorRepository.findByOfficeAndSpecialization(office, specializationRepository.findByName(specialization));
    }
}
