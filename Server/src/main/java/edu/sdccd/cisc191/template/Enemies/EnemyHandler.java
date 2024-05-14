package edu.sdccd.cisc191.template.Enemies;

import java.lang.Math;
import java.util.ArrayList;


public class EnemyHandler {
    private static Enemy enemy;


    /* list of enemies sorted by level
       ex: first array is for level 1, second for level 2, etc
     */
    static String[][] enemyLeveledList =
            {
                    {"Goblin","Grogoroth"}, // level 1, add 1 goblin to enemy pool
                    {"Goblin","Minotaur"},// level 2, add 1 goblin and 1 minotaur
                    {"Minotaur"}, //level 3. add one minotaur
                    {"Grogoroth"}, // level 4
            };

    public static Boolean checkValidEnemy(String name,int level){
        boolean yes = false;
      for (int i=0; i<enemyLeveledList[level].length;i++){
        if (enemyLeveledList[level][i].equals(name)){
            yes = true;
        }
      }
        return yes;
    }

    public static Enemy createEnemy(boolean isRandom,String enemyName,int playerLevel){
        if (isRandom)
        {
            if ((playerLevel)>enemyLeveledList.length)
            {
                playerLevel = enemyLeveledList.length;
                // if player level higher than leveled list, caps player level so it doesn't get index out of bounds error
            }


            ArrayList<String> enemyPool = new ArrayList<>();
            for (int i = 0;i < playerLevel;i++)
            {
                for (int j = 0;j<enemyLeveledList[i].length;j++)
                {
                    if (enemyLeveledList[i][j].equals("") == false)
                    {
                        enemyPool.add(enemyLeveledList[i][j]);
                    }
                }
            }
            /*
            for (String enemy: enemyPool)
            {
                System.out.println(enemy);
            }

             */
            int max = enemyPool.size()-1;
            int min = 0;
            int range = (max - min) + 1;
            int randomNumber = (int)((Math.random() *range)+min);
            enemyName = enemyPool.get(randomNumber);

        }

        System.out.println(enemyName);
        switch(enemyName){
            case "Goblin":
                enemy = new Goblin();
                break;
            case "Grogoroth":
                enemy = new Grogoroth();
                break;
            case "Minotaur":
                enemy = new Minotaur();
                break;
            case "Prowler":
                enemy = new Prowler();
                break;
            case "NightLurker":
                enemy = new NightLurker();
                break;
        }
        return enemy;
    }
}
