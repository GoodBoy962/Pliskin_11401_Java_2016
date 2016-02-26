package com.pliskin;

public class Lesson {

    public Lesson() {
    }

    public Lesson(String name) {
        this.name = name;
    }

    private String name;

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public static Lesson getLesson() {
        return new Lesson();
    }
}
