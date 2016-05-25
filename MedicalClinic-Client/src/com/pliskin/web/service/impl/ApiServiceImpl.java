package com.pliskin.web.service.impl;

import com.pliskin.model.*;
import com.pliskin.util.WebUtils;
import com.pliskin.web.dto.ApiResponse;
import com.pliskin.web.service.ApiService;
import com.sun.javadoc.Doc;
import com.sun.org.apache.xpath.internal.operations.Bool;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

import java.util.Date;
import java.util.List;
import java.util.Map;

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
    public ResponseEntity getDates(String city, String specialization) {
        MultiValueMap<String, Object> params = new LinkedMultiValueMap<>();
        params.add("login", login);
        return restTemplate.postForEntity(WebUtils.PROFILE, params, Object.class);
    }

}
