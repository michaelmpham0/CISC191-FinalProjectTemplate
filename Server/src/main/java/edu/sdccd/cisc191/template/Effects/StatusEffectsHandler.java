package edu.sdccd.cisc191.template.Effects;
import edu.sdccd.cisc191.template.Enemies.Enemy;
import edu.sdccd.cisc191.template.Entity;
import edu.sdccd.cisc191.template.Items;
import edu.sdccd.cisc191.template.Server;
import edu.sdccd.cisc191.template.Player;

import java.util.LinkedList;
public class StatusEffectsHandler {

    public static void applyStatus(StatusEffect appliedStatus, Entity target){

        LinkedList<StatusEffect> targetStatusList = target.getStatusList();
        targetStatusList.addFirst(appliedStatus);

    }

    public static void deleteStatus(StatusEffect deletedStatus, Entity target){

        LinkedList<StatusEffect> targetStatusList = target.getStatusList();
        int count=0;
        for (StatusEffect currentStatus : targetStatusList) {
            if (currentStatus.getStatusName().equals(deletedStatus.getStatusName())){
                targetStatusList.remove(count);
            }
            count++;
        }
    }

    public static void checkStatus(){

    }
}
