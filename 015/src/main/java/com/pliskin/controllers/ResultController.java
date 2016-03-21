package com.pliskin.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Created by aleksandrpliskin on 21.03.16.
 */
@Controller
public class ResultController {

    @RequestMapping("/result")
    public String getResult(Model model, String result) {
        model.addAttribute(result);
        return "result";
    }

}
