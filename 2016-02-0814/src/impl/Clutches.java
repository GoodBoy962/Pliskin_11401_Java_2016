package impl;

import interfaces.Weapon;


public class Clutches implements Weapon {
    private final byte FULL_ATTACKS = 5;

    private boolean sharpened;

    private int lifetime;

    private byte restAttacksBeforeReload;

    public Clutches() {
        restAttacksBeforeReload = FULL_ATTACKS;
        lifetime = 4 * FULL_ATTACKS;
    }

    @Override
    public void tune() {
        sharpened = true;
        System.out.println("Clutches are ready");
    }

    @Override
    public String attack() {
        if (!isBroken()) {
            if (restAttacksBeforeReload > 0) {
                System.out.println("Attack with clutches");
                restAttacksBeforeReload--;
                return "ATTACK";
            } else {
                System.out.println("Gun is not ready! It needs to be reloaded");
                sharpened = false;
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

    public int getLifetime() {
        return lifetime;
    }

    public byte getRestAttacksBeforeReload() {
        return restAttacksBeforeReload;
    }

    public boolean isSharpened() {
        return sharpened;
    }

}

