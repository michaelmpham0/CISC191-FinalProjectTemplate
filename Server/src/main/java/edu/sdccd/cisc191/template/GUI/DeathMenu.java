package edu.sdccd.cisc191.template.GUI;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

import java.io.File;

public class DeathMenu extends GUIController {
    public static void deathMenu()
    {
        VBox root = new VBox();
        root.setPrefSize(screenWidth,screenHeight);
        root.setAlignment(Pos.CENTER);
        root.getStylesheets().add("styleSheet.css");

        Label titleText = createLabel("You Died","Times New Roman",90,0.3,0.05);
        titleText.setTranslateY(screenHeight*0.1);
        root.getChildren().add(titleText);
        Button mainMenu = createButton("Return To Menu","Button1","Times New Roman",120,0.2,0.02,0,0);
        titleText.setTranslateY(-screenHeight*0.1);
        root.getChildren().add(mainMenu);

        stage.setScene(new Scene(root));

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

        mainMenu.setOnAction(e ->
        {
            //Intro
            GUIController.showMainMenu();
        });
    }
}
