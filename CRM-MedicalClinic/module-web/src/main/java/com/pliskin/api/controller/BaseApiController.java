package com.pliskin.api.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

/**
 * Created by aleksandrpliskin on 19.05.16.
 */
public class BaseApiController {

    <T> ResponseEntity<T> createGoodResponse(T data) {
        return new ResponseEntity<>(data, HttpStatus.OK);
    }

    <T> ResponseEntity<T> createBadResponse(HttpStatus status) {
        return new ResponseEntity<>(status);
    }

}
