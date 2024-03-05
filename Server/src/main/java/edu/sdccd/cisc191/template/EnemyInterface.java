package edu.sdccd.cisc191.template;

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

    public static void setHealth(int health) {

    }

    public static void setName(String name) {

    }

    public String enemyTurn();

    public static int takeDamage(int damage) {
        return 0;
    }
}
