package com.pliskin.fights;


import com.pliskin.interfaces.Hero;
import com.pliskin.interfaces.Monster;

/**
 * Created by aleksandrpliskin on 17.03.16.
 */
public class Fights {

    public static void fights(String heroAttack, String monsterAttack, Hero hero, Monster monster) {
        while (!heroAttack.equals("LOST") && !monsterAttack.equals("LOST")) {
            monsterAttack = monster.makeAttack();
            heroAttack = hero.makeAttack();
            System.out.println(monsterAttack);
            System.out.println(heroAttack);
        }
    }

}
