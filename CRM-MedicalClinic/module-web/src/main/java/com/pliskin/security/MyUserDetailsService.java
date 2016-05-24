package com.pliskin.security;

import com.pliskin.model.Credentials;
import com.pliskin.model.enums.Role;
import com.pliskin.repository.CredentialsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by aleksandrpliskin on 25.05.16.
 */
public class MyUserDetailsService implements UserDetailsService {

    @Autowired
    CredentialsRepository credentialsRepository;

    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
        Credentials credentials = stringToCredentialsParse(s);
        GrantedAuthority authority = new SimpleGrantedAuthority(credentials.getRole().name());
        List<GrantedAuthority> list = new ArrayList<>();
        list.add(authority);
        return new User(credentials.getLogin(), credentials.getPassword(), list);
    }

    private Credentials stringToCredentialsParse(String s) {
        Role role = Role.valueOf(s.substring(s.indexOf("role=") + 5, s.indexOf(", login")).toUpperCase());
        String login = s.substring(s.indexOf("login=") + 6, s.indexOf(", password") - 1);
        String password = s.substring(s.indexOf("password=") + 9, s.indexOf('}') - 1);
        Credentials credentials = new Credentials();
        credentials.setRole(role);
        credentials.setPassword(password);
        credentials.setLogin(login);
        return credentials;
    }
}
