package com.pliskin.api.service;

/**
 * Created by aleksandrpliskin on 25.05.16.
 */
public interface ApiAuthService {

    boolean auth(String password, String login);

}
