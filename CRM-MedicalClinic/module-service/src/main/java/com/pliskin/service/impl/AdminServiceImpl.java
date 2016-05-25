package com.pliskin.service.impl;

import com.pliskin.model.Admin;
import com.pliskin.model.Credentials;
import com.pliskin.repository.AdminRepository;
import com.pliskin.service.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by aleksandrpliskin on 12.04.16.
 */
@Service
public class AdminServiceImpl implements AdminService {

    @Autowired
    AdminRepository adminRepository;

    @Override
    public Admin findOneByCredentials(Credentials currentUser) {
        return adminRepository.findOneByCredentials(currentUser);
    }
}
