package com.pliskin.service.impl;

import com.pliskin.service.SimpleService;
import org.springframework.stereotype.Service;

/**
 * Created by aleksandrpliskin on 01.04.16.
 */
@Service
public class SimpleServiceImpl implements SimpleService {
    @Override
    public String getSimpleHello() {
        return "Hello";
    }
}
