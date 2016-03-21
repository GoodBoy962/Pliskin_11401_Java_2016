package com.pliskin.services.impl;

import com.pliskin.exceptions.NotCorrectCommandException;
import com.pliskin.services.OperationService;
import org.springframework.stereotype.Service;

/**
 * Created by aleksandrpliskin on 22.03.16.
 */
@Service
public class OperationServiceImpl implements OperationService {
    @Override
    public String getResult(String op, String num1, String num2) {
        int a, b;
        try {
            a = Integer.parseInt(num1);
            b = Integer.parseInt(num2);
        } catch (NumberFormatException e) {
            throw new NumberFormatException();
        }
        String res;
        switch (op) {
            case "mult":
                res = String.valueOf(a * b);
                break;
            case "add":
                res = String.valueOf(a + b);
                break;
            default:
                throw new NotCorrectCommandException();
        }
        return res;
    }
}
