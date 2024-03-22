package edu.sdccd.cisc191.template.Enemies;


import edu.sdccd.cisc191.template.GUI.GUIController;
import edu.sdccd.cisc191.template.Player;
import edu.sdccd.cisc191.template.StatusHandler;

import java.util.HashMap;

public class Enemy extends StatusHandler implements EnemyInterface {

    static HashMap<String, Integer> Statuses = new HashMap<String, Integer>()
    {
    };

    private static boolean attacking = false;
    private static int health;
    private static int maxHealth;
    private static int damage;
    private static String name;
    private static String encounterText;
    private static String firstText;
    private int xpDrop = 0;
    private int goldDrop = 0;
    private double defenseMultiplier = 1.0;

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

    public void setAttacking(boolean attacking) {
        this.attacking = attacking;
    }

    public HashMap<String,Integer> getAllStatus() {return Statuses;}
    public boolean hasStatuses() {return Statuses.isEmpty();}

    public boolean getStatus(String status ){return Statuses.containsKey(status);}

    public void setStatus(String status,int statusTime)
    {
        if (Statuses.containsKey(status)) {
            if (statusTime>Statuses.get(status)){
                Statuses.replace(status,statusTime);
            }
        }
        else {
            Statuses.put(status,statusTime);
        }
    }

    public String checkStatus(String status){
        String returnString = "Unknown.";

        switch (status){
            case "Bleed":
                if (Statuses.get("Bleed")>0){
                    Statuses.replace("Bleed",Statuses.get("Bleed")-1);
                    if (attacking) {
                        double bleedDamage = (((double) (maxHealth - health) / maxHealth) * (maxHealth * 0.25));

                        if (bleedDamage <= 0) {
                            bleedDamage = 1;
                        }
                        returnString = name + " bleeds for " + bleedDamage + " damage!";
                        takeDamage((int) bleedDamage);
                    }
                    else {
                        returnString = name + " bleeds for " + ((int)(maxHealth * 0.01)) + " damage!";
                        takeDamage((int) (maxHealth * 0.01));
                    }
                }
                else {
                    Statuses.remove("Bleed");
                    returnString = name + " stops bleeding.";
                }
                break;
            case "Burn":
                if (Statuses.get("Burn")>0) {
                    Statuses.replace("Burn",Statuses.get("Burn")-1);
                    returnString = name + " burns for " + ((int) (maxHealth * 0.05)) + " damage!";
                    takeDamage((int) (maxHealth * 0.05));
                }
                else {
                    Statuses.remove("Burn");
                    returnString = name + " stops burning.";
                }
                break;
            case "Paralyze":
                if (Statuses.get("Paralyze")>0) {
                    Statuses.replace("Paralyze",Statuses.get("Paralyze")-1);
                    returnString = name + " is paralyzed!";
                }
                else {
                    Statuses.remove("Paralyze");
                    returnString = name + " stops being paralyzed.";
                }
                break;
        }
        return returnString;
    }
    public String dealDamage(int damageDealt, Player player, String returnStr)
    {
        damageDealt = (int) (damageDealt*player.getDefenseMultiplier());

        String trueReturnString = returnStr+" You took " + damageDealt + " damage!";

        if (Statuses.containsKey("Paralyze")){
            trueReturnString = name+" can't move!";
            damageDealt=0;
        }

        player.setHealth(player.getHealth()-damageDealt);
        GUIController.updateHealthAndMana();
        return trueReturnString;
    }

    public void setDefenseMultiplier(double newDefenseMultiplier) {defenseMultiplier = newDefenseMultiplier;}
    public double getDefenseMultiplier() {return defenseMultiplier;}
    public int getHealth() {return health;}
    public String getName() {return name;}

    public int getMaxHealth() {return maxHealth;}
    public String getEncounterText() {return encounterText;}
    public String getFirstText() {return firstText;}
    public void setDamage(int damage) {this.damage = damage;}

    public void setHealth(int health) {
        this.health = health;
    }

    public void setName(String name) {
        this.name = name;
    }

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
