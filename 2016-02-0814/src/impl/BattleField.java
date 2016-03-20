package impl;

import interfaces.Audience;
import interfaces.Field;
import interfaces.Hero;
import interfaces.Monster;
import org.springframework.stereotype.Component;

@Component
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

    public void turnTheSun() {
        sun = !sun;
        if (sun) {
            System.out.println("the sun is up");
        } else {
            System.out.println("the sun is down");
        }
    }

    public Audience getAudience() {
        return audience;
    }

    public Hero getHero() {
        return hero;
    }

    public Monster getMonster() {
        return monster;
    }

    public boolean isSun() {
        return sun;
    }
}
