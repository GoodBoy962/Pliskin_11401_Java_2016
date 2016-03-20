package com.pliskin.impl;

import com.pliskin.interfaces.Monster;
import com.pliskin.interfaces.Regeneratable;
import com.pliskin.interfaces.SuperHero;
import com.pliskin.interfaces.Weapon;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class Wolverine implements SuperHero, Regeneratable {

    @Autowired
    @Qualifier("clutches")
    private Weapon weapon;

    @Value("Hugh Jackman")
    private String name;

    @Autowired
    @Qualifier("gungster")
    private Monster monster;

    private int lives = 1;

    private List<Weapon> weapons;

    public Wolverine(String name) {
        this.name = name;
        System.out.println("i am wolverine the " + name + " and I'll kill you");
    }

    public Wolverine() {
        System.out.println("i am wolverine the " + name + " and I'll kill you");
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
            if (lives > 0) {
                regenerate();
                updateWeapon(new Clutches());
                lives--;
                return "regenerating";
            } else {
                return "LOST";
            }
        }
    }

    @Override
    public String runaway() {
        return "I'd like to run away";
    }

    @Override
    public void setOpponent(Monster monster) {
        this.monster = monster;
    }

    @Override
    public void regenerate() {
        System.out.println("The wolverine is regerating!");
    }

    @Override
    public void updateWeapon(Weapon weapon) {
        if (lives > 0) {
            if (weapon.isBroken()) {
                setWeapon(weapon);
                lives--;
            }
        } else {
            System.out.println(flyAway("You won! But I'll be back"));
        }
    }

    @Override
    public void impossibleToDo() {
        if (weapon.isBroken() && lives == 0) {
            System.out.println("the " + name + " lives level is over");
        }
    }

    @Override
    public void setWeapons(List<Weapon> weapons) {
        this.weapons = weapons;
    }

    @Override
    public String makeSuperAttack() {
        String superAttack = "";
        for (int i = 0; i < weapons.size(); i++) {
            superAttack += weapons.get(i).attack();
        }
        return superAttack;
    }

    @Override
    public String flyAway(String finalWords) {
        return finalWords;
    }

    public int getLives() {
        return lives;
    }

    public List<Weapon> getWeapons() {
        return weapons;
    }

    public Weapon getWeapon() {
        return weapon;
    }

    public Monster getMonster() {
        return monster;
    }

    public String getName() {
        return name;
    }
}
