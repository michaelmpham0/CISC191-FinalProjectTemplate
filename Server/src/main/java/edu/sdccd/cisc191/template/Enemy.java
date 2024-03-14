package edu.sdccd.cisc191.template;


import edu.sdccd.cisc191.template.GUI.GUIController;

public class Enemy implements EnemyInterface{
    private static int health;
    private static int maxHealth;
    private static int damage;

    private static String name;
    private static String encounterText;
    private static String firstText;
    private int xpDrop = 0;
    private int goldDrop = 0;

    public Enemy(){
        this.health = maxHealth = damage= 0;
        name = "Unknown";
    }
    public Enemy(int health,int damage, String name,String encounterText,String firstText,int xpDrop,int goldDrop) {
        this.health = maxHealth = health;
        this.damage = damage;
        this.name = name;
        this.encounterText = encounterText;
        this.firstText = firstText;
        this.xpDrop = xpDrop;
        this.goldDrop = goldDrop;
    }

    public static int getDamage() {
        return damage;
    }
    public String dealDamage(int damageDealt,Player player,String returnStr)
    {
        damageDealt = (int) (damageDealt*player.getDefenseMultiplier());
        player.setHealth(player.getHealth()-damageDealt);
        GUIController.updateHealthAndMana();
        return returnStr+" You took " + damageDealt + " damage!";
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
    public String getFirstText()
    {
        return firstText;
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
    public int getXpReward()
    {
        return this.xpDrop;
    }
    public int getGoldReward()
    {
        return this.goldDrop;
    }

    public String enemyTurn(Player player) {
        return null;
    }
}
