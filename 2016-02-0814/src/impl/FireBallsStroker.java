package impl;

import interfaces.Weapon;

public class FireBallsStroker implements Weapon {

    private final byte FULL_ATTACKS = 3;

    private boolean loaded;

    private int lifetime;

    private byte restAttacksBeforeReload;

    public FireBallsStroker() {
        restAttacksBeforeReload = FULL_ATTACKS;
        lifetime = 3 * FULL_ATTACKS;
    }

    @Override
    public void tune() {
        loaded = true;
        System.out.println("Fire balls stroker is ready");
    }

    @Override
    public String attack() {
        if (!isBroken()) {
            if (restAttacksBeforeReload > 0) {
                System.out.println("Attack with fire balls");
                restAttacksBeforeReload--;
                return "ATTACK";
            } else {
                System.out.println("fire balls stroker is not ready! It needs to be reloaded");
                loaded = false;
                reload();
                return "NO ATTACK";
            }
        } else {
            return "NO ATTACK";
        }
    }


    @Override
    public void reload() {
        restAttacksBeforeReload = FULL_ATTACKS;
        lifetime -= restAttacksBeforeReload;
        tune();
    }

    @Override
    public boolean isBroken() {
        return lifetime <= 0 && restAttacksBeforeReload <= 0;
    }
}
