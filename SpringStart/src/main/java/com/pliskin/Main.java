package com.pliskin;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class Main {

    public static void main(String[] args) {

        ApplicationContext context = new ClassPathXmlApplicationContext("root-config.xml");
        Student student1 = (Student) context.getBean("student1");
        Student student2 = (Student) context.getBean("student2");

        Lesson databases = (Lesson) context.getBean("databases");

        System.out.println("\nStudent 1\n");

        for (Object lesson : student1.getLessons()) {
            System.out.println(((Lesson) lesson).getName());
        }
        System.out.println(student1.getName());
        System.out.println(student1.getInstitute().getName());

        System.out.println("\nStudent 2\n");

        for (Object lesson : student2.getLessons()) {
            System.out.println(((Lesson) lesson).getName());
        }
        System.out.println(student2.getName());
        System.out.println(student2.getInstitute().getName());
    }

}
