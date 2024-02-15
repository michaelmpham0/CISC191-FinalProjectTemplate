package edu.sdccd.cisc191.template;

import  java.util.Scanner;

/**
 * Stats used for the player:
 * HP = Health
 * ATK = Attack
 * DEF = Defense
 * GLD = Gold
 */
public class Player {
    private double HP,ATK,DEF,GLD=0.0;

    private String Name,Class;

    /**
     * Creates player, default constructor
     */
    public Player(){
        Name=Class="Unknown";
    }

    /**
     * Simple constructor
     */
    public Player(String Name, String Class){
        this.Name = Name;
        this.Class = Class;
    }

    public String getStat(String Stat){
        if (Stat.equals("All")) {
            try
            {
                String Return = String.format("Your HP is " + HP + ", Attack is " + ATK + ", Defense is " + DEF + ", and you have " + GLD +" Gold."  );
                return Stat;
            }
            catch (NullPointerException Exception)
            {
                return "Unknown";
            }
        }
        else
        {
            try
            {
                return Stat;
            }
            catch (NullPointerException Exception)
            {
                return "Unknown";
            }
        }
    }

    public void setStat(){

    }
}
