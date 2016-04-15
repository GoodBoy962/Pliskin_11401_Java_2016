package com.pliskin.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Created by aleksandrpliskin on 01.04.16.
 */
@Controller
public class IndexController {

    @RequestMapping(value = "")
    public String getIndexPage() {
        return "/index";
    }

}
