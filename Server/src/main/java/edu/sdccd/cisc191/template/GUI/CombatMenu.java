package edu.sdccd.cisc191.template.GUI;

import edu.sdccd.cisc191.template.Enemies.Grogoroth;
import edu.sdccd.cisc191.template.Enemy;
import edu.sdccd.cisc191.template.EnemyHandler;
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

public class CombatMenu extends GUIController{
    private static int turn = 1;
   protected  static  void refreshGUI(Label name, Label health,Label acts,Enemy enemy,String action,String act){
       name.setText(enemy.getName());
      if (action.equals("Attack")) {
          health.setText("Health: " + enemy.takeDamage(player.getAttack()+player.getCurrentWeapon().getWeaponDamage()) + "/" + enemy.getMaxHealth());
          acts.setText(player.getName() + " attacks " + enemy.getName()  + " for " + (player.getAttack()+player.getCurrentWeapon().getWeaponDamage()) + " damage.");
          if (enemy.getHealth() <= 0) {
              name.setText(enemy.getName() + " has perished!");
          }
      }
      else {
          health.setText("Health: " + enemy.getHealth() + "/" + enemy.getMaxHealth());
          acts.setText(act);
       }
   }
    protected static void combatMenu()
    {
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



        //enemy GUI container
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

        currentEnemy = EnemyHandler.createEnemy(true,0);

        Label allActions = new Label();
        allActions.getStylesheets().add("styleSheet.css");
        allActions.setText(currentEnemy.getName() + " examines you.");
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
        //image
        Label imageContainer = createLabel("","Times New Roman",0,imageWidth,imageHeight);
        imageContainer.setMinSize(imageWidth,imageHeight);
        imageContainer.setMaxSize(imageWidth,imageHeight);
        imageContainer.getStyleClass().add("noBorder");
        imageContainer.setAlignment(Pos.CENTER);
        enemyGUIContainer.setCenter(imageContainer);

        Image newImage = new Image("file:Server/src/main/resources/Images/" + currentEnemy.getName() + ".png");
        ImageView imageView = new ImageView(newImage);
        imageView.setPreserveRatio(true);
        imageView.setFitHeight(imageHeight);
        imageView.setFitWidth(imageWidth);
        imageContainer.setGraphic(imageView);
        imageView.setVisible(true);



        String[] buttonList = {"Attack","Check Status","Spells","Items"};


        for (int i=1;i<=4;i++)
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
                            pauseGame = true;
                            buttonContainer.setVisible(false);
                            refreshGUI(introText,enemyStats,allActions,currentEnemy,"Attack",null);
                            PauseTransition delay1 = new PauseTransition(Duration.seconds(1));
                            delay1.setOnFinished(event -> {
                                refreshGUI(introText, enemyStats, allActions, currentEnemy, "Enemy", currentEnemy.enemyTurn(player));
                                PauseTransition delay2 = new PauseTransition(Duration.seconds(1));
                                delay2.setOnFinished(event2 -> {
                                    buttonContainer.setVisible(true);
                                    pauseGame = false;
                                });
                                delay2.play();
                            });
                            delay1.play();
                            break;
                        case 2:
                            // Check Status
                            StatusMenu.statusMenu();

                            //stage.setScene(new Scene(root));
                            break;
                        case 3:
                            // Spells
                            break;
                        case 4:
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
                                            buttonContainer.setVisible(false);
                                            PauseTransition delay3 = new PauseTransition(Duration.seconds(2));
                                            delay3.setOnFinished(event -> {
                                                refreshGUI(introText, enemyStats, allActions, currentEnemy, "Enemy", currentEnemy.enemyTurn(player));
                                                PauseTransition delay4 = new PauseTransition(Duration.seconds(1));
                                                delay4.setOnFinished(event2 -> {
                                                    buttonContainer.setVisible(true);
                                                    pauseGame = false;
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
                    }
                }
            });

            buttonContainer.getChildren().add(newButton);
        }
        lastScene = new Scene(root);
        stage.setScene(lastScene);
    }
}
