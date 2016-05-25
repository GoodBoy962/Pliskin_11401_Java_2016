package com.pliskin.exceptions;

/**
 * Created by aleksandrpliskin on 07.04.16.
 */
public class AddressAlreadyExistsException extends RuntimeException {

    public AddressAlreadyExistsException() {
        super("офис с таким адресом уже существует");
    }
}
