package com.pliskin.web.service;

import com.pliskin.web.dto.ApiResponse;
import org.springframework.http.ResponseEntity;

/**
 * Created by aleksandrpliskin on 22.05.16.
 */
public interface ApiService {

    ApiResponse signIn(String login, String password);

    ApiResponse profile();

}
