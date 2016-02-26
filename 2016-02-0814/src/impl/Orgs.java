package impl;

import interfaces.Audience;
import interfaces.Monster;


public class Orgs implements Audience {

    private boolean isReady;

    private String words;

    private Monster monster;

    private int number;

    public Orgs(String words, Monster monster, Integer number) {
        this.words = words;
        this.monster = monster;
        this.number = number;
        isReady = true;
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
        System.out.println("sdghsilhsvhegsesev[e");
    }

    public Monster getMonster() {
        return monster;
    }

    public int getNumber() {
        return number;
    }

    public String getWords() {
        return words;
    }
}
