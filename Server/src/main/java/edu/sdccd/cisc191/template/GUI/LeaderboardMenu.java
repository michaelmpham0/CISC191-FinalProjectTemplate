package edu.sdccd.cisc191.template.GUI;

import edu.sdccd.cisc191.template.LeaderboardSystem.Database;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Paint;

import java.util.ArrayList;

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
        playerList.setSpacing(screenHeight*0.025);
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


        ArrayList<String> allPlayerDetails = Database.getPlayerDetails();
        for (int i=0;i<allPlayerDetails.size();i++)
        {
            String[] playerInfo = allPlayerDetails.get(i).split(",");

            Label playerLabel = createLabel(playerInfo[0]+" - "+playerInfo[1]+" - Level: "+playerInfo[2]+" - Score: "+playerInfo[3],"Times New Roman",75,0.65,0.05);
            playerLabel.getStyleClass().add("borders");
            playerLabel.setTranslateY(screenHeight*0.015);
            playerList.getChildren().add(playerLabel);
        }




        Scene thisScene = new Scene(root);
        stage.setScene(thisScene);

    }
}
