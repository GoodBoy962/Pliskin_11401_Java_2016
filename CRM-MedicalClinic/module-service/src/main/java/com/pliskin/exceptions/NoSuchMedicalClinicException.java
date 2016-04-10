package com.pliskin.exceptions;

/**
 * Created by aleksandrpliskin on 07.04.16.
 */
public class NoSuchMedicalClinicException extends ResourceNotFoundException {

    public NoSuchMedicalClinicException() {
        super("не существуею такой мед клиники");
    }

}
