package edu.sdccd.cisc191.template.Enemies;

import edu.sdccd.cisc191.template.Enemy;

public class Grogoroth extends Enemy {

    private int health,maxHealth,damage,skill;

    private String name;
    private void skillPeaceForAll(){

    }

    private void skillDestroy(){
    }

    public String enemyTurn(){
        String returnStr = "Nothing happened.";
        int max = 3;
        int min = 0;
        int range = (max - min) +1;

        skill = (int)((Math.random() *range)+min);

        switch (skill){
            case 1:
                skillDestroy();
                returnStr = "Grogoroth destroys you.";
                break;
            case 2:
                skillPeaceForAll();
                returnStr = "Grogoroth summon peace for all.";
                break;
        }
        return returnStr;
    }

    public Grogoroth(){
        super(1000,50,"Grogoroth, Destroyer of Worlds");
        this.health = maxHealth = 1000;
        damage = 50;
        name = "Grogoroth, Destroyer of Worlds";
    }
}
