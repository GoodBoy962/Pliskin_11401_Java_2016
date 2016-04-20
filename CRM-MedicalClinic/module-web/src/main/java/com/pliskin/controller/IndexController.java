package com.pliskin.controller;

import com.pliskin.service.ProposalService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * Created by aleksandrpliskin on 01.04.16.
 */
@Controller
public class IndexController {

    @RequestMapping(value = "")
    public String getIndexPage() {
        return "/index";
    }

    @Autowired
    ProposalService proposalService;

    @RequestMapping(value = "/proposal", method = RequestMethod.POST)
    public String createProposal(@RequestParam("fio") String fio,
                                 @RequestParam("email") String email,
                                 @RequestParam("message") String message
    ) {
        proposalService.createProposal(fio, email, message);
        return "/index";
    }

}
