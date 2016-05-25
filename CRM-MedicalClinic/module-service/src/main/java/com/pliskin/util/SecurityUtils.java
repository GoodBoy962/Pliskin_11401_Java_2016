package com.pliskin.util;

import com.pliskin.model.Credentials;
import org.springframework.security.core.context.SecurityContextHolder;

/**
 * Created by aleksandrpliskin on 10.04.16.
 */
public class SecurityUtils {

    public static Credentials getCurrentUser() {
        return ((Credentials) SecurityContextHolder.getContext().getAuthentication().getPrincipal());
    }

}
