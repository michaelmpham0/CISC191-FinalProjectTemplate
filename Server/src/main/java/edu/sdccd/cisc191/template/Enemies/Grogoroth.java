package edu.sdccd.cisc191.template.Enemies;

import edu.sdccd.cisc191.template.Enemy;
import edu.sdccd.cisc191.template.Player;

public class Grogoroth extends Enemy {

    private int health,maxHealth,damage,damageDealt,skill=0;

    public String name;
    private int skillPeaceForAll(){
        return (damage/2);
    }

    private int skillDestroy(){
        return (damage/3);
    }

    public String enemyTurn(Player player){
        String returnStr = "Nothing happened.";
        int max = 3;
        int min = 0;
        int range = (max - min) +1;

        skill = (int)((Math.random() *range)+min);

        switch (skill){
            case 1:
                damageDealt = skillDestroy();
                returnStr = "Grogoroth destroys you. You took " + damageDealt + " damage!";
                break;
            case 2:
                damageDealt = skillPeaceForAll();
                returnStr = "Grogoroth summon peace for all. You took " + damageDealt + " damage!";
                break;
        }
        player.setHealth(-damageDealt);
        return returnStr;
    }

    public Grogoroth(){
        super(1000,50,"Grogoroth, Destroyer of Worlds");
        this.health = maxHealth = 1000;
        damage = 50;
        name = "Grogoroth, Destroyer of Worlds";
    }
}
