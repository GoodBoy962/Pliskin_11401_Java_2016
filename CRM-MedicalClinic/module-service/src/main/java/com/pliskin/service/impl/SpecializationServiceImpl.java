package com.pliskin.service.impl;

import com.pliskin.model.Specialization;
import com.pliskin.repository.SpecializationRepository;
import com.pliskin.service.SpecializationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by aleksandrpliskin on 10.04.16.
 */
@Service
public class SpecializationServiceImpl implements SpecializationService {

    @Autowired
    SpecializationRepository specializationRepository;

    @Override
    public List<Specialization> getAllSpecializations() {
        return specializationRepository.findAll();
    }
}
