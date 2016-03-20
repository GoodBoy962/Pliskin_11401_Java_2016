package com.pliskin.impl;


import com.pliskin.interfaces.Hero;
import com.pliskin.interfaces.Monster;
import com.pliskin.interfaces.Weapon;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class Knight implements Hero {

    @Autowired
    @Qualifier(value = "sword")
    private Weapon weapon;

    @Value("Arthur")
    private String name;

    @Autowired
    @Qualifier(value = "dragon")
    private Monster monster;

    private final int extraLives = 0;

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
    public String runaway() {
        return "You won! I will ride away withoit princess";
    }

    @Override
    public void setOpponent(Monster monster) {
        this.monster = monster;
    }

    public int getExtraLives() {
        return extraLives;
    }

    public Monster getMonster() {
        return monster;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Weapon getWeapon() {
        return weapon;
    }
}
