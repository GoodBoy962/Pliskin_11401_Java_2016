package com.pliskin.controller;

import com.pliskin.exceptions.ResourceNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * Created by aleksandrpliskin on 06.04.16.
 */
@ControllerAdvice
public class ExceptionHandlerController {

    @ResponseStatus(value = HttpStatus.NOT_FOUND)
    @ExceptionHandler(ResourceNotFoundException.class)
    public String resourceNorExists(Exception e, Model model) {
        model.addAttribute("error", e.getMessage());
        return "error";
    }

}
