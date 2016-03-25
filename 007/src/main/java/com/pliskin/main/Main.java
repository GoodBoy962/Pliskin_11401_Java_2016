package com.pliskin.main;

import com.pliskin.configs.Configuration;
import com.pliskin.fights.Fights;
import com.pliskin.impl.BattleField;
import com.pliskin.impl.BoxingRing;
import com.pliskin.interfaces.Field;
import com.pliskin.interfaces.Hero;
import com.pliskin.interfaces.Monster;
import com.pliskin.interfaces.SuperHero;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

/**
 * Created by aleksandrpliskin on 19.03.16.
 * 007
 */
public class Main {
    public static void main(String[] args) {
        ApplicationContext context = new AnnotationConfigApplicationContext(Configuration.class);
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
        Field field1 = (Field) context.getBean("boxingRing");
        Monster monster1 = (Monster) context.getBean("gungster");
        SuperHero superHero = (SuperHero) context.getBean("wolverine");
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
