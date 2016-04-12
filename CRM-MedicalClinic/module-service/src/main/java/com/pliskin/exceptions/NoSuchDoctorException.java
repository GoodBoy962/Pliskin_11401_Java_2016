package com.pliskin.exceptions;

/**
 * Created by aleksandrpliskin on 13.04.16.
 */
public class NoSuchDoctorException extends ResourceNotFoundException {
    public NoSuchDoctorException() {
        super("нет такого доктора");
    }
}
