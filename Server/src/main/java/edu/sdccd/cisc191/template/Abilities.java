package edu.sdccd.cisc191.template;

import edu.sdccd.cisc191.template.GUI.GUIController;

import java.io.Serializable;

public class Abilities implements Serializable {
    private String abilityName;
    private String abilityDesc;
    private String useDesc;
    private String scalingType;

     private int oldAttack;
    private double abilityScaling;

    //if ability's damage is 0, the ability will purely have a special function
    private int abilityDamage;

    //when abilities modify their own damage
    private int changedDamage = 0;

    //turn based abilities
    private int previousTurn,turns=0;
    private final int manaCost;

    public Abilities() {
        abilityName = "unknown";
        abilityDesc = "unknown";
        useDesc = "...";
        abilityDamage = 0;
        manaCost = 0;
        scalingType = "Linear";
        abilityScaling =1.0;
    }
    public Abilities(String inName, String inDesc,String useDesc,int manaCost,int abilityDamage,double abilityScaling,String scalingType){
        abilityName = inName;
        abilityDesc = inDesc;
        this.manaCost = manaCost;
        this.useDesc = useDesc;
        this.abilityDamage = abilityDamage;
        this.abilityScaling = abilityScaling;
        this.scalingType = scalingType;
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
    public int getChangedDamage(){return changedDamage;}
    public void resetTurns(){
        previousTurn=turns=0;
    }
    public void setAbilityName(String inName){
        abilityName = inName;
    }
    public void setAbilityDesc(String inDesc){
        abilityDesc = inDesc;
    }

    public void scaleAbility(int level){
        int oldDamage = abilityDamage;
        switch (scalingType){
            case "Linear":
                abilityDamage+= (int) abilityScaling;
                break;
            case "Exponential":
                abilityDamage*= (int) abilityScaling;
                break;
        }
        try {
            useDesc = String.format(useDesc.replace(Integer.toString(oldDamage), "%d"), abilityDamage);
            abilityDesc = String.format(abilityDesc.replace(Integer.toString(oldDamage), "%d"), abilityDamage);
        }
        catch (NullPointerException e) {
            System.err.println("Ability does no damage, cannot scale.");
        }
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
                    case "Charge Shot":
                        if (turns==0){
                            previousTurn = 1;
                            turns=1;
                            changedDamage=abilityDamage;
                        }
                        else {
                            turns++;
                            if ((turns-previousTurn)>1){
                                changedDamage+=abilityDamage;
                            }
                        }
                        break;
                    default:
                        System.err.println("Ability Not Found");
                }
            }
            else
            {
                GUIController.setFumbleSpell(true);
                System.err.println("Could not use "+abilityName+", low mana.");
            }
        }
        else if ((abilityDamage > 0)){
            System.err.println("Could not use "+abilityName+", no target.");
        }
        else {
            if (player.getMana() >= manaCost)
            {
                GUIController.setFumbleSpell(false);
                player.setMana(player.getMana()-manaCost);
                switch (abilityName)
                {
                    case "Rage":
                        player.setStatus("ATKBoost",3);
                        player.setStatus("Burn",3);
                        player.setStatus("Bleed",3);
                        break;
                    case "Heal":
                        player.restoreHealth(player.getMaxHealth()/2);
                        break;
                    case "Quickness":
                        player.setStatus("Guard",3);
                    default:
                        System.err.println("Ability Not Usable");
                }
            }
            else {
                GUIController.setFumbleSpell(true);
                System.err.println("Could not use "+abilityName+", low mana.");
            }
        }
    }
    @Override
    public String toString() {
        return abilityName;
    }
}
