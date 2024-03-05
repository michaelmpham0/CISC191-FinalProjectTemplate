package edu.sdccd.cisc191.template.GUI;

import edu.sdccd.cisc191.template.Inventory;
import edu.sdccd.cisc191.template.Player;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.TextAlignment;

public class ExploreMenu extends GUIController {
    protected static void exploreMenu()
    {
        previousStage = "Explore";
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

        Label middleTextBox = new Label();
        middleTextBox.setVisible(false);
        middleTextBox.getStylesheets().add("styleSheet.css");
        middleTextBox.setFont(new Font("Times New Roman",(screenHeight+screenWidth)/75));
        middleTextBox.setTextAlignment(TextAlignment.CENTER);
        middleTextBox.setAlignment(Pos.CENTER);
        middleTextBox.setPrefSize(screenWidth*0.8,screenHeight*0.45);
        middleTextBox.setMinSize(screenWidth*0.8,screenHeight*0.45);
        middleTextBox.setMaxSize(screenWidth*0.8,screenHeight*0.45);
        middleTextBox.setTranslateY(-screenHeight*0.075);

        vBox.getChildren().add(middleTextBox);

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

        String[] buttonList = {"Continue Forward","Check Status","Spells","Items"};

        for (int i=1;i<=4;i++)
        {
            //the width and height of the button is huge, because it seems to automatically scale it to fit the HBox
            Button newButton = createButton(buttonList[i-1],"Button2","Times New Roman",100,8000,8000,0,0);

            int index = i;
            newButton.setOnAction(e -> {
                switch (index)
                {
                    case 1:
                        // Continue Forward
                        CombatMenu.combatMenu();
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
