package edu.sdccd.cisc191.template.Enemies;

import edu.sdccd.cisc191.template.Player;

public interface EnemyInterface{
    public static int getDamage() {
        return 0;
    }

    public static int getHealth() {
        return 0;
    }

    public static String getName() {
        return null;
    }

    public static int getMaxHealth() {
        return 0;
    }

    public static void setDamage(int damage) {

    }
    public static String getEncounterText() {
        return null;
    }
    public static String getFirstText() {
        return null;
    }
    public static void setHealth(int health) {

    }

    public static void setName(String name) {

    }

    public String enemyTurn(Player player);

    public static int takeDamage(int damage) {
        return 0;
    }
}
