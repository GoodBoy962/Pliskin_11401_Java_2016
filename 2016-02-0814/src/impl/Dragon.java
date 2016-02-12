package impl;

import interfaces.Hero;
import interfaces.Monster;
import interfaces.Regeneratable;
import interfaces.Weapon;

public class Dragon implements Monster, Regeneratable {

    private Weapon weapon;

    private String name;

    private Hero hero;

    private int lives = 1;

    public Dragon(String name) {
        this.name = name;
        System.out.println("i am dragon the " + name + " and I'll kill you");
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
}
