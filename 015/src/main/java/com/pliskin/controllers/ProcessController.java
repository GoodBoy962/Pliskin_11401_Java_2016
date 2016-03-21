package com.pliskin.controllers;

import com.pliskin.service.TextAnalyzeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

/**
 * Created by aleksandrpliskin on 21.03.16.
 * 015
 */
@Controller
@RequestMapping(value = "/process")
public class ProcessController {

    @Autowired
    TextAnalyzeService service;

    @RequestMapping(value = "", method = RequestMethod.GET)
    public String getProcessPage() {
        return "process";
    }

    @RequestMapping(value = "", method = RequestMethod.POST)
    public String textAnalysing(@RequestParam("text") String text,
                                @RequestParam("operation") String operation,
                                RedirectAttributes redirectAttributes) {
        redirectAttributes.addAttribute("result", service.analyzeText(text, operation));
        return "redirect:/result";
    }


}
