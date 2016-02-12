package main;


import impl.*;
import interfaces.*;

public class Main {
    public static void main(String[] args) {
        System.out.println("Fight 1");
        Field field = new BattleField();
        Monster monster = new Dragon("WITH GIRLS TATTOO");
        Weapon monsterWeapon = new FireBallsStroker();
        Weapon heroWeapon = new Sword();
        monster.setWeapon(monsterWeapon);
        Audience audience = new Orgs("sdrhfbsdfb", monster, 100);
        Hero hero = new Knight();
        hero.setWeapon(heroWeapon);
        hero.setOpponent(monster);
        monster.setOpponent(hero);
        ((BattleField) field).turnTheSun();
        field.setAudience(audience);
        field.setFighters(hero, monster);
        field.startBattle("The battle is starting!!!");
        String monsterAttack = monster.makeAttack();
        String heroAttack = hero.makeAttack();
        while (!heroAttack.equals("LOST") && !monsterAttack.equals("LOST")) {
            monsterAttack = monster.makeAttack();
            heroAttack = hero.makeAttack();
            System.out.println(monsterAttack);
            System.out.println(heroAttack);
        }
        if (heroAttack.equals("LOST")) {
            System.out.println(hero.runaway());
        } else {
            System.out.println(monster.surrender());
        }
        ((BattleField) field).turnTheSun();

        System.out.println();
        System.out.println();
        System.out.println("Fight 2");
        Field field1 = new BoxingRing();
        Monster monster1 = new Gungster();
        SuperHero superHero = new Wolverine("Hugh Jackman");
        Weapon monsterWeapon1 = new Gun();
        Weapon superHeroWeapon = new Clutches();
        monster1.setWeapon(monsterWeapon1);
        superHero.setWeapon(superHeroWeapon);
        monster1.setOpponent(superHero);
        superHero.setOpponent(monster1);
        Audience audience1 = new People("We want bload and meat", superHero, 20);
        field1.setFighters(superHero, monster1);
        field1.setAudience(audience1);
        field1.startBattle("Wolverine vs Gungster");
        ((BoxingRing) field1).turnTheLights();
        String monsterAttack1 = monster1.makeAttack();
        String superHeroAttack = superHero.makeAttack();
        while (!superHeroAttack.equals("LOST") && !monsterAttack1.equals("LOST")) {
            System.out.println(monsterAttack1);
            System.out.println(superHeroAttack);
            monsterAttack1 = monster1.makeAttack();
            superHeroAttack = superHero.makeAttack();
        }
        if (superHeroAttack.equals("LOST")) {
            System.out.println(superHero.flyAway("uppps"));
        } else {
            System.out.println(monster1.surrender());
        }
        ((BoxingRing) field1).turnTheLights();
    }
}
