package com.pliskin.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * Created by aleksandrpliskin on 13.04.16.
 */
@Controller
public class AccessDeniedController {

    @RequestMapping(value = "/403", method = RequestMethod.GET)
    public String getAccessDeniedPage() {
        return "403";
    }

}
