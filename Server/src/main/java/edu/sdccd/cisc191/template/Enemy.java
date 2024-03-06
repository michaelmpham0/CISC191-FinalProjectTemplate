package edu.sdccd.cisc191.template;

public class Enemy implements EnemyInterface{
    private static int health;
    private static int maxHealth;
    private static int damage;

    private static String name;
    private static String encounterText;

    public Enemy(){
        this.health = maxHealth = damage= 0;
        name = "Unknown";
    }
    public Enemy(int health,int damage, String name,String encounterText) {
        this.health = maxHealth = health;
        this.damage = damage;
        this.name = name;
        this.encounterText = encounterText;
    }

    public static int getDamage() {
        return damage;
    }

    public static int getHealth() {
        return health;
    }

    public static String getName() {
        return name;
    }

    public static int getMaxHealth() {
        return maxHealth;
    }

    public String getEncounterText()
    {
        return encounterText;
    }

    public void setDamage(int damage) {
        this.damage = damage;
    }

    public void setHealth(int health) {
        this.health = health;
    }

    public void setName(String name) {
        this.name = name;
    }

    public static int takeDamage(int damage){
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

    public String enemyTurn(Player player) {
        return null;
    }
}
