package edu.sdccd.cisc191.template.Enemies;

import edu.sdccd.cisc191.template.Enemy;
import edu.sdccd.cisc191.template.GUI.GUIController;
import edu.sdccd.cisc191.template.Player;

public class Goblin extends Enemy {
    private int health,maxHealth,damage,damageDealt,skill=0;

    private String encounterText;
    private String action = "None";

    public String name;
    private String Slash(Player player)
    {
        damageDealt = 5;
        return "The goblin slashes at you.";
    }
    private String PrepareMegaSlash(Player player)
    {
        action = "MegaSlash";
        damageDealt = 0;
        return "The goblin takes a stance and glares at you.";
    }
    private String MegaSlash(Player player)
    {
        action = "None";
        damageDealt = 16;
        return "The goblin leaps onto you and clings onto your face, relentlessly stabbing you.";
    }

    private int currentTurn = 0;



    public String enemyTurn(Player player){
        String returnStr = "The goblin shuffles around, unsure what to do.";
        currentTurn++;
        if (currentTurn == 1)
        {
            // Goblin always shuffles around on first turn
            return returnStr;
        }
        else if (action.equals("MegaSlash"))
        {
            returnStr = MegaSlash(player);
        }
        else
        {
            action = "None";
            int max = 6;
            int min = 0;
            int range = (max - min) +1;
            skill = (int)((Math.random() *range)+min);
            damageDealt = 0;
            switch (skill)
            {
                case 1:
                    returnStr = Slash(player);
                    break;
                case 2:
                    returnStr = Slash(player);
                    break;
                case 3:
                    returnStr = Slash(player);
                    break;
                case 4:
                    returnStr = PrepareMegaSlash(player);
                case 5:
                    returnStr = PrepareMegaSlash(player);
            }
        }
        if (damageDealt > 0)
        {
            damageDealt = (int) (damageDealt*player.getDefenseMultiplier());
            player.setHealth(player.getHealth()-damageDealt);
            GUIController.updateHealthAndMana();
            returnStr = returnStr+" You took " + damageDealt + " damage!";
        }
        return returnStr;
    }

    public Goblin(){
        super(60,50,"Goblin","A puny creature scurries towards you.","The goblin cautiously eyes you up.",35,10);
        this.health = maxHealth = getMaxHealth();
        damage = getDamage();
        name = getName();
        encounterText =  getEncounterText();
    }
}
