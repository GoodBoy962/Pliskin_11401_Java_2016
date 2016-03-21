package com.pliskin.exceptions;

/**
 * Created by aleksandrpliskin on 22.03.16.
 */
public class NotCorrectCommandException extends RuntimeException {

    public NotCorrectCommandException() {
        super("неправильная команда");
    }

}
