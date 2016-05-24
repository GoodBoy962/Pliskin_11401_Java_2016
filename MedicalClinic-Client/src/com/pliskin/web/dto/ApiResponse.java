package com.pliskin.web.dto;

import org.springframework.http.HttpStatus;
import com.pliskin.model.Credentials;

import java.util.List;

/**
 * Created by aleksandrpliskin on 20.05.16.
 */
public class ApiResponse<T> {

    private T data;
    private List<String> errors;
    private HttpStatus status;
    private Credentials credentials;

    public ApiResponse(T data, List<String> errors, HttpStatus status) {
        this.data = data;
        this.errors = errors;
        this.status = status;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    public List<String> getErrors() {
        return errors;
    }

    public void setErrors(List<String> errors) {
        this.errors = errors;
    }

    public HttpStatus getStatus() {
        return status;
    }

    public void setStatus(HttpStatus status) {
        this.status = status;
    }

    public Credentials getCredentials() {
        return credentials;
    }

    public void setCredentials(Credentials credentials) {
        this.credentials = credentials;
    }
}
