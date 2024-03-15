package edu.sdccd.cisc191.template;


import edu.sdccd.cisc191.template.GUI.GUIController;
import javafx.scene.media.MediaPlayer;
import org.omg.CORBA.PRIVATE_MEMBER;

public class Enemy implements EnemyInterface{

    private static boolean attacking = false;
    private static int health;
    private static int maxHealth;
    private static int damage;
    private static String status = "None";

    private static int statusTime = 0;
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
    public String checkStatus(){
        String returnString = "Unknown.";
        statusTime-=1;
        System.out.println(status + " for another " + statusTime + " turn(s).");
        System.out.println(attacking);
        switch (status) {
            case "Bleed":

                if (statusTime>0)
                {
                    if (attacking) {
                        double bleedDamage = (((double) (maxHealth - health) / maxHealth) * (maxHealth * 0.25));
                        System.out.println(((maxHealth - health) / maxHealth));

                        System.out.println(bleedDamage);
                        if (bleedDamage <= 0) {
                            bleedDamage = 1;
                        }
                        if (statusTime>0) {
                            returnString = name + " bleeds for " + bleedDamage + " damage!";
                            takeDamage((int) bleedDamage);
                        }
                    }
                    else {
                        returnString = name + " bleeds for " + ((int)(maxHealth * 0.01)) + " damage!";
                        takeDamage((int) (maxHealth * 0.01));
                    }
                }
                else
                {
                    returnString = name + " stops bleeding.";
                    status = "None";
                }

                break;
            case "Burn":
                if (statusTime>0) {
                    returnString = name + " burns for " + ((int) (maxHealth * 0.05)) + " damage!";
                    takeDamage((int) (maxHealth * 0.05));
                }
                else {
                    returnString = name + " stops burning.";
                    status = "None";
                }
                break;
            default:
        }
        return returnString;
    }
    public String dealDamage(int damageDealt,Player player,String returnStr)
    {
        damageDealt = (int) (damageDealt*player.getDefenseMultiplier());

        String trueReturnString = returnStr+" You took " + damageDealt + " damage!";
        switch (status){
            case "Paralyze":
                damageDealt = 0;
                trueReturnString = name + " is paralyzed!";
                break;
            case "DMGReduction":
                //lol
            case "DMGBoost":
                //lol
            default:
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

    public String getStatus() {return status;}
    public String getEncounterText() {return encounterText;}
    public String getFirstText() {return firstText;}
    public int getStatusTime() {return statusTime;}
    public void setDamage(int damage) {this.damage = damage;}

    public void setStatus(String status,int statusTime)
    {
        this.status = status;this.statusTime = statusTime;
    }

    public void setStatusTime(int statusTime){this.statusTime = statusTime;}

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
