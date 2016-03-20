package com.pliskin.interfaces;


public interface Field {

    void setAudience(Audience audience);

    void setFighters(Hero hero, Monster monster);

    String startBattle(String startWords);

    String endBattle(String endWords);

}
