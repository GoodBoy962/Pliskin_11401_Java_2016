package com.pliskin.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Created by aleksandrpliskin on 06.04.16.
 */
@Controller
@RequestMapping(value = "/system")
public class SystemAdminController {

    @RequestMapping(value = "")
    public String getSystemAdminIndex() {
        return "system";
    }

}
