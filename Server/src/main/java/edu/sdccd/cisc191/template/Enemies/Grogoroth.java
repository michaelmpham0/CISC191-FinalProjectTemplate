package edu.sdccd.cisc191.template.Enemies;

import edu.sdccd.cisc191.template.Enemy;
import edu.sdccd.cisc191.template.GUI.GUIController;
import edu.sdccd.cisc191.template.Player;

public class Grogoroth extends Enemy {

    private int health,maxHealth,damage,damageDealt,skill=0;

    private String encounterText;

    private String action = "None";

    public String name;
    private String skillPeaceForAll(Player player){
        action = "None";
        if (player.getDefenseMultiplier() == 0.5 ) {
            damageDealt = player.getMaxHealth()/5;
        } else {
            damageDealt = player.getMaxHealth();
        }
        return "With a simple gesture, Grogoroth crushes your soul.";
    }

    private String skillDestroy(Player player){
        action = "None";
        damageDealt = 25;
        return "Grogoroth slams down its arms, destroying you." ;
    }

    private int currentTurn,oldTurn = 0;

    public String enemyTurn(Player player){
        String returnStr = "The many eyes stare at you. Nothing happens.";
        currentTurn++;
       if (action.equals("PrepareDestroy")) {
            returnStr =  skillDestroy(player);
        }
        else if (action.equals("PreparePOA")) {
            if (oldTurn==0){
                oldTurn = currentTurn;
            }
            else {
                if ((currentTurn-oldTurn)>2){
                    returnStr = skillPeaceForAll(player);
                }
                else if ((currentTurn-oldTurn)==1){
                    returnStr = "A sinister aura envelopes you. Grogoroth prepares its arms towards you.";
                }
                else {
                    returnStr = "Grogoroth's many eyes begin to focus on you.";
                }
            }
        }
        else {
           action = "None";
           damageDealt = 0;
            int max = 10;
            int min = 0;
            int range = (max - min) + 1;

            skill = (int) ((Math.random() * range) + min);
            switch (skill) {
                case 6:
                case 7:
                case 8:
                case 9:
                    action = "PrepareDestroy";
                    returnStr = "Grogoroth raises its arm.";
                    break;
                case 10:
                    action = "PreparePOA";
                    returnStr = "Mystical energy surrounds Grogoroth.";
                    break;
            }
        }
        GUIController.updateHealthAndMana();

        if (damageDealt > 0)
        {
            damageDealt = (int) (damageDealt*player.getDefenseMultiplier());
            player.setHealth(player.getHealth()-damageDealt);
            GUIController.updateHealthAndMana();
            returnStr = returnStr+" You took " + damageDealt + " damage!";
        }
        return returnStr;
    }

    public Grogoroth(){
        super(1000,50,"Grogoroth, Destroyer of Worlds","An overwhelming entity rises from the dark.","Grogoroth examines you.",400,1000);
        this.health = maxHealth = getMaxHealth();
        damage = getDamage();
        name = getName();
        encounterText =  getEncounterText();
    }
}
