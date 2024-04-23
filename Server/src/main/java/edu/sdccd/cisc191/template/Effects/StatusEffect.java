package edu.sdccd.cisc191.template.Effects;

import edu.sdccd.cisc191.template.Entity;

public abstract class StatusEffect implements StatusEffectInterface{

    static String statusName="";
    static int statusDuration=0;

    public StatusEffect(String name, int duration){
        statusName=name;
        statusDuration=duration;
    }
    public static String getStatusName() {
        return statusName;
    }

    public static int getStatusDuration() {
        return statusDuration;
    }

    public static void checkStatus(String target){}

    public static void setStatusDuration(String target){}

    public abstract String activateStatus(Entity target);
}
