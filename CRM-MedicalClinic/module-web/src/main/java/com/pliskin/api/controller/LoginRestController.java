package com.pliskin.api.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by aleksandrpliskin on 18.05.16.
 */
@RestController
@RequestMapping(value = "/api/v1")
public class LoginRestController extends BaseApiController {

    @RequestMapping("/login")
    public String login(@RequestParam("login") String login, @RequestParam("password") String password) {
        return "";
    }

}
