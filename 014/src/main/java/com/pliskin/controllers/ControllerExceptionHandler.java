package com.pliskin.controllers;

import com.pliskin.exceptions.AddressNotCorrectException;
import com.pliskin.exceptions.NotCorrectCommandException;
import org.springframework.http.HttpStatus;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * Created by aleksandrpliskin on 22.03.16.
 */
@ControllerAdvice
public class ControllerExceptionHandler {
    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ExceptionHandler(NumberFormatException.class)
    public String numberNotInteger(Model model) {
        model.addAttribute("error", "только целые числа");
        return "error-page";
    }

    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ExceptionHandler(NotCorrectCommandException.class)
    public String commandNotGood(Model model) {
        model.addAttribute("error", "команда mult или add");
        return "error-page";
    }

    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ExceptionHandler(AddressNotCorrectException.class)
    public String addressNotCorrect(Model model) {
        model.addAttribute("error", "неправильный адрес для поиска");
        return "error-page";
    }
}
