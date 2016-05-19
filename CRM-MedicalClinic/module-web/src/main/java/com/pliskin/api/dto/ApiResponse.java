package com.pliskin.api.dto;

import java.util.List;

/**
 * Created by aleksandrpliskin on 19.05.16.
 */
public class ApiResponse<T> {

    private T data;
    private List<String> errors;

    public ApiResponse(T data, List<String> errors) {
        this.data = data;
        this.errors = errors;
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
}
