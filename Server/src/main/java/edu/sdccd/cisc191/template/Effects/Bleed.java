package edu.sdccd.cisc191.template.Effects;

import edu.sdccd.cisc191.template.Entity;

public class Bleed extends StatusEffect{
    static String statusName="";
    static String statusType="Damage";
    static int statusDuration=0;

    public Bleed(int duration){
        super("Bleed",duration);
        statusName="Bleed";
        statusDuration=duration;
    }

    public static String getStatusName() {
        return statusName;
    }

    public static int getStatusDuration() {
        return statusDuration;
    }

    public static void checkStatus(Entity target){}
    public static void setStatusDuration(Entity target){}

    public static String activateStatus(Entity target){
        String string;
        int maxHealth= target.getMaxHealth();;
        int currentHealth= target.getHealth();;
        String name = target.getName();
        if (target.getStatus().equals("Attacking")) {
            double bleedDamage = (((double) (maxHealth - currentHealth) / maxHealth) * (maxHealth * 0.25));

            if (bleedDamage <= 0) {
                bleedDamage = 1;
            }
            string = name + " bleeds for " + bleedDamage + " damage!";
            target.takeDamage((int) bleedDamage);
        }
        else {
            string = name + " bleeds for " + ((int)(maxHealth * 0.01)) + " damage!";
            target.takeDamage((int) (maxHealth * 0.01));
        }
        return string;
    }
}
