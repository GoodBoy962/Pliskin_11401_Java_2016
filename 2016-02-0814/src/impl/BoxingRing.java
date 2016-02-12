package impl;


import interfaces.Audience;
import interfaces.Field;
import interfaces.Hero;
import interfaces.Monster;

public class BoxingRing implements Field {
    private Audience audience;

    private Hero hero;

    private Monster monster;

    private boolean lights;

    public BoxingRing() {

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

    public void turnTheLights() {
        lights = !lights;
        if (lights) {
            System.out.println("the lights were turned up");
        } else {
            System.out.println("the lights were turned down");
        }
    }
}
