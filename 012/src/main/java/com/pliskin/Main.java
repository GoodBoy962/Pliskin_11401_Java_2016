package com.pliskin;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * Created by aleksandrpliskin on 21.03.16.
 * 012
 */
public class Main {
    public static void main(String[] args) {
        ApplicationContext context = new ClassPathXmlApplicationContext("spring-config.xml");
        SimpleClass obj = context.getBean(SimpleClass.class);
        obj.execute("Alexander");
    }
}
