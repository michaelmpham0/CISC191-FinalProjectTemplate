package edu.sdccd.cisc191.template;

import edu.sdccd.cisc191.template.GUI.GUIController;

import java.io.Serializable;

public class Abilities implements Serializable {
    public String abilityName;
    public String abilityDesc;
    public String useDesc;

    //if ability's damage is 0, the ability will purely have a special function
    private int abilityDamage;
    private int manaCost;

    public Abilities() {
        abilityName = "unknown";
        abilityDesc = "unknown";
        useDesc = "...";
        abilityDamage = 0;
        manaCost = 0;
    }
    public Abilities(String inName, String inDesc,String useDesc,int manaCost,int abilityDamage){
        abilityName = inName;
        abilityDesc = inDesc;
        this.manaCost = manaCost;
        this.useDesc = useDesc;
        this.abilityDamage = abilityDamage;
    }

    public String getAbilityName() {
        return abilityName;
    }
    public String getAbilityDesc(){
        return abilityDesc;
    }
    public String getAbilityUseDesc(){
        return useDesc;
    }
    public int getAbilityCost(){
        return manaCost;
    }
    public int getAbilityDamage(){
        return abilityDamage;
    }

    public void setAbilityName(String inName){
        abilityName = inName;
    }
    public void setAbilityDesc(String inDesc){
        abilityDesc = inDesc;
    }

    public void useAbility(Player player)
    {
        System.out.println("Tried To Use: "+abilityName);
        if (!(Server.getCurrentEnemy()== null) && abilityDamage > 0) {
            if (player.getMana() >= manaCost)
            {
                GUIController.setFumbleSpell(false);
                player.setMana(player.getMana()-manaCost);
                switch (abilityName)
                {
                    case "Horizontal":
                        Server.getCurrentEnemy().setStatus("Bleed",3);
                        break;
                    case "Firebolt":
                        Server.getCurrentEnemy().setStatus("Burn",3);
                        break;
                    case "Punching":
                        Server.getCurrentEnemy().setStatus("Paralyze",1);
                        break;
                    default:
                        System.out.println("Ability Not Usable");
                }
            }
            else
            {
                GUIController.setFumbleSpell(true);
                System.out.println("Could not use "+abilityName+", low mana.");
            }
        }
        else if ((abilityDamage > 0)){
            System.out.println("Could not use "+abilityName+", no target.");
        }
        else {
            if (player.getMana() >= manaCost)
            {
                GUIController.setFumbleSpell(false);
                player.setMana(player.getMana()-manaCost);
                switch (abilityName)
                {
                    case "fortnite shield pot":
                        System.out.println("Gulp gulp guilp");
                        break;
                    default:
                        System.out.println("Ability Not Usable");
                }
            }
            else {
                GUIController.setFumbleSpell(true);
                System.out.println("Could not use "+abilityName+", low mana.");
            }
        }
    }
    @Override
    public String toString() {
        return abilityName;
    }
}
