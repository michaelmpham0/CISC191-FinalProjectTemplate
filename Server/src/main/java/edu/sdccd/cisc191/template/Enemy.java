package edu.sdccd.cisc191.template;

public class Enemy implements EnemyInterface{
    private int health,maxHealth,damage;

    private String name;

    public Enemy(){
        this.health = maxHealth = damage= 0;
        name = "Unknown";
    }
    public Enemy(int health,int damage, String name) {
        this.health = maxHealth = health;
        this.name = name;
    }

    public int getDamage() {
        return damage;
    }

    public int getHealth() {
        return health;
    }

    public String getName() {
        return name;
    }

    public int getMaxHealth() {
        return maxHealth;
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

    public int takeDamage(int damage){
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
