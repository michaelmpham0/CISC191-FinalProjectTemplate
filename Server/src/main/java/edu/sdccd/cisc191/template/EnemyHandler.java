package edu.sdccd.cisc191.template;
import java.lang.Math;
import java.util.HashMap;

public class EnemyHandler {

    //int array stats in order: Health, Damage
    static HashMap<String, int[]> enemyList = new HashMap<String, int[]>()
    {{
        put("Grogorath, Destroyer of Worlds", new int[]{1000,5});
        put("Evil Bob", new int[]{25,100});
        put("Test", new int[]{1,1});
    }};

    public static Enemy createEnemy(boolean isRandom,String name, int health,int damage){
        if (isRandom) {
            int max = 2;
            int min = 0;
            int range = (max - min) +1;

            int randomChance = (int)((Math.random() *range)+min);

            System.out.println(randomChance);

            String enemyArray[] = {"Grogorath, Destroyer of Worlds","Evil Bob","Test"};

            String enemyName = enemyArray[randomChance];

            Enemy enemy = new Enemy(enemyList.get(enemyName)[0],enemyList.get(enemyName)[1],enemyName);
            return enemy;
        }
        else {
            Enemy enemy = new Enemy(health,damage,name);
            return enemy;
        }
    }
}
