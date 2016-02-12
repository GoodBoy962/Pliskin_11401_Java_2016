package impl;

import interfaces.Audience;
import interfaces.Hero;


public class People implements Audience {

    private boolean isReady;

    private String words;

    private Hero hero;

    private int number;

    public People(String words, Hero hero, Integer number) {
        this.words = words;
        this.hero = hero;
        this.number = number;
        isReady = true;
    }


    @Override
    public boolean isReady() {
        return isReady();
    }

    @Override
    public void say(String words) {
        System.out.println(words);
    }

    @Override
    public void goAway() {
        System.out.println("Good Fight");
    }
}
