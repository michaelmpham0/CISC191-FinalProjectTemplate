package edu.sdccd.cisc191.template;

import  java.util.Scanner;
import java.util.HashMap;

/**
 * Stats used for the player:
 * HP = Health
 * ATK = Attack
 * DEF = Defense
 * MAN = Mana
 * GLD = Gold
 */
public class Player {

   //HashMap to store class info. Stats correspond top from bottom of the above list, left to right.
    static HashMap<String, int[]> classStats = new HashMap<String, int[]>()
    {{
        put("Knight", new int[]{100,50,50,50,50});
        put("Wizard", new int[]{100,25,25,100,75});
        put("Bulwark", new int[]{125,25,75,25,50});
        put("Hunter", new int[]{75,75,25,0,100});
    }};
    private int HP, maxHP, ATK, DEF, GLD, MAN;

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
            return String.format("Health:%-5d \t Attack:%-5d \t Defense:%-5d \t Mana:%-5d \t Gold:%d",HP,ATK,DEF,GLD,MAN);
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
        return  GLD;
    }
    public int getDefense(){
        return  DEF;
    }

    public int getMana(){
        return  MAN;
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
                Class = "Bulwark";
                break;
            case 4:
                Class = "Hunter";
                break;
            default:
                break;
        }


        maxHP = HP = classStats.get(Class)[0];
        ATK = classStats.get(Class)[1];
        DEF = classStats.get(Class)[2];
        MAN = classStats.get(Class)[3];
        GLD = classStats.get(Class)[4];
    }
}
