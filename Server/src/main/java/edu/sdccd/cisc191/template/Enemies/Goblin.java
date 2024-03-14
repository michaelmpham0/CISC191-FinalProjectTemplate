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
        if (slashCount == 3)
        {
            //stronger slash every 3 slashes
            slashCount = 0;
            damageDealt = 10;
            return "The goblin hacks away you, drawing a considerable amount of blood and flesh.";
        }
        else
        {
            slashCount++;
            damageDealt = 5;
            return "The goblin slashes at you with its crude blade.";
        }
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
        if (player.getGuarding() == true ) {
            damageDealt = 0;
            return "The goblin leaps at you, but you manage to step out of its way.";
        } else {
            damageDealt = 18;
            return "The goblin leaps onto you and clings onto your face, relentlessly stabbing you.";
        }

    }

    private int currentTurn = 0;
    private int slashCount = 0;


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
            returnStr = dealDamage(damageDealt,player,returnStr);
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
