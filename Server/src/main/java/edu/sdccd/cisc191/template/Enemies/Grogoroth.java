package edu.sdccd.cisc191.template.Enemies;

import edu.sdccd.cisc191.template.Enemy;
import edu.sdccd.cisc191.template.Player;

public class Grogoroth extends Enemy {

    private int health,maxHealth,damage,damageDealt,skill;

    public String name;
    private void skillPeaceForAll(Player player){

    }

    private void skillDestroy(Player player){
    }

    public String enemyTurn(Player player){
        String returnStr = "Nothing happened.";
        int max = 3;
        int min = 0;
        int range = (max - min) +1;

        skill = (int)((Math.random() *range)+min);

        switch (skill){
            case 1:
           //     damageDealt = skillDestroy(player);
                returnStr = "Grogoroth destroys you.";
                break;
            case 2:
                skillPeaceForAll(player);
                returnStr = "Grogoroth summon peace for all.";
                break;
        }
        return returnStr;
    }

    public Grogoroth(){
        super(1000,50,"Grogoroth, Destroyer of Worlds");
        this.health = maxHealth = 1000;
    }
}
