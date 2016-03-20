package com.pliskin;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * Created by aleksandrpliskin on 20.03.16.
 */
public class Main {
    public static void main(String[] args) {
        ApplicationContext context = new ClassPathXmlApplicationContext("aop-config.xml");
        Email email = context.getBean(Email.class);
        email.setEmail("aa@al.com");
    }
}
