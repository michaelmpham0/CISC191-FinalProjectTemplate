package edu.sdccd.cisc191.template.GUI;

import edu.sdccd.cisc191.template.Enemies.Enemy;
import edu.sdccd.cisc191.template.Enemies.EnemyHandler;
import javafx.application.Platform;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ProgressBar;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.animation.PauseTransition;
import javafx.util.Duration;

import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;

public class CombatMenu extends GUIController{
    public static int delayTime = 0;
    public static int turn = 1;

    private static boolean charged=false;
    protected static int statusLoop(Label name, Label health,Label acts,boolean isEnemy,boolean attacking){
        int count=0;

        if (isEnemy && !currentEnemy.hasStatuses()){
            HashMap<String,Integer> enemyStatus = currentEnemy.getAllStatus();
            int i = -1;
            for (Map.Entry<String, Integer> entry : enemyStatus.entrySet()) {
                i++;
                count++;

                String key = entry.getKey();
                Integer value = entry.getValue();
                System.out.println("Enemy Status: " + key + ", Time: " + value);

                PauseTransition dl = new PauseTransition(Duration.seconds(i+1));

                dl.setOnFinished(evt -> {
                    refreshGUI(name, health, acts, currentEnemy, "Enemy", currentEnemy.checkStatus(entry.getKey()));
                });
                dl.play();
            }
        }
        else if (!player.hasStatuses()){
            HashMap<String,Integer> playerStatus = player.getAllStatus();
            int i = -1;
            for (Map.Entry<String, Integer> entry : playerStatus.entrySet()) {
                i++;
                count++;

                String key = entry.getKey();
                Integer value = entry.getValue();
                System.out.println("Player Status: " + key + ", Time: " + value);

                PauseTransition dl = new PauseTransition(Duration.seconds(i+1));

                dl.setOnFinished(evt -> {
                    refreshGUI(name, health, acts, currentEnemy, "PlayerStatus", player.checkStatus(entry.getKey(),attacking));
                });
                dl.play();
            }
        }
        return count;
    }

