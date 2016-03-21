package com.pliskin.exceptions;

/**
 * Created by aleksandrpliskin on 22.03.16.
 */
public class AddressNotCorrectException extends RuntimeException {

    public AddressNotCorrectException() {
        super("неправильный адресс для поиска");
    }

}
