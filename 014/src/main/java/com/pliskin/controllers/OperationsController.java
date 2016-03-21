package com.pliskin.controllers;

import com.pliskin.services.OperationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by aleksandrpliskin on 21.03.16.
 * 014
 */
@Controller
public class OperationsController {

    @Autowired
    OperationService operationService;

    @RequestMapping("/getdate")
    public String getDatePage(Model model) {
        DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
        Date date = new Date();
        model.addAttribute("datetime", dateFormat.format(date));
        return "getdate";
    }

    @RequestMapping(value = "/{op}/{num1}/{num2}")
    public String getOperationPage(@PathVariable("op") String op,
                                   @PathVariable("num1") String num1,
                                   @PathVariable("num2") String num2,
                                   Model model) {
        model.addAttribute("result", operationService.getResult(op, num1, num2));
        return "result";
    }
}