   protected  static  void refreshGUI(Label name, Label health,Label acts,Enemy enemy,String action,String act){
      if (action.equals("Attack")) {
          if (enemy.getDefenseMultiplier() == 0.0 && enemy.getName().equals("The Prowling Beast"))
          {
              acts.setText("You pointlessly attack the mist. Nothing happens.");
          }
          else
          {
              int damageDealt;
              name.setText(enemy.getName());
              if (charged) {
                  damageDealt = (int) (previousSpell.getChangedDamage()*enemy.getDefenseMultiplier());
                  previousSpell.resetTurns();
              }
              else {
                  damageDealt= (int) ((player.getAttack()+player.getCurrentWeapon().getWeaponDamage())*enemy.getDefenseMultiplier());
              }
              enemy.takeDamage(damageDealt);
              health.setText("Health: " + enemy.getHealth() + "/" + enemy.getMaxHealth());
              acts.setText(player.getName() + " attacks " + enemy.getName()  + " for " + damageDealt + " damage.");
          }
      }
      else if (action.equals("Defend"))
      {
          acts.setText(player.getName()+" prepares to defend against an attack.");
      }
      else if (action.equals("UseSpell") && usedSpell.getAbilityDamage()>0)
      {
          if (enemy.getDefenseMultiplier() == 0.0 && enemy.getName().equals("The Prowling Beast"))
          {
              acts.setText("You pointlessly attack the mist. Nothing happens.");
          }
          else
          {
              if (!fumbleSpell)
              {
                  name.setText(enemy.getName());
                  if (!usedSpell.getAbilityName().equals("Charge Shot")){
                      enemy.takeDamage(usedSpell.getAbilityDamage());
                  }
                  else {
                      charged = true;
                  }
                  health.setText("Health: " + enemy.getHealth() + "/" + enemy.getMaxHealth());
                  acts.setText(act);
              }
              else
              {
                  acts.setText("You did not have enough mana to use that spell.");
              }
          }
      }
      else {
          if (enemy.getDefenseMultiplier() != 0.0 && enemy.getName().equals("The Prowling Beast"))
          {
              //reveals the prowling beast's name when you defend against it and it's not in the mist
              name.setText(enemy.getName());
          }
          health.setText("Health: " + enemy.getHealth() + "/" + enemy.getMaxHealth());
          acts.setText(act);
       }

       if (enemy.getHealth() <= 0) {
           name.setText(enemy.getName() + " has perished!");
           if (enemy.getXpReward()+player.getExperience()>=player.getMaxExperience())
           {
               // Leveled Up
               acts.setText("You gained "+enemy.getXpReward()+" XP and "+enemy.getGoldReward()+" gold. You leveled up.");
           }
           else
           {
               acts.setText("You gained "+enemy.getXpReward()+" XP and "+enemy.getGoldReward()+" gold.");
           }

           player.gainExperience(enemy.getXpReward());
           player.setGold(player.getGold()+enemy.getGoldReward());
       }

   }
    protected static void combatMenu()
    {
        turn = 1;

        previousStage = "Combat";
        BorderPane root = new BorderPane();
        root.getStylesheets().add("styleSheet.css");
        root.setPrefSize(screenWidth,screenHeight);

        VBox vBox = new VBox();
        root.setCenter(vBox);
        vBox.setAlignment(Pos.CENTER);
        vBox.setMaxSize(screenWidth*0.9,screenHeight*0.9);
        vBox.setSpacing(0.0);
        vBox.setPadding(new Insets(0,0,0,0));

        vBox.getStylesheets().add("styleSheet.css");
        //vBox.getStyleClass().add("borders");

        /*
        HBox barContainer = new HBox();
        barContainer.getStylesheets().add("styleSheet.css");
        barContainer.getStyleClass().add("noBorder");
        barContainer.setAlignment(Pos.CENTER);
        barContainer.setPrefSize(screenWidth*0.8,screenHeight*0.025);
        barContainer.setMinSize(screenWidth*0.8,screenHeight*0.025);
        barContainer.setMaxSize(screenWidth*0.8,screenHeight*0.025);
        barContainer.setTranslateY(screenHeight*0);
        barContainer.setPadding(new Insets(screenWidth*0.015,screenWidth*0.015,screenWidth*0.015,screenWidth*0.015));
        barContainer.setSpacing(screenWidth*0.025);
        vBox.getChildren().add(barContainer);

         */

        // code chunk for health and mana bars
        Label healthBarText = createLabel(player.getHealth()+"/"+player.getMaxHealth(),"Times New Roman",200,0.075,0.025);
        healthBarText.getStyleClass().add("noBorder");
        healthBarText.setTranslateY(screenHeight*-0.01);
        currentHealthBarText = healthBarText;
        vBox.getChildren().add(healthBarText);
        ProgressBar healthBar = createHealthBar();
        currentHealthBar = healthBar;
        vBox.getChildren().add(healthBar);
        Label manaBarText = createLabel(player.getMana()+"/"+player.getMaxMana(),"Times New Roman",200,0.075,0.025);
        manaBarText.getStyleClass().add("noBorder");
        manaBarText.setTranslateY(screenHeight*0.025);
        currentManaBarText = manaBarText;
        vBox.getChildren().add(manaBarText);
        ProgressBar manaBar = createManaBar();
        currentManaBar = manaBar;
        vBox.getChildren().add(manaBar);


        // code chunk for turn counter
        Label turnCounter = createLabel("Turn: 1","Times New Roman",150,0.075,0.025);
        turnCounter.setTranslateY(screenHeight*0.1);
        vBox.getChildren().add(turnCounter);


        // button container to hold the 6 buttons of the player in combat
        HBox buttonContainer = new HBox();
        buttonContainer.getStylesheets().add("styleSheet.css");
        buttonContainer.getStyleClass().add("borders");
        buttonContainer.setAlignment(Pos.CENTER);
        buttonContainer.setPrefSize(screenWidth*0.8,screenHeight*0.1);
        buttonContainer.setMinSize(screenWidth*0.8,screenHeight*0.1);
        buttonContainer.setMaxSize(screenWidth*0.8,screenHeight*0.1);
        buttonContainer.setTranslateY(screenHeight*0.125);
        buttonContainer.setPadding(new Insets(screenWidth*0.015,screenWidth*0.015,screenWidth*0.015,screenWidth*0.015));
        buttonContainer.setSpacing(screenWidth*0.025);
        vBox.getChildren().add(buttonContainer);



        //enemy GUI container, basically the border holding the enemies
        BorderPane enemyGUIContainer = new BorderPane();
        enemyGUIContainer.setPadding(new Insets(screenWidth*0.015,0,screenWidth*0.015,0));
        enemyGUIContainer.getStylesheets().add("styleSheet.css");
        enemyGUIContainer.getStyleClass().add("borders");
        enemyGUIContainer.setPrefSize(screenWidth*0.8,screenHeight*0.4);
        enemyGUIContainer.setMinSize(screenWidth*0.8,screenHeight*0.4);
        enemyGUIContainer.setMaxSize(screenWidth*0.8,screenHeight*0.4);
        enemyGUIContainer.setTranslateY(screenHeight*0.05);
        enemyGUIContainer.setTranslateX(screenWidth*0.1);
        root.setTop(enemyGUIContainer);

        if (player.getFoughtProwler() == false)
        {
            player.setFoughtProwler(true);
            currentEnemy = EnemyHandler.createEnemy(false,"Prowler",player.getLevel());
        }
        else
        {
            currentEnemy = EnemyHandler.createEnemy(true,"Random",player.getLevel());
        }


        Label allActions = new Label();
        allActions.getStylesheets().add("styleSheet.css");
        allActions.setText(currentEnemy.getFirstText());
        allActions.setFont(new Font("Times New Roman",(screenHeight+screenWidth)/100 ));
        allActions.setTranslateY(-screenHeight*0.45);
        BorderPane.setAlignment(allActions,Pos.CENTER);

        root.setBottom(allActions);

        //intro text
        Label introText = new Label();
        introText.getStylesheets().add("styleSheet.css");
        introText.setText(currentEnemy.getEncounterText());
        introText.setFont(new Font("Times New Roman",(screenHeight+screenWidth)/100 ));
        BorderPane.setAlignment(introText,Pos.CENTER);

        enemyGUIContainer.setTop(introText);

        //enemy stats
        Label enemyStats = new Label();
        enemyStats.getStylesheets().add("styleSheet.css");
        enemyStats.setText("Health: " + currentEnemy.getHealth() + "/" + currentEnemy.getMaxHealth());
        enemyStats.setFont(new Font("Times New Roman",(screenHeight+screenWidth)/100));
        BorderPane.setAlignment(enemyStats,Pos.CENTER);

        enemyGUIContainer.setBottom(enemyStats);

        double imageWidth = (screenHeight+screenWidth)/11;
        double imageHeight = (screenHeight+screenWidth)/11;


        //image container for enemies
        Label imageContainer = createLabel("","Times New Roman",0,imageWidth,imageHeight);
        imageContainer.setMinSize(imageWidth,imageHeight);
        imageContainer.setMaxSize(imageWidth,imageHeight);
        imageContainer.getStyleClass().add("noBorder");
        imageContainer.setAlignment(Pos.CENTER);
        enemyGUIContainer.setCenter(imageContainer);
        InputStream inputStream = null;
        try
        {
            inputStream = CombatMenu.class.getResourceAsStream("/Images/"+currentEnemy.getName()+".png");
        }
        catch (NullPointerException ex)
        {
            System.out.println("NO PICTURE FOR: "+currentEnemy.getName());
        }
        finally
        {
            if (inputStream == null)
            {
                inputStream = CombatMenu.class.getResourceAsStream("/Images/Placeholder.png");
            }
        }
        Image newImage = new Image(inputStream);
        ImageView imageView = new ImageView(newImage);
        imageView.setPreserveRatio(true);
        imageView.setFitHeight(imageHeight);
        imageView.setFitWidth(imageWidth);
        imageContainer.setGraphic(imageView);
        imageView.setVisible(true);

        String[] buttonList = {"Attack","Defend","Check Status","Spells","Items","Run Away"};


        for (int i=1;i<=6;i++)
        {
            //the width and height of the button is huge, because it seems to automatically scale it to fit the HBox
            Button newButton = createButton(buttonList[i-1],"Button2","Times New Roman",100,8000,8000,0,0);

            int index = i;
            newButton.setOnAction(e -> {
                if (pauseGame == false)
                {
                    switch (index)
                    {
                        case 1:
                            // Attack
                            turn++;
                            turnCounter.setText("Turn: "+turn);


                            pauseGame = true;
                            buttonContainer.setVisible(false);
                            refreshGUI(introText,enemyStats,allActions,currentEnemy,"Attack",null);
                            PauseTransition delay1 = new PauseTransition(Duration.seconds(1.5));

                            delay1.setOnFinished(event -> {
                                PauseTransition delay3 = new PauseTransition(Duration.seconds(statusLoop(introText, enemyStats, allActions,false,true)));
                                delay3.setOnFinished(event3 -> {

                                if (currentEnemy.getHealth()>0 && !currentEnemy.getStatus("Paralyze"))
                                {
                                    refreshGUI(introText, enemyStats, allActions, currentEnemy, "Enemy", currentEnemy.enemyTurn(player));
                                }
                                PauseTransition delay2 = new PauseTransition(Duration.seconds(statusLoop(introText, enemyStats, allActions,true,false)));
                                delay2.setOnFinished(event2 -> {
                                    if (player.getHealth() != 0)
                                    {
                                        buttonContainer.setVisible(true);
                                        pauseGame = false;
                                    }

                                    if (currentEnemy.getHealth() <= 0)
                                    {
                                        currentEnemy = null;
                                        ExploreMenu.exploreMenu();
                                    }
                                });
                                delay2.play();
                                });
                                delay3.play();
                            });
                            delay1.play();
                            break;
                        case 2:
                            // Defend
                            turn++;
                            turnCounter.setText("Turn: "+turn);
                            pauseGame = true;
                            buttonContainer.setVisible(false);
                            refreshGUI(introText,enemyStats,allActions,currentEnemy,"Defend",null);
                            PauseTransition delay5 = new PauseTransition(Duration.seconds(1));
                            delay5.setOnFinished(event -> {
                                player.setGuarding(true);
                                double oldDefenseMultiplier = player.getDefenseMultiplier();
                                player.setDefenseMultiplier(oldDefenseMultiplier/2);
                                if (!currentEnemy.getStatus("Paralyze")) {
                                    refreshGUI(introText, enemyStats, allActions, currentEnemy, "Enemy", currentEnemy.enemyTurn(player));
                                }
                                PauseTransition delay6 = new PauseTransition(Duration.seconds(statusLoop(introText, enemyStats, allActions,true,false)));
                                delay6.setOnFinished(event2 -> {
                                    player.setGuarding(false);
                                    player.setDefenseMultiplier(oldDefenseMultiplier);
                                    if (player.getHealth() != 0)
                                    {
                                        buttonContainer.setVisible(true);
                                        pauseGame = false;
                                    }
                                    if (currentEnemy.getHealth() <= 0)
                                    {
                                        currentEnemy = null;
                                        ExploreMenu.exploreMenu();
                                    }
                                });
                                delay6.play();
                            });
                            delay5.play();
                            break;
                        case 3:
                            // Check Status
                            StatusMenu.statusMenu();

                            //stage.setScene(new Scene(root));
                            break;
                        case 4:
                            // Spells
                            inMenu = true;
                            SpellsMenu.spellsMenu();
                            Thread spellThread = new Thread(new Runnable() {
                                @Override
                                public void run() {
                                    //while pit to wait until player exits item menu
                                    while (inMenu == true) {
                                        try {
                                            Thread.sleep(100);
                                        } catch (InterruptedException e) {
                                            e.printStackTrace();
                                        }
                                    }

                                    if (usedSpell != null)
                                    {
                                        pauseGame = true;
                                        Platform.runLater(() -> {
                                            refreshGUI(introText, enemyStats, allActions, currentEnemy, "UseSpell", usedSpell.getAbilityUseDesc());
                                            usedSpell = null;
                                            turn++;
                                            turnCounter.setText("Turn: "+turn);
                                            buttonContainer.setVisible(false);
                                            PauseTransition delay7 = new PauseTransition(Duration.seconds(2));
                                            delay7.setOnFinished(event -> {
                                                if (!currentEnemy.getStatus("Paralyze")) {
                                                    refreshGUI(introText, enemyStats, allActions, currentEnemy, "Enemy", currentEnemy.enemyTurn(player));
                                                }
                                                PauseTransition delay9 = new PauseTransition(Duration.seconds(statusLoop(introText, enemyStats, allActions,true,false)));
                                                delay9.setOnFinished(event2 -> {
                                                    if (player.getHealth() != 0)
                                                    {
                                                        buttonContainer.setVisible(true);
                                                        pauseGame = false;
                                                    }
                                                    if (currentEnemy.getHealth() <= 0)
                                                    {
                                                        currentEnemy = null;
                                                        ExploreMenu.exploreMenu();
                                                    }
                                                });
                                                delay9.play();
                                            });
                                            delay7.play();
                                        });
                                    }
                                }
                            });
                            spellThread.start();

                            break;
                        case 5:
                            inMenu = true;
                            ItemMenu.itemMenu();

                            /*creates new thread so this javafx thread isn't paused. Because if htis thread is paused,
                            javafx does not update when it is paused
                             */
                            Thread newThread = new Thread(new Runnable() {
                                @Override
                                public void run() {
                                    //while pit to wait until player exits item menu
                                    while (inMenu == true) {
                                        try {
                                            Thread.sleep(100);
                                        } catch (InterruptedException e) {
                                            e.printStackTrace();
                                        }
                                    }
                                    //Check if item was used once item menu was exited,
                                    if (usedItem != null)
                                    {
                                        pauseGame = true;
                                        //i dont really know what Platform is, but i needed it, because i couldn't update JavaFX in a new thread
                                        Platform.runLater(() -> {
                                            refreshGUI(introText, enemyStats, allActions, currentEnemy, "UseItem", usedItem.getUseDesc());
                                            usedItem = null;
                                            turn++;
                                            turnCounter.setText("Turn: "+turn);
                                            buttonContainer.setVisible(false);
                                            PauseTransition delay3 = new PauseTransition(Duration.seconds(2));
                                            delay3.setOnFinished(event -> {
                                                delayTime = 1;
                                                if (!currentEnemy.getStatus("Paralyze")) {
                                                    delayTime = 0;
                                                    refreshGUI(introText, enemyStats, allActions, currentEnemy, "Enemy", currentEnemy.enemyTurn(player));
                                                }
                                                PauseTransition delay4 = new PauseTransition(Duration.seconds(delayTime));
                                                delay4.setOnFinished(event2 -> {
                                                    if (player.getHealth() != 0)
                                                    {
                                                        buttonContainer.setVisible(true);
                                                        pauseGame = false;
                                                    }
                                                });
                                                delay4.play();
                                            });
                                            delay3.play();
                                        });
                                    }
                                }
                            });
                            newThread.start();
                            // Items
                            break;
                        case 6:
                            // Run Away
                            ExploreMenu.exploreMenu();
                            break;
                    }
                }
            });

            buttonContainer.getChildren().add(newButton);

        }

        lastScene = new Scene(root);
        stage.setScene(lastScene);
    }
}
