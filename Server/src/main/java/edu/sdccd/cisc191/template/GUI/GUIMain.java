package edu.sdccd.cisc191.template.GUI;

import edu.sdccd.cisc191.template.Enemy;
import edu.sdccd.cisc191.template.GameData;
import edu.sdccd.cisc191.template.Inventory;
import edu.sdccd.cisc191.template.Player;
import javafx.application.Application;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.*;

/**
* GUI using javafx
*/

public class GUIMain extends Application {
    public static void main(String[] args)
    {
       launch(args);
    }

    protected static Inventory storage;
    protected static Player player = new Player();
    protected static Stage stage;
    protected static Enemy currentEnemy;

    protected static Scene lastScene;
    protected static String previousStage;
    @Override
    public void start(Stage stage) throws Exception {
        try {
            this.stage = stage;
            GUIController guiController = new GUIController();

            this.stage.setOnCloseRequest(e -> {

                if (!player.getPlayerClass().equals("Unknown"))
                {
                    System.out.println("GAME IS CLOSING, SAVE HERE");
                    FileOutputStream saveFile;
                    String outputPath = "Server/src/main/resources/SaveFile.ser";
                    try {
                        saveFile = new FileOutputStream(outputPath);
                        try {
                            ObjectOutputStream objWriter = new ObjectOutputStream(saveFile);
                            objWriter.writeObject(new GameData(player,storage));

                        } catch (IOException ex) {
                            throw new RuntimeException(ex);
                        }

                    } catch (FileNotFoundException ex) {
                        throw new RuntimeException(ex);
                    }

                }


            });

            this.stage.setScene(guiController.showMainMenu());
            stage.setTitle("Group 1 Architect Game");
            this.stage.setResizable(false);


            this.stage.show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}