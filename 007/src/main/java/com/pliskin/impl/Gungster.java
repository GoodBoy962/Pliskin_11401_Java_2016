package com.pliskin.impl;

import com.pliskin.interfaces.Hero;
import com.pliskin.interfaces.Monster;
import com.pliskin.interfaces.Weapon;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class Gungster implements Monster {

    @Autowired
    @Qualifier("wolverine")
    private Hero hero;

    @Autowired
    @Qualifier("gun")
    private Weapon weapon;

    @Value("Mica")
    private String name;

    private final int extraLives = 0;

    public Gungster(String name) {
        this.name = name;
    }

    public Gungster() {
    }

    @Override
    public void setWeapon(Weapon weapon) {
        this.weapon = weapon;
    }

    @Override
    public String makeAttack() {
        if (!weapon.isBroken()) {
            return weapon.attack();
        } else {
            return "LOST";
        }
    }

    @Override
    public String surrender() {
        return "AAAAAAA! run boy run";
    }

    @Override
    public void setOpponent(Hero hero) {
        this.hero = hero;
    }

    public int getExtraLives() {
        return extraLives;
    }

    public Weapon getWeapon() {
        return weapon;
    }

    public String getName() {
        return name;
    }

    public Hero getHero() {
        return hero;
    }


}
