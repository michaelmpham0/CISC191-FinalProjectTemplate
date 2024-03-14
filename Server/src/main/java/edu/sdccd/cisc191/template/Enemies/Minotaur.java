package edu.sdccd.cisc191.template.Enemies;

import edu.sdccd.cisc191.template.Enemy;
import edu.sdccd.cisc191.template.GUI.GUIController;
import edu.sdccd.cisc191.template.Player;

public class Minotaur extends Enemy {
    private int health,maxHealth,damage,damageDealt,skill=0;

    private String encounterText;
    private String action = "None";

    public String name;
    private String Swing(Player player)
    {
        if (swingCount == 1)
        {
            //charge every other swing
            swingCount = 0;
            if (player.getGuarding() == true ) {
                damageDealt = 0;
                return "You manage to deftly sidestep just in time, avoiding the deadly thrust of the minotaur.";
            } else {
                damageDealt = 16;
                return "The minotaur charges forward, striking your chest and goring you with brutal horns. ";
            }
        }
        else
        {
            swingCount++;
            damageDealt = 6;
            return "The minotaur cleaves you with its oversized axe.";
        }
    }
    private String MinotaurRoar(Player player)
    {
        action = "MinotaurBeatdown";
        damageDealt = 1;
        return "The minotaur lets out a deafening roar.";
    }
    private String MinotaurBeatdown(Player player)
    {
        action = "None";
        if (player.getGuarding() == true ) {
            damageDealt = 0;
            return "The minotaur attempts to grab you, but you manage to duck its embrace.";
        } else {
            damageDealt = 25;
            return "The minotaur catches you in its grasp, smashing you repeatedly into the ground before tossing you into a wall.";
        }

    }
    private int currentTurn = 0;
    private int swingCount = 0;

    public String enemyTurn(Player player){
        String returnStr = "The minotaur stands up straight, staring at you.";
        currentTurn++;
        if (currentTurn == 1)
        {
            returnStr = MinotaurRoar(player);
        }
        else if (action.equals("MinotaurBeatdown"))
        {
            returnStr = MinotaurBeatdown(player);
        }
        else
        {
            action = "None";
            int max = 6;
            int min = 0;
            int range = (max - min) + 1;
            skill = (int)((Math.random() *range)+min);
            damageDealt = 0;
            switch (skill)
            {
                case 1:
                    returnStr = Swing(player);
                    break;
                case 2:
                    returnStr = Swing(player);
                    break;
                case 3:
                    returnStr = Swing(player);
                    break;
                case 4:
                    returnStr = MinotaurRoar(player);
                case 5:
                    returnStr = MinotaurRoar(player);
            }
        }
        if (damageDealt > 0)
        {
            returnStr = dealDamage(damageDealt,player,returnStr);
        }
        return returnStr;
    }

    public Minotaur(){
        super(400,55,"Minotaur","A hulking bull-like creature stands before you.","The Minotaur appears.",55,35);
        this.health = maxHealth = getMaxHealth();
        damage = getDamage();
        name = getName();
        encounterText =  getEncounterText();
    }
}
