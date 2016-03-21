package com.pliskin.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Created by aleksandrpliskin on 21.03.16.
 */
@Controller
public class IndexController {

    @RequestMapping("/")
    public String getIndexPage() {
        return "index";
    }

}
