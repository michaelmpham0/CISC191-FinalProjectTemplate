package edu.sdccd.cisc191.template.GUI;

import edu.sdccd.cisc191.template.Enemy;
import edu.sdccd.cisc191.template.EnemyHandler;
import edu.sdccd.cisc191.template.EnemyInterface;
import edu.sdccd.cisc191.template.Weapons;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.TextAlignment;

public class CombatMenu extends GUIController{
   protected  static  void refreshGUI(Label name, Label health,Enemy enemy,String action){
       name.setText(enemy.getName());
      if (action.equals("Attack")) {
          health.setText("Health: " + enemy.takeDamage(player.getAttack()+player.getCurrentWeapon().getWeaponDamage()) + "/" + enemy.getMaxHealth());
          if (enemy.getHealth() <= 0) {
              name.setText(enemy.getName() + " has perished!");
          }
      }
      else {
          health.setText("Health: " + enemy.getHealth() + "/" + enemy.getMaxHealth());
       }
   }
    protected static void combatMenu()
    {
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
        enemyGUIContainer.setPrefSize(screenWidth*0.8,screenHeight*0.5);
        enemyGUIContainer.setMinSize(screenWidth*0.8,screenHeight*0.5);
        enemyGUIContainer.setMaxSize(screenWidth*0.8,screenHeight*0.5);
        enemyGUIContainer.setTranslateY(screenHeight*0.1);
        enemyGUIContainer.setTranslateX(screenWidth*0.1);
        root.setTop(enemyGUIContainer);

        Enemy enemy = EnemyHandler.createEnemy(true,"0",0,0);


        //intro text
        Label introText = new Label();
        introText.getStylesheets().add("styleSheet.css");
        introText.setText("You encountered " + enemy.getName() + "!");
        introText.setFont(new Font("Times New Roman",(screenHeight+screenWidth)/100 ));
        BorderPane.setAlignment(introText,Pos.CENTER);

        enemyGUIContainer.setTop(introText);

        //enemy stats
        Label enemyStats = new Label();
        enemyStats.getStylesheets().add("styleSheet.css");
        enemyStats.setText("Health: " + enemy.getHealth() + "/" + enemy.getMaxHealth());
        enemyStats.setFont(new Font("Times New Roman",(screenHeight+screenWidth)/100));
        BorderPane.setAlignment(enemyStats,Pos.CENTER);

        enemyGUIContainer.setBottom(enemyStats);

        double imageWidth = (screenHeight+screenWidth)/9;
        double imageHeight = (screenHeight+screenWidth)/8;
        //image
        Label imageContainer = createLabel("","Times New Roman",0,imageWidth,imageHeight);
        imageContainer.setMinSize(imageWidth,imageHeight);
        imageContainer.setMaxSize(imageWidth,imageHeight);
        imageContainer.getStyleClass().add("noBorder");
        imageContainer.setAlignment(Pos.CENTER);
        enemyGUIContainer.setCenter(imageContainer);

        Image newImage = new Image("file:Server/src/main/resources/Images/" + enemy.getName() + ".png");
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
                switch (index)
                {
                    case 1:
                        // Attack
                        refreshGUI(introText,enemyStats,enemy,"Attack");
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
                        ItemMenu.itemMenu();
                        // Items
                        break;
                }
            });

            buttonContainer.getChildren().add(newButton);
        }

        stage.setScene(new Scene(root));
    }
}