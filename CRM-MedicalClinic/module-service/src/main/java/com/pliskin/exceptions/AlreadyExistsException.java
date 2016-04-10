package com.pliskin.exceptions;

/**
 * Created by aleksandrpliskin on 07.04.16.
 */
public class AlreadyExistsException extends RuntimeException {

    public AlreadyExistsException(String message) {
        super(message);
    }
}
