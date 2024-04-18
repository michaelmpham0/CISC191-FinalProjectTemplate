package edu.sdccd.cisc191.template.Effects;
import edu.sdccd.cisc191.template.Entity;

import java.util.Iterator;
import java.util.LinkedList;
public class StatusEffectsHandler {

    public static void applyStatus(StatusEffect appliedStatus, Entity target) {
        LinkedList<StatusEffect> targetStatuses = target.getStatusList();
        targetStatuses.addFirst(appliedStatus);
    }

    public static void activateAllStatuses(Entity target){
        LinkedList<StatusEffect> targetSEList = target.getStatusList();
        Iterator<StatusEffect> seIterator = targetSEList.iterator();
    }
}
