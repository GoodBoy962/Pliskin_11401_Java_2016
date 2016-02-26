package com.pliskin;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class Main {

    public static void main(String[] args) {

        ApplicationContext context = new ClassPathXmlApplicationContext("resources/root-config.xml");
        Student student1 = (Student) context.getBean("alex");
        Student student2 = context.getBean()

    }

}
