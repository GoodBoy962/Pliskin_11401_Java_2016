package impl;

import interfaces.Weapon;

public class Gun implements Weapon {
    private final byte FULL_ATTACKS = 5;

    private boolean loaded;

    private int lifetime;

    private byte restAttacksBeforeReload;

    public Gun() {
        restAttacksBeforeReload = FULL_ATTACKS;
        lifetime = 4 * FULL_ATTACKS;
    }

    @Override
    public void tune() {
        loaded = true;
        System.out.println("gun is ready");
    }

    @Override
    public String attack() {
        if (!isBroken()) {
            if (restAttacksBeforeReload > 0) {
                System.out.println("Attack with gun");
                restAttacksBeforeReload--;
                return "ATTACK";
            } else {
                System.out.println("Gun is not ready! It needs to be reloaded");
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

    public byte getRestAttacksBeforeReload() {
        return restAttacksBeforeReload;
    }

    public int getLifetime() {
        return lifetime;
    }

    public boolean isLoaded() {
        return loaded;
    }
}
