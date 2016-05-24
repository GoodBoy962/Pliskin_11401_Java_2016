package com.pliskin.api.controller;

import com.pliskin.api.dto.ApiResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Collections;

/**
 * Created by aleksandrpliskin on 18.05.16.
 */
@RestController
@RequestMapping(value = "/api/v1")
public class LoginRestController extends BaseApiController {

    @RequestMapping(value = "/sign_in", method = RequestMethod.POST, produces = "application/json")
    public ApiResponse login(HttpServletRequest request, HttpServletResponse response) {
        return new ApiResponse("good", Collections.emptyList());
    }

}
