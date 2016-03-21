package com.pliskin.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by aleksandrpliskin on 21.03.16.
 * 014
 */
@Controller
public class IndexController {

    @RequestMapping(value = "/")
    public String getIndexPage() {
        return "index";
    }


}
