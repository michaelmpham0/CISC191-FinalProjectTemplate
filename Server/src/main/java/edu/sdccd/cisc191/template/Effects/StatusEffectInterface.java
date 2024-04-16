package edu.sdccd.cisc191.template.Effects;

public interface StatusEffectInterface {
    static String statusName="";
    static int statusDuration=0;

    public static void checkStatuses(String target){}

    public static void modifyStatus(String target){}

    public static String getStatusName() {
        return statusName;
    }

    public static int getStatusDuration() {
        return statusDuration;
    }
}
