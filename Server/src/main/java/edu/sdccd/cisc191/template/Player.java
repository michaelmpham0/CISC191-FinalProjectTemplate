package edu.sdccd.cisc191.template;

import  java.util.Scanner;
import java.util.HashMap;

/**
 * Stats used for the player:
 * HP = Health
 * ATK = Attack
 * MANA = Mana
 * GOLD = Gold
 */
public class Player {

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
    public Player(){
        Name = Class = "Unknown";
    }

    /**
     * Simple constructor
     */
    public Player(String Name, String Class){
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

    public void setUp(){
        TextDisplay displayText = new TextDisplay();
        PromptDisplay displayPrompt = new PromptDisplay();
        PromptController controlPrompt = new PromptController();

        Scanner keyboard = new Scanner(System.in);

        System.out.print("Enter your name: ");
        Name = keyboard.next();

        System.out.println("----------------");

        displayText.display("ClassInstructions");
        displayPrompt.display("Classes");

        int promptChoice = controlPrompt.answerPrompt(4);

        switch(promptChoice)
        {
            case 1:
                Class = "Knight";
                break;
            case 2:
                Class = "Wizard";
                break;
            case 3:
                Class = "Barbarian";
                break;
            case 4:
                Class = "Ranger";
                break;
            default:
                break;
        }


        maxHP = HP = classStats.get(Class)[0];
        ATK = classStats.get(Class)[1];
        MANA = classStats.get(Class)[2];
        GOLD = classStats.get(Class)[3];
    }
}
