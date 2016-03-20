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

    public People() {

    }


    @Override
    public boolean isReady() {
        return isReady;
    }

    @Override
    public void say(String words) {
        System.out.println(words);
    }

    @Override
    public void goAway() {
        System.out.println("Good Fight");
    }

    public Hero getHero() {
        return hero;
    }

    public int getNumber() {
        return number;
    }

    public String getWords() {
        return words;
    }

    public void setHero(Hero hero) {
        this.hero = hero;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public void setWords(String words) {
        this.words = words;
    }
}
