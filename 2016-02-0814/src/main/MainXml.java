package main;

import fights.Fights;
import impl.BattleField;
import impl.BoxingRing;
import interfaces.*;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * Created by aleksandrpliskin on 17.03.16.
 * 006
 */
public class MainXml {
    public static void main(String[] args) {
        System.out.println("Fight 1");
        ApplicationContext context = new ClassPathXmlApplicationContext("spring-config.xml");
        Field field = (Field) context.getBean("battleField");
        Monster monster = (Monster) context.getBean("dragon");
        Hero hero = (Hero) context.getBean("knight");
        ((BattleField) field).turnTheSun();
        field.setFighters(hero, monster);
        field.startBattle("The battle is starting!!!");
        String monsterAttack = monster.makeAttack();
        String heroAttack = hero.makeAttack();
        Fights.fights(heroAttack, monsterAttack, hero, monster);
        if (heroAttack.equals("LOST")) {
            System.out.println(hero.runaway());
        } else {
            System.out.println(monster.surrender());
        }
        ((BattleField) field).turnTheSun();

        System.out.println();
        System.out.println();
        System.out.println("Fight 2");
        Field field1 = (Field) context.getBean("boxRing");
        Monster monster1 = (Monster) context.getBean("gungster");
        SuperHero superHero = (SuperHero) context.getBean("wolwerine");
        field1.setFighters(superHero, monster1);
        field1.startBattle("Wolverine vs Gungster");
        ((BoxingRing) field1).turnTheLights();
        String monsterAttack1 = monster1.makeAttack();
        String superHeroAttack = superHero.makeAttack();
        Fights.fights(superHeroAttack, monsterAttack1, superHero, monster1);
        if (superHeroAttack.equals("LOST")) {
            System.out.println(superHero.flyAway("uppps"));
        } else {
            System.out.println(monster1.surrender());
        }
        ((BoxingRing) field1).turnTheLights();
    }
}