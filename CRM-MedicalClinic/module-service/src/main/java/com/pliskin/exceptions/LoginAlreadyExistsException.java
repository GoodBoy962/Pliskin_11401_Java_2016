package com.pliskin.exceptions;

/**
 * Created by aleksandrpliskin on 07.04.16.
 */
public class LoginAlreadyExistsException extends AlreadyExistsException {

    public LoginAlreadyExistsException() {
        super("такой логин уже существует");
    }

}
