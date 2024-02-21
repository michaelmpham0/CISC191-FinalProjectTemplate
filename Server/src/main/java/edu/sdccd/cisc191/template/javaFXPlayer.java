package edu.sdccd.cisc191.template;

import java.util.HashMap;

public class javaFXPlayer {
    //HashMap to store class info. Stats correspond top from bottom of the above list, left to right.
    static HashMap<String, int[]> classStats = new HashMap<String, int[]>()
    {{
        put("Knight", new int[]{35,15,30,50});
        put("Wizard", new int[]{18,5,100,50});
        put("Barbarian", new int[]{25,25,10,0});
        put("Ranger", new int[]{20,10,20,35});
    }};
    private int HP,maxHP,ATK,GOLD,MANA;

    private String Name,Class;

    /**
     * Creates player, default constructor
     */
    public javaFXPlayer(){
        Name = Class = "Unknown";
    }

    /**
     * Simple constructor
     */
    public javaFXPlayer(String Name, String Class){
        this.Name = Name;
        this.Class = Class;
    }

    public String getAllStats(){
        try
        {
            return String.format("Health:%-5d \t Attack:%-5d \t\nMana:%-5d \t\t Gold:%d",HP,ATK,MANA,GOLD);
        }
        catch (NullPointerException Exception)
        {
            return "Unknown";
        }
    }

    public String getName(){
        return Name;
    }

    public String getPlayerClass(){
        return Class;
    }

    public int getGold(){
        return  GOLD;
    }
    public int getMana(){
        return  MANA;
    }
    public int getAttack(){
        return  ATK;
    }
    public int getHealth(){
        return  HP;
    }

    public void setHealth(int healthDifference){
        if (healthDifference<0){
            HP = Math.min((HP + healthDifference), 0);
        }
        else {
            HP = Math.max((HP + healthDifference), maxHP);
        }
    }

    public void setUp(int currentClass){
        String classList[] = {"Knight","Wizard","Barbarian","Ranger"};
        Class = classList[currentClass];

        maxHP = HP = classStats.get(Class)[0];
        ATK = classStats.get(Class)[1];
        MANA = classStats.get(Class)[2];
        GOLD = classStats.get(Class)[3];
    }
}
