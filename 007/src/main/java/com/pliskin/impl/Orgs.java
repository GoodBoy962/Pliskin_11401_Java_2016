package com.pliskin.impl;


import com.pliskin.interfaces.Audience;
import com.pliskin.interfaces.Monster;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class Orgs implements Audience {

    private boolean isReady;

    @Value("bo-ga-ga")
    private String words;

    @Autowired
    @Qualifier("dragon")
    private Monster monster;

    @Value("100")
    private int number;

    public Orgs(String words, Monster monster, Integer number) {
        this.words = words;
        this.monster = monster;
        this.number = number;
        isReady = true;
    }

    public Orgs() {
        isReady = true;
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
        System.out.println("sdghsilhsvhegsesev[e");
    }

    public Monster getMonster() {
        return monster;
    }

    public int getNumber() {
        return number;
    }

    public String getWords() {
        return words;
    }
}
