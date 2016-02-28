package com.pliskin.configs;

import com.pliskin.Institute;
import com.pliskin.Lesson;
import com.pliskin.Student;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;

import java.util.ArrayList;
import java.util.List;

@Configuration
public class SpringConfiguration {

    @Bean(name = "kpfu")
    @Scope("singleton")
    Institute kpfu() {
        Institute institute = new Institute();
        institute.setName("kpfu");
        return institute;
    }

    @Bean
    @Scope("singleton")
    Institute mfti() {
        Institute institute = new Institute();
        institute.setName("mfti");
        return institute;
    }

    @Bean()
    Student student1() {
        Student student = new Student();
        student.setName("alex");
        student.setInstitute(this.kpfu());
        List<Lesson> lessons = new ArrayList<Lesson>();
        lessons.add(maths());
        lessons.add(informatics());
        lessons.add(databases());
        student.setLessons(lessons);
        return student;
    }

    @Bean
    Student student2() {
        Student student = new Student();
        student.setName("denis");
        student.setInstitute(this.mfti());
        List<Lesson> lessons = new ArrayList<Lesson>();
        lessons.add(maths());
        lessons.add(physics());
        lessons.add(databases());
        student.setLessons(lessons);
        return student;
    }

    @Bean
    Lesson maths() {
        Lesson lesson = new Lesson();
        lesson.setName("maths");
        return lesson;
    }

    @Bean
    Lesson informatics() {
        Lesson lesson = new Lesson();
        lesson.setName("informatics");
        return lesson;
    }

    @Bean
    Lesson physics() {
        Lesson lesson = new Lesson();
        lesson.setName("physics");
        return lesson;
    }

    @Bean
    Lesson databases() {
        Lesson lesson = new Lesson();
        lesson.setName("databases");
        return lesson;
    }

}
