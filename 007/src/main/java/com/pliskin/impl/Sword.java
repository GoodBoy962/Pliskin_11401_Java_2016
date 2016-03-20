package com.pliskin.impl;


import com.pliskin.interfaces.Weapon;
import org.springframework.stereotype.Component;

@Component
public class Sword implements Weapon {

    private final byte FULL_ATTACKS = 3;

    private boolean sharpened;

    private int lifetime;

    private byte restAttacksBeforeReload;

    public Sword() {
        sharpened = true;
        System.out.println("Sword is ready");
        restAttacksBeforeReload = FULL_ATTACKS;
        lifetime = 5 * FULL_ATTACKS;
    }

    @Override
    public void tune() {
        sharpened = true;
        System.out.println("Sword is ready");
    }

    @Override
    public String attack() {
        if (!isBroken()) {
            if (restAttacksBeforeReload > 0) {
                System.out.println("Attack with sword");
                restAttacksBeforeReload--;
                return "ATTACK";
            } else {
                System.out.println("Sword is not ready! It needs to be reloaded");
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
