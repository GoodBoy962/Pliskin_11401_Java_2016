package impl;

import interfaces.Audience;
import interfaces.Field;
import interfaces.Hero;
import interfaces.Monster;


public class BattleField implements Field {

    private Audience audience;

    private Hero hero;

    private Monster monster;

    private boolean sun;

    public BattleField() {
    }


    @Override
    public void setAudience(Audience audience) {
        this.audience = audience;
    }

    @Override
    public void setFighters(Hero hero, Monster monster) {
        this.hero = hero;
        this.monster = monster;
    }

    @Override
    public String startBattle(String startWords) {
        System.out.println(startWords);
        return "Start";
    }

    @Override
    public String endBattle(String endWords) {
        System.out.println(endWords);
        return "End";
    }

    void turnTheSun() {
        sun = !sun;
        if (sun) {
            System.out.println("the sun is up");
        } else {
            System.out.println("the sun is down");
        }
    }

}
