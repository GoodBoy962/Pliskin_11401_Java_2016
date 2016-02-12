package interfaces;


public interface Monster {

    void setWeapon(Weapon weapon);

    String makeAttack();

    String surrender();

    void setOpponent(Hero hero);

}
