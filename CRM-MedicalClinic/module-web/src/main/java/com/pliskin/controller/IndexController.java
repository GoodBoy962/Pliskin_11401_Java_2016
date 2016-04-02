package com.pliskin.controller;

import com.pliskin.service.SimpleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Created by aleksandrpliskin on 01.04.16.
 */
@Controller
public class IndexController {

    @Autowired
    SimpleService simpleService;

    @RequestMapping(value = "")
    public String getIndexPage(Model model) {
        model.addAttribute("hello", simpleService.getSimpleHello());
        return "index";
    }

}
