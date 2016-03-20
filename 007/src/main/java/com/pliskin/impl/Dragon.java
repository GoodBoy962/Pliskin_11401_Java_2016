package com.pliskin.impl;


import com.pliskin.interfaces.Hero;
import com.pliskin.interfaces.Monster;
import com.pliskin.interfaces.Regeneratable;
import com.pliskin.interfaces.Weapon;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class Dragon implements Monster, Regeneratable {

    @Autowired
    @Qualifier(value = "fireBallsStroker")
    private Weapon weapon;

    @Value("Drago")
    private String name;

    @Autowired
    @Qualifier(value = "knight")
    private Hero hero;

    private int lives = 1;

    public Dragon(String name) {
        this.name = name;
        System.out.println("i am dragon the " + name + " and I'll kill you");
    }

    public Dragon() {
    }

    @Override
    public void setWeapon(Weapon weapon) {
        this.weapon = weapon;
    }

    @Override
    public String makeAttack() {
        return weapon.attack();
    }

    @Override
    public String surrender() {
        return "You won! The Princess is yours now";
    }

    @Override
    public void setOpponent(Hero hero) {
        this.hero = hero;
    }

    @Override
    public void regenerate() {
        System.out.println("The dragon is renerating!");
    }

    @Override
    public void updateWeapon(Weapon weapon) {
        if (lives > 0) {
            if (weapon.isBroken()) {
                setWeapon(weapon);
                lives--;
            }
        } else {
            System.out.println(surrender());
        }
    }

    @Override
    public void impossibleToDo() {
        if (weapon.isBroken() && lives == 0) {
            System.out.println("the " + name + " lives level is over");
        }
    }

    public Hero getHero() {
        return hero;
    }

    public int getLives() {
        return lives;
    }

    public String getName() {
        return name;
    }

    public Weapon getWeapon() {
        return weapon;
    }
}
