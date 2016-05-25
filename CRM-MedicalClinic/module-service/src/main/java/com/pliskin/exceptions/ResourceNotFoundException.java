package com.pliskin.exceptions;

/**
 * Created by aleksandrpliskin on 07.04.16.
 */
public class ResourceNotFoundException extends RuntimeException {

    public ResourceNotFoundException(String message) {
        super(message);
    }
}
