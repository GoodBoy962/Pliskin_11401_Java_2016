package com.pliskin.services.impl;

import com.pliskin.services.SearchService;
import org.springframework.stereotype.Service;

/**
 * Created by aleksandrpliskin on 22.03.16.
 */
@Service
public class SearchServiceImpl implements SearchService {
    @Override
    public String getFinalAddress(String address) {
        String result;
        switch (address) {
            case "baidu.com":
                result = "baidu.com/s";
                break;
            case "bing.com":
                result = "bing.com/search";
                break;
            case "yahoo.com":
                result = "yahoo.com/search";
                break;
            case "aol.com":
                result = "aol.com/aol/search";
                break;
            default:
                result = "";
        }
        return result;
    }

    @Override
    public String getParam(String param) {
        String result;
        switch (param) {
            case "baidu.com":
                result = "wd";
                break;
            case "bing.com":
                result = "q";
                break;
            case "yahoo.com":
                result = "p";
                break;
            case "aol.com":
                result = "q";
                break;
            default:
                result = "";
        }
        return result;
    }
}
