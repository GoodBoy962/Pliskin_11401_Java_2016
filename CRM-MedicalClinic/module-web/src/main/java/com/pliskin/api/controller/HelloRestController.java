package com.pliskin.api.controller;

import com.pliskin.api.dto.ApiResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by aleksandrpliskin on 18.05.16.
 */
@RequestMapping("/api/v1/hello")
@RestController
public class HelloRestController extends BaseApiController {

    public ResponseEntity<ApiResponse<String>> getInfo() {
        return createGoodResponse("Hello world");
    }
}
