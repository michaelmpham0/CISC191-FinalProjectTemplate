package edu.sdccd.cisc191.template;

import java.util.HashMap;

public class StatusHandler {

    static HashMap<String, Integer> Statuses = new HashMap<String, Integer>()
    {
    };

    public HashMap<String,Integer> getAllStatus() {return Statuses;}
    public boolean hasStatuses() {return Statuses.isEmpty();}

    public boolean getStatus(String status ){return Statuses.containsKey(status);}

    public void setStatus(String status,int statusTime)
    {
        if (Statuses.containsKey(status)) {
            if (statusTime>Statuses.get(status)){
                Statuses.replace(status,statusTime);
            }
        }
        else {
            Statuses.put(status,statusTime);
        }
    }

    public String checkStatus(String status,boolean attacking,){
        String returnString = "Unknown.";

        switch (status){
            case "Bleed":
                if (Statuses.get("Bleed")>0){
                    Statuses.replace("Bleed",Statuses.get("Bleed")-1);
                    if (attacking) {
                        double bleedDamage = (((double) (maxHealth - health) / maxHealth) * (maxHealth * 0.25));

                        if (bleedDamage <= 0) {
                            bleedDamage = 1;
                        }
                        returnString = name + " bleeds for " + bleedDamage + " damage!";
                        takeDamage((int) bleedDamage);
                    }
                    else {
                        returnString = name + " bleeds for " + ((int)(maxHealth * 0.01)) + " damage!";
                        takeDamage((int) (maxHealth * 0.01));
                    }
                }
                else {
                    Statuses.remove("Bleed");
                    returnString = name + " stops bleeding.";
                }
                break;
            case "Burn":
                if (Statuses.get("Burn")>0) {
                    Statuses.replace("Burn",Statuses.get("Burn")-1);
                    returnString = name + " burns for " + ((int) (maxHealth * 0.05)) + " damage!";
                    takeDamage((int) (maxHealth * 0.05));
                }
                else {
                    Statuses.remove("Burn");
                    returnString = name + " stops burning.";
                }
                break;
            case "Paralyze":
                if (Statuses.get("Paralyze")>0) {
                    Statuses.replace("Paralyze",Statuses.get("Paralyze")-1);
                    returnString = name + " is paralyzed!";
                }
                else {
                    Statuses.remove("Paralyze");
                    returnString = name + " stops being paralyzed.";
                }
                break;
        }
        return returnString;
    }


}
