package impl;

import interfaces.Monster;
import interfaces.Regeneratable;
import interfaces.SuperHero;
import interfaces.Weapon;

import java.util.List;


public class Wolverine implements SuperHero, Regeneratable {
    Weapon weapon;

    String name;

    Monster monster;

    int lives = 1;

    List<Weapon> weapons;

    public Wolverine(String name) {
        this.name = name;
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
        for (Weapon weap : weapons) {
            superAttack += weap.attack();
        }
        return superAttack;
    }

    @Override
    public String flyAway(String finalWords) {
        return finalWords;
    }
}
