package impl;

import interfaces.Hero;
import interfaces.Monster;
import interfaces.Weapon;

public class Gungster implements Monster {

    private Hero hero;

    private Weapon weapon;

    private String name;

    private final int extraLives = 0;

    public Gungster(String name) {
        this.name = name;
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
