package com.pliskin.web.service.impl;

import com.pliskin.model.Patient;
import com.pliskin.util.WebUtils;
import com.pliskin.web.service.ApiService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

import static com.pliskin.Main.login;

/**
 * Created by aleksandrpliskin on 22.05.16.
 */
@Service
public class ApiServiceImpl implements ApiService {

    @Autowired
    Environment environment;

    @Autowired
    RestTemplate restTemplate;

    public ResponseEntity<Boolean> signIn(String login, String password) {
        MultiValueMap<String, Object> params = new LinkedMultiValueMap<>();
        params.add("login", login);
        params.add("password", password);
        return restTemplate.postForEntity(WebUtils.SIGN_IN, params, Boolean.class);
    }

    @Override
    public ResponseEntity<Patient> home() {
        MultiValueMap<String, Object> params = new LinkedMultiValueMap<>();
        params.add("login", login);
        return restTemplate.postForEntity(WebUtils.PROFILE, params, Patient.class);
    }

    @Override
    public ResponseEntity<String[]> getDates(String city, String specialization) {
//        MultiValueMap<String, Object> params = new LinkedMultiValueMap<>();
//        params.add("city", city);
//        params.add("specialization", specialization);
        return restTemplate.getForEntity(WebUtils.DATES + "/" + city + "/" + specialization, String[].class);

    }

}
