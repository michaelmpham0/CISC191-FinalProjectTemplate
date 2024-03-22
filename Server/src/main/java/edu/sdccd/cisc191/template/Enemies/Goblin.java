package edu.sdccd.cisc191.template.Enemies;

import edu.sdccd.cisc191.template.Player;

public class Goblin extends Enemy {
    private int health,maxHealth,damage,damageDealt,skill=0;

    private boolean attacking = false;
    private String encounterText;
    private String action = "None";

    public String name;
    private String Slash(Player player)
    {
        setAttacking(true);
        if (slashCount == 2)
        {
            //stronger slash every 2 slashes
            slashCount = 0;
            damageDealt = 12;
            return "The goblin hacks away at you, drawing a considerable amount of blood.";
        }
        else
        {
            slashCount++;
            damageDealt = 5;
            return "The goblin punctures your flesh with its crude blade.";
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
        setAttacking(true);
        action = "None";
        if (player.getGuarding() == true ) {
            damageDealt = 0;
            return "The goblin leaps at you, but you manage to step out of its way.";
        } else {
            damageDealt = 25;
            return "The goblin leaps onto you and clings onto your face, relentlessly gouging out holes on your face.";
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
            setAttacking(false);
            action = "None";
            int max = 6;
            int min = 0;
            int range = (max - min) +1;
            skill = (int)((Math.random() *range)+min);
            System.out.println(skill);
            damageDealt = 0;
            switch (skill)
            {
                case 1:
                case 2:
                case 3:
                    returnStr = Slash(player);
                    break;
                case 4:
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
        super(80,50,"Goblin","A puny creature scurries towards you.","The goblin cautiously eyes you up.",35,10);
        this.health = maxHealth = getMaxHealth();
        damage = getDamage();
        name = getName();
        encounterText =  getEncounterText();
    }
}
