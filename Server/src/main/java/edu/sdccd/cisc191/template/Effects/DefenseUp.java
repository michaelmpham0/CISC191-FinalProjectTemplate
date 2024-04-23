package edu.sdccd.cisc191.template.Effects;

import edu.sdccd.cisc191.template.Entity;

public class DefenseUp extends StatusEffect{
    static String statusName="";
    static String statusType="Other";
    static int statusDuration=0;

    public DefenseUp(int duration){
        super("DefenseUp",duration);
        statusName="DefenseUp";
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

    @Override
    public String activateStatus(Entity target){
        String string="nob";

        return string;
    }
}
