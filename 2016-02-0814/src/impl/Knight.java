package impl;

import interfaces.Hero;
import interfaces.Monster;
import interfaces.Weapon;


public class Knight implements Hero {

    private Weapon weapon;

    private String name;

    private Monster monster;

    private int extraLives = 0;

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
}
