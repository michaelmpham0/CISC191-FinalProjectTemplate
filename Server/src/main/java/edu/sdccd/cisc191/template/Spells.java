package edu.sdccd.cisc191.template;

import java.io.Serializable;
import java.util.ArrayList;

public class Spells implements Serializable {
    private static ArrayList<Abilities> inventory = new ArrayList<>();

    public Spells(String userClass) {
        switch (userClass.toLowerCase()) {
            case "knight":
                inventory.add(new Abilities("Vertical", String.format("Deal %d damage.",15),"You strike downwards vertically, dealing 5 damage.",5,15,2.0,"Exponential"));
                inventory.add(new Abilities("Horizontal", "Deal 10 damage, then inflict Bleed for 3 turns.","You attack with a broad strike, dealing 10 damage and bleeding the enemy.",10,10,10,"Linear"));
                break;
            case "wizard":
                inventory.add(new Abilities("Heal", "Heal 50% HP.","You call upon heavenly forces, healing your wounds.",15,0,0.0,"Linear"));
                inventory.add(new Abilities("Firebolt", "Deal 5 damage, then inflict Burn for 3 turns.","You fire forth a bolt of fire, dealing 5 damage and igniting the enemy on fire.",15,5,1.5,"Exponential"));
                break;
            case "barbarian":
                inventory.add(new Abilities("Rage", "Deal 50% more damage for 3 turns.","You roar with fury, gaining a damage boost.",10,0,0.0,"Linear"));
                inventory.add(new Abilities("Punching", "Deal 5 damage, then inflict Paralyze for 1 turn.","Your fist meets the enemy's face, dealing 5 damage and stunning them.",10,5,10.0,"Linear"));
                break;
            case "ranger":
                inventory.add(new Abilities("Precision Shot", "Focus for a turn, then deal 25 damage.","You focus on the tip of your arrow.",1,0,20,"Linear"));
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
