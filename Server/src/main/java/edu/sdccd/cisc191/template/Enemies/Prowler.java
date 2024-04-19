package edu.sdccd.cisc191.template.Enemies;

import edu.sdccd.cisc191.template.Player;

public class Prowler extends Enemy {
    private int health,maxHealth,damage,damageDealt,skill=0;

    private String encounterText;
    private boolean attacking = false;
    private String action = "None";

    public String name;
    private String Claw(Player player)
    {
        setAttacking(true);
        damageDealt = 12;
        return "The beast rabidly claws at you.";
    }
    private String Pounce(Player player)
    {
        setAttacking(true);
        action = "None";
        if (player.getGuarding() == true ) {
            damageDealt = 0;
            return "Guided by instinct, you leap to the side, barely catching a glimpse of a blackened blur zip past your vision.";
        } else {
            damageDealt = 100;
            return "The lurking entity launches itself at you before you can even react.";
        }
    }

    private int currentTurn = 0;



    public String enemyTurn(Player player){
        String returnStr = "The beast stalks you from the dark.";
        // I think bosses should have a set pattern instead of randomness?
        currentTurn++;
        damageDealt = 0;
        if (currentTurn == 1)
        {
            setAttacking(false);
            //is invincible in the mist on the first turn, and will always pounce on the next turn
            returnStr = "Something is stalking you from within the mist.";
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
            returnStr = Claw(player);
        }
        else if (currentTurn == 5)
        {
            setAttacking(false);
            // turn 5, opporunity given to player
            returnStr = "The beast is exhausted. It pants in frustation at your unexpected survival.";
        }
        else
        {
            setAttacking(false);
            setDefenseMultiplier(0.0);
            //turn 6, return to the mist and repeat the pattern
            returnStr = "The beast fades away into the mist.";
            currentTurn = 0;
        }
        if (damageDealt > 0)
        {
            returnStr = dealDamage(damageDealt,player,returnStr);
        }
        return returnStr;
    }

    public Prowler(){
        super(100,50,"The Prowling Beast","???","A veil of mist envelopes the area in front of you.",100,25,200);
        this.health = maxHealth = getMaxHealth();
        damage = getDamage();
        name = getName();
        encounterText =  getEncounterText();
        setDefenseMultiplier(0.0);
        //invicible on the first turn
    }
}
