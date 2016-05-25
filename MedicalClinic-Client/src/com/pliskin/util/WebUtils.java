package com.pliskin.util;

/**
 * Created by aleksandrpliskin on 22.05.16.
 */
public interface WebUtils {

    String DOMAIN = "http://localhost:8080/api/v1";
    String SIGN_IN = DOMAIN + "/login";
    String PROFILE = DOMAIN + "/patient";
    String DATES = DOMAIN + "/appointment/dates";

}
