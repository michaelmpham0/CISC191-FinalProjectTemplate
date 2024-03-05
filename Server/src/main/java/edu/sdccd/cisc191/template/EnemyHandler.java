package edu.sdccd.cisc191.template;
import edu.sdccd.cisc191.template.Enemies.Grogoroth;

import java.lang.Math;
import java.util.HashMap;

/**
 * 1 - Grogoroth
 * 2 - Demon
 */

public class EnemyHandler {

    private static int enemyID = 0;
    private static Enemy enemy;
    public static Enemy createEnemy(boolean isRandom,int ID){
        if (isRandom) {
            int max = 2;
            int min = 0;
            int range = (max - min) +1;

            enemyID = (int)((Math.random() *range)+min);
        }
        else {
           enemyID = ID;
        }
        switch(enemyID){
            case 1:
                enemy = new Grogoroth();
                break;
            case 2:
                enemy = new Grogoroth();
                break;
        }
        return enemy;
    }
}
