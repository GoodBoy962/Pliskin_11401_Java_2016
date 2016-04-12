package com.pliskin.exceptions;

/**
 * Created by aleksandrpliskin on 13.04.16.
 */
public class NoSuchOfficeException extends ResourceNotFoundException {
    public NoSuchOfficeException() {
        super("нет такого офиса");
    }
}
