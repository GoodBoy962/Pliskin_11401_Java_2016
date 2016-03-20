package com.pliskin.impl;


import com.pliskin.interfaces.Audience;
import com.pliskin.interfaces.Field;
import com.pliskin.interfaces.Hero;
import com.pliskin.interfaces.Monster;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class BoxingRing implements Field {

    @Autowired
    @Qualifier("people")
    private Audience audience;

    @Autowired
    @Qualifier("wolverine")
    private Hero hero;

    @Autowired
    @Qualifier("gungster")
    private Monster monster;

    @Value("false")
    private boolean lights;

    public BoxingRing() {

    }


    @Override
    public void setAudience(Audience audience) {
        this.audience = audience;
    }

    @Override
    public void setFighters(Hero hero, Monster monster) {
        this.hero = hero;
        this.monster = monster;
    }

    @Override
    public String startBattle(String startWords) {
        System.out.println(startWords);
        return "Start";
    }

    @Override
    public String endBattle(String endWords) {
        System.out.println(endWords);
        return "End";
    }

    public void turnTheLights() {
        lights = !lights;
        if (lights) {
            System.out.println("the lights were turned up");
        } else {
            System.out.println("the lights were turned down");
        }
    }

    public Audience getAudience() {
        return audience;
    }

    public Hero getHero() {
        return hero;
    }

    public Monster getMonster() {
        return monster;
    }
}
