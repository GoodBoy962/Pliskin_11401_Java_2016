package com.pliskin.web.service.impl;

import com.pliskin.util.WebUtils;
import com.pliskin.web.dto.ApiResponse;
import com.pliskin.web.service.ApiService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

/**
 * Created by aleksandrpliskin on 22.05.16.
 */
@Service
public class ApiServiceImpl implements ApiService {

    @Autowired
    Environment environment;

    @Autowired
    RestTemplate restTemplate;

    public ApiResponse signIn(String login, String password) {
        MultiValueMap<String, Object> params = new LinkedMultiValueMap<>();
        params.add("login", login);
        params.add("password", password);
        return restTemplate.postForObject(WebUtils.SIGN_IN, params, ApiResponse.class);
    }

    public ApiResponse profile() {
        return null;
    }
}
