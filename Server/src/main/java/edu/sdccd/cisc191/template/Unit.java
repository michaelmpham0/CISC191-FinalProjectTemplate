package edu.sdccd.cisc191.template;

public class Unit {
    private static boolean attacking = false;
    private static int health;
    private static int maxHealth;
    private static int damage;
    private static String name;

    public int takeDamage(int damage){
        damage = (int) (damage*defenseMultiplier);

        if ((health-damage) <= 0) {
            health = 0;
            return 0;
        }
        else
        {
            health = health-damage;
            return health;
        }
    }
}
