package com.pliskin.util;

/**
 * Created by aleksandrpliskin on 22.05.16.
 */
public interface WebUtils {

    String DOMAIN_URL = "http://localhost:8080/api/v1";
    String SIGN_IN_URL = DOMAIN_URL + "/login";
    String PROFILE_URL = DOMAIN_URL + "/patient";
    String APPOINTMENT_DATES_URL = DOMAIN_URL + "/appointment/dates";
    String APPOINTMENT_CREATION_URL = DOMAIN_URL + "/appointment/new";
    String PATIENT_HISTORIES_URL = DOMAIN_URL + "/patient_histories";

}
