package edu.sdccd.cisc191.template.Effects;

public interface StatusEffectInterface {
    static String statusName="";
    static int statusDuration=0;

    public static String getStatusName() {
        return statusName;
    }

    public static int getStatusDuration() {
        return statusDuration;
    }

    public static void checkStatus(String target){}

    public static void setStatusDuration(String target){}
}
