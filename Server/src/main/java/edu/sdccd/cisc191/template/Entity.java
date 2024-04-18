package edu.sdccd.cisc191.template;

import edu.sdccd.cisc191.template.Effects.StatusEffect;

import java.util.LinkedList;

public class Entity {
    String name = "";
    String status = "None";
    int HP=0;
    int maxHP=0;
    LinkedList<StatusEffect> statusEffectsLinkedList = new LinkedList<>();

    public String getStatus(){return status;}

    public int takeDamage(int dmg){
        return(HP-=dmg);
    }

    public LinkedList<StatusEffect> getStatusList(){
        return statusEffectsLinkedList;
    }

    public String getName() {return name;}

    public int getHealth(){return  HP;}

    public int getMaxHealth() {return maxHP;}
}
