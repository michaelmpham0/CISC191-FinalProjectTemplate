package edu.sdccd.cisc191.template.Effects;

public class Bleed extends StatusEffect{
    static String statusName="";
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

    public static void checkStatus(String target){}

    public static void modifyStatus(String target){}
}
