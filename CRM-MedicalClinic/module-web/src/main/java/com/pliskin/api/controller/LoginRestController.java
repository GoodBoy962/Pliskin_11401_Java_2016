package com.pliskin.api.controller;

import com.pliskin.api.service.ApiAuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by aleksandrpliskin on 18.05.16.
 */
@RestController
@RequestMapping(value = "api/v1")
public class LoginRestController extends BaseApiController {

    @Autowired
    ApiAuthService apiAuthService;

    @RequestMapping(value = "/login", method = RequestMethod.POST)
    @ResponseBody
    public ResponseEntity<Boolean> login(String login, String password) {
        if (apiAuthService.auth(password, login)) {
            return new ResponseEntity<>(Boolean.TRUE, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(Boolean.FALSE, HttpStatus.OK);
        }
    }

}
