package com.pliskin.api.controller;

import com.pliskin.api.dto.ApiResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.List;

/**
 * Created by aleksandrpliskin on 19.05.16.
 */
public class BaseApiController {

    <T> ResponseEntity<ApiResponse<T>> createGoodResponse(T data) {
        ApiResponse<T> response = new ApiResponse<>(data, null);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    <T> ResponseEntity<ApiResponse<T>> createBadResponse(List<String> errors, HttpStatus status) {
        ApiResponse<T> response = new ApiResponse<>(null, errors);
        return new ResponseEntity<>(response, status);
    }

}
