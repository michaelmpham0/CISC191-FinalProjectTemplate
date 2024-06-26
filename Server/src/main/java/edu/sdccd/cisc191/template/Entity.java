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

    public void takeDamage(int dmg){
        HP -= dmg;
    }

    public LinkedList<StatusEffect> getStatusList(){
        return statusEffectsLinkedList;
    }
    public int numberOfStatusesInList(){return statusEffectsLinkedList.size();}

    public String getName() {return name;}

    public int getHealth(){return  HP;}

    public int getMaxHealth() {return maxHP;}
}
