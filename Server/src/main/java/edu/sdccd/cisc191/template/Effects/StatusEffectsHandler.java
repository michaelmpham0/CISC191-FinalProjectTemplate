package edu.sdccd.cisc191.template.Effects;
import edu.sdccd.cisc191.template.Enemies.Enemy;
import edu.sdccd.cisc191.template.Items;
import edu.sdccd.cisc191.template.Server;
import edu.sdccd.cisc191.template.Player;

import java.util.LinkedList;
public class StatusEffectsHandler {

    public static void applyStatus(StatusEffect appliedStatus,int duration, String target){

        if (target.equals("Player")){
           LinkedList<StatusEffect> playerStatuses =  Server.getPlayer().getPlayerStatusList();
           playerStatuses.addFirst(appliedStatus);
        }
        else {
            LinkedList<StatusEffect> enemyStatuses =  Server.getCurrentEnemy().getEnemyStatusList();
            enemyStatuses.addFirst(appliedStatus);
        }
    }

    public static void checkStatus(){

    }
}
