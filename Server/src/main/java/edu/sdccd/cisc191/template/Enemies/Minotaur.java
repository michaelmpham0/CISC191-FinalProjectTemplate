package edu.sdccd.cisc191.template.Enemies;

import edu.sdccd.cisc191.template.Enemy;
import edu.sdccd.cisc191.template.GUI.GUIController;
import edu.sdccd.cisc191.template.Player;

public class Minotaur extends Enemy {
    private int health,maxHealth,damage,damageDealt,skill=0;

    private String encounterText;
    private String action = "None";

    public String name;
    private String Charge(Player player)
    {
        damageDealt = 15;
        return "The Minotaur charges at you aggressively and swing its axe at you.";
    }
    private String MinotaurRoar(Player player)
    {
        action = "MinotaurRoar";
        damageDealt = 1;
        return "The Minotaur roar loudly, causing your ears to bleed.";
    }
    private String MinotaurBeatdown(Player player)
    {
        action = "None";
        damageDealt = 20;
        return "The Minotaur grabs you, smashing you repeatedly into the ground then throws you into a wall.";
    }

    private int currentTurn = 0;

    public String enemyTurn(Player player){
        String returnStr = "The Minotaur makes its stance, staring into your soul.";
        currentTurn++;
        if (currentTurn == 1)
        {
            return returnStr;
        }
        else if (action.equals("MinotaurBeatdown"))
        {
            returnStr = MinotaurBeatdown(player);
        }
        else
        {
            action = "None";
            int max = 10;
            int min = 0;
            int range = (max - min) + 3;
            skill = (int)((Math.random() *range)+min);
            damageDealt = 0;
            switch (skill)
            {
                case 1:
                    returnStr = Charge(player);
                    break;
                case 2:
                    returnStr = Charge(player);
                    break;
                case 3:
                    returnStr = Charge(player);
                    break;
                case 4:
                    returnStr = MinotaurRoar(player);
                case 5:
                    returnStr = MinotaurRoar(player);
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

    public Minotaur(){
        super(400,50,"Minotaur","A giant bull-like creature stands before you.","The Minotaur appears.",55,35);
        this.health = maxHealth = getMaxHealth();
        damage = getDamage();
        name = getName();
        encounterText =  getEncounterText();
    }
}
