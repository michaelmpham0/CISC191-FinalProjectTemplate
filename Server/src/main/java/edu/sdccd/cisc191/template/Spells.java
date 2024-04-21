package edu.sdccd.cisc191.template;

import java.io.Serializable;
import java.util.ArrayList;

public class Spells implements Serializable {
    private static ArrayList<Abilities> inventory = new ArrayList<>();

    public Spells(String userClass) {
        switch (userClass.toLowerCase()) {
            case "knight":
                inventory.add(new Abilities("Vertical Slash", String.format("Deal %d damage.",15),String.format("You strike downwards vertically, dealing %d damage.",15),5,15,2.0,"Exponential"));
                inventory.add(new Abilities("Sanguine Sever", "Deal 10 damage, then inflict Bleed for 3 turns.","You attack with a broad strike, dealing 10 damage and bleeding the enemy.",10,10,10,"Linear"));
                break;
            case "wizard":
                inventory.add(new Abilities("Heal", String.format("Heal %d%% health.",50),"You call upon heavenly forces, healing your wounds.",15,0,0.0,"Linear"));
                inventory.add(new Abilities("Firebolt", String.format("Deal %d damage, then inflict Burn for 3 turns.",5),String.format("You fire forth a bolt of fire, dealing %d damage and igniting the enemy on fire.",5),15,5,1.5,"Exponential"));
                break;
            case "barbarian":
                inventory.add(new Abilities("Rage", String.format("Deal %d%% more damage with attacks for 3 turns.",50),"You roar with fury, gaining a damage boost.",10,0,0.0,"Linear"));
                inventory.add(new Abilities("Punching",  String.format("Deal %d damage, then inflict Paralyze for 1 turn.",5), String.format("Your fist meets the enemy's face, dealing %d damage and stunning them.",5),10,5,10.0,"Linear"));
                break;
            case "ranger":
                inventory.add(new Abilities("Charge Shot",  String.format("Next turn, deal %d damage the next time you attack. Consecutive charges stack more damage.",30),"You focus on the tip of your arrow.",1,30,20,"Linear"));
                inventory.add(new Abilities("Quickness", "Automatically defend against attacks for 2 turns.","You sharpen your instincts, reacting to the enemies automatically.",20,0,0.0,"Linear"));
                break;
            default:
                System.out.println("Unknown class.");
                break;
        }
    }

    public static ArrayList<Abilities> getSpells() {
        return inventory;
    }

}
