package com.pliskin.impl;


import com.pliskin.interfaces.Audience;
import com.pliskin.interfaces.Hero;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class People implements Audience {

    private boolean isReady;

    @Value("we want fight")
    private String words;

    @Autowired
    @Qualifier("wolverine")
    private Hero hero;

    @Value("20")
    private int number;

    public People(String words, Hero hero, Integer number) {
        this.words = words;
        this.hero = hero;
        this.number = number;
        isReady = true;
    }

    public People() {
        this.isReady = true;
    }


    @Override
    public boolean isReady() {
        return isReady;
    }

    @Override
    public void say(String words) {
        System.out.println(words);
    }

    @Override
    public void goAway() {
        System.out.println("Good Fight");
    }

    public Hero getHero() {
        return hero;
    }

    public int getNumber() {
        return number;
    }

    public String getWords() {
        return words;
    }

    public void setHero(Hero hero) {
        this.hero = hero;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public void setWords(String words) {
        this.words = words;
    }
}
