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

public class DeathMenu extends GUIController {
    public static void deathMenu()
    {
        VBox root = new VBox();
        root.setPrefSize(screenWidth,screenHeight);
        root.setAlignment(Pos.CENTER);
        root.getStylesheets().add("styleSheet.css");

        Label titleText = createLabel("You Died","Times New Roman",90,0.3,0.05);
        //titleText.setTranslateY(screenHeight*0.1);
        root.getChildren().add(titleText);

        Label scoreText = createLabel("Score: "+player.getScore(),"Times New Roman",150,0.2,0.035);
        scoreText.setTranslateY(screenHeight*0.1);
        root.getChildren().add(scoreText);

        //Button mainMenu = createButton("Return To Menu","Button1","Times New Roman",120,0.2,0.02,0,0);
        //root.getChildren().add(mainMenu);
        Scene thisScene = new Scene(root);
        stage.setScene(thisScene);

        String savePath = System.getProperty("user.home") + "/Documents/ArchitectSaveFile.ser";
        File saveFile = new File(savePath);

        if (saveFile.exists()) {
            if (saveFile.delete()) {
                System.out.println("Save file deleted successfully.");
            } else {
                System.out.println("Failed to delete save file.");
            }
        } else {
            System.out.println("Save file doesn't exist, did not delete.");
        }
        //while loop, because sometimes it didn't set the death screen
        while (stage.getScene().equals(thisScene) == false)
        {
            stage.setScene(thisScene);
        }

        Button backButton = createButton("Go Back","Button2","Times New Roman",100,0.1,0.05,0,0);
        backButton.setTranslateY(screenHeight*0.125);
        root.getChildren().add(backButton);

        backButton.setOnAction(e -> {
            Scene scene = new Scene(createMainmenu());
            scene.getStylesheets().add("styleSheet.css");
            scene.setFill(Paint.valueOf("Black"));
            stage.setScene(scene);
        });
    }
}
