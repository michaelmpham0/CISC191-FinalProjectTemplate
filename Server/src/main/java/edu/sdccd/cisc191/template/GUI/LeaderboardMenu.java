package edu.sdccd.cisc191.template.GUI;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Paint;

import java.io.File;

public class LeaderboardMenu extends GUIController {
    public static void leaderboardMenu()
    {
        VBox root = new VBox();
        root.setPrefSize(screenWidth,screenHeight);
        root.setAlignment(Pos.TOP_CENTER);
        root.getStylesheets().add("styleSheet.css");

        Label titleText = createLabel("The Graveyard","Times New Roman",90,0.3,0.05);
        titleText.setTranslateY(screenHeight*0.05);
        root.getChildren().add(titleText);

        Label flavorText = createLabel("Here lies your failures.","Times New Roman",160,0.15,0.025);
        flavorText.setTranslateY(screenHeight*0.065);
        flavorText.getStyleClass().add("noBorder");
        root.getChildren().add(flavorText);

        VBox playerList = new VBox();
        playerList.getStyleClass().add("borders");
        playerList.setAlignment(Pos.TOP_CENTER);
        playerList.setMinSize(screenWidth*0.7,screenHeight*0.65);
        playerList.setMaxSize(screenWidth*0.7,screenHeight*0.65);
        playerList.setTranslateY(screenHeight*0.1);
        root.getChildren().add(playerList);

        Button backButton = createButton("Go Back","Button2","Times New Roman",100,0.1,0.05,0,0);
        backButton.setTranslateY(screenHeight*0.125);
        root.getChildren().add(backButton);

        backButton.setOnAction(e -> {
            Scene scene = new Scene(createMainmenu());
            scene.getStylesheets().add("styleSheet.css");
            scene.setFill(Paint.valueOf("Black"));
            stage.setScene(scene);
        });

        //Button mainMenu = createButton("Return To Menu","Button1","Times New Roman",120,0.2,0.02,0,0);
        //root.getChildren().add(mainMenu);
        Scene thisScene = new Scene(root);
        stage.setScene(thisScene);

    }
}