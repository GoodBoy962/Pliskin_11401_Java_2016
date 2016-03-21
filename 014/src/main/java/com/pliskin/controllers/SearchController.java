package com.pliskin.controllers;

import com.pliskin.exceptions.AddressNotCorrectException;
import com.pliskin.services.SearchService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Created by aleksandrpliskin on 22.03.16.
 * 014
 */
@Controller
public class SearchController {

    @Autowired
    private SearchService searchService;

    @RequestMapping("{address}/search")
    public String getSearch(@PathVariable("address") String address,
                            Model model) {
        if ( "baidu.com".equals(address) || "bing.com".equals(address) || "yahoo.com".equals(address) || "aol.com".equals(address)) {
            model.addAttribute("address", searchService.getFinalAddress(address));
            model.addAttribute("param", searchService.getParam(address));
            return "search-page";
        } else {
            throw new AddressNotCorrectException();
        }
    }

}
