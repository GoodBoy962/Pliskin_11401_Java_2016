package com.pliskin;

import java.util.List;

public class Student {

    private String name;

    private Institute institute;
    private List lessons;

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setInstitute(Institute institute) {
        this.institute = institute;
    }

    public Institute getInstitute() {
        return institute;
    }

    public void setLessons(List lessons) {
        this.lessons = lessons;
    }

    public List getLessons() {
        return lessons;
    }
}
