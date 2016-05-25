package com.pliskin.service;

import com.pliskin.model.Admin;
import com.pliskin.model.Credentials;

/**
 * Created by aleksandrpliskin on 12.04.16.
 */
public interface AdminService {
    Admin findOneByCredentials(Credentials currentUser);
}
