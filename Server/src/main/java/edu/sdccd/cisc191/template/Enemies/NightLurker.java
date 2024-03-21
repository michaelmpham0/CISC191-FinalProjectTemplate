package edu.sdccd.cisc191.template.Enemies;

import edu.sdccd.cisc191.template.Enemy;
import edu.sdccd.cisc191.template.Player;

public class NightLurker extends Enemy {
    private int health,maxHealth,damage,damageDealt,skill=0;

    private String encounterText;
    private boolean attacking = false;
    private String action = "None";

    public String name;
    private String Stab(Player player)
    {
        setAttacking(true);
        damageDealt = 10;
        return "The creature quickly stabs you then retreat back into the shadow.";
    }
    private String Pounce(Player player)
    {
        setAttacking(true);
        action = "None";
        if (player.getGuarding() == true ) {
            damageDealt = 0;
            return "Hearing the sounds of footsteps behind you, you immediately drop on the ground, barely avoiding the creature before it lunges at you";
        } else {
            damageDealt = 20;
            return "The lurking creature lunges itself at you, thrusting you with its spear";
        }
    }

    private int currentTurn = 0;

    public String enemyTurn(Player player){
        String returnStr = "The creatures stares at you from the shadow with its bloody, bright red eyes";
        currentTurn++;
        damageDealt = 0;
        if (currentTurn == 1)
        {
            setAttacking(false);
            //is invincible in the mist on the first turn, and will always pounce on the next turn
            returnStr = "You felt like you're being watched within the dark shadow.";
            action = "Pounce";
            return returnStr;
        }
        else if (action.equals("Pounce"))
        {
            // turn 2
            setDefenseMultiplier(1);
            returnStr = Pounce(player);
        }
        else if (currentTurn == 3 || currentTurn == 4)
        {
            setDefenseMultiplier(1);
            returnStr = Stab(player);
        }
        else if (currentTurn == 5)
        {
            setAttacking(false);
            // turn 5, opporunity given to player
            returnStr = "The creature hissed, angry as it fails to defeat you.";
        }
        else
        {
            setAttacking(false);
            setDefenseMultiplier(0.0);
            //turn 6, return to the dark and repeat the pattern
            returnStr = "The creature retreats and disappear into the dark abyss.";
            currentTurn = 0;
        }
        if (damageDealt > 0)
        {
            returnStr = dealDamage(damageDealt,player,returnStr);
        }
        return returnStr;
    }

    public NightLurker(){
        super(50,20,"The Bizarre Night Lurker","You sense a mysterious presence somewhere in the shadow","A pair of bloody, red eyes appear from the gloomy walls .",75,30);
        this.health = maxHealth = getMaxHealth();
        damage = getDamage();
        name = getName();
        encounterText =  getEncounterText();
        setDefenseMultiplier(0.0);
        //invincible on the first turn
    }
}
