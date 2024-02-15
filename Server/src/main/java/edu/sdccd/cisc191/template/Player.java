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

    static HashMap<String, int[]> classStats = new HashMap<String, int[]>()
    {{
        put("Knight", {1,1});
        put("Wizard", 2.0);
        put("Bulwark", 2.0);
        put("Hunter", 2.0);
    }};
    private int HP,ATK,DEF,GLD,MAN;

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
                String Return = String.format("Health:%-15d \t Attack:%-15d \t Defense:%-15d \t Gold:%-15d \t Mana:%d",HP,ATK,DEF,GLD,MAN);
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

    public void setUp(){
        TextDisplay displayText = new TextDisplay();
        PromptDisplay displayPrompt = new PromptDisplay();
        PromptController controlPrompt = new PromptController();

        Scanner keyboard = new Scanner(System.in);

        System.out.print("Enter your name: ");
        Name = keyboard.next();

        displayText.display("ClassInstructions");
        displayPrompt.display("Classes");

        int promptChoice = controlPrompt.answerPrompt(4);
        if (promptChoice == 1)
        {
            Class = "Knight";
        }
        else
        {
            System.out.println("Try again.");
        }
    }

    public void setClass(String Class){
        this.Class = Class;
    }

    public void setStat(){

    }
}
