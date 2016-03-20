package com.pliskin.interfaces;


import java.util.List;

public interface SuperHero extends Hero {

    void setWeapons(List<Weapon> weapons);

    String makeSuperAttack();

    String flyAway(String finalWords);

}
