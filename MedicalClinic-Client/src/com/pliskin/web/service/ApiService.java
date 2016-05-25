package com.pliskin.web.service;

import com.pliskin.model.Patient;
import org.springframework.http.ResponseEntity;

/**
 * Created by aleksandrpliskin on 22.05.16.
 */
public interface ApiService {

    ResponseEntity<Boolean> signIn(String login, String password);

    ResponseEntity<Patient> home();

    ResponseEntity getDates(String city, String specialization);
}
