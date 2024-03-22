package edu.sdccd.cisc191.template;

import edu.sdccd.cisc191.template.Enemies.Enemy;
import edu.sdccd.cisc191.template.GUI.GUIController;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.ProgressBar;
import javafx.stage.Stage;

import java.io.*;

/**
 * This program is a server that takes connection requests on
 * the port specified by the constant LISTENING_PORT.  When a
 * connection is opened, the program sends the current time to
 * the connected socket.  The program will continue to receive
 * and process connections until it is killed (by a CONTROL-C,
 * for example).  Note that this server processes each connection
 * as it is received, rather than creating a separate thread
 * to process the connection.
 */

public class Server extends Application {
    protected static Spells spells;
    protected static Inventory storage;
    public static Player player = new Player();
    public static Player getPlayer() {
        return player;
    }
    protected static Stage stage;
    protected static Enemy currentEnemy;

    public static Enemy getCurrentEnemy() {
        return currentEnemy;
    }
    protected static Scene lastScene;
    protected static String previousStage;

    public static ProgressBar currentHealthBar = null;
    public static ProgressBar currentManaBar = null;

    public static Label currentHealthBarText = null;
    public static Label currentManaBarText = null;

    @Override
    public void start(Stage stage) throws Exception {
        try {
            this.stage = stage;
            GUIController guiController = new GUIController();

            this.stage.setOnCloseRequest(e -> {

                if (!player.getPlayerClass().equals("Unknown") && (player.getHealth() != 0))
                {
                    System.out.println("GAME IS CLOSING, SAVE HERE");
                    FileOutputStream saveFile;
                    String outputPath = System.getProperty("user.home") + "/Documents/ArchitectSaveFile.ser";
                    try {
                        saveFile = new FileOutputStream(outputPath);
                        ObjectOutputStream objWriter = new ObjectOutputStream(saveFile);
                        objWriter.writeObject(new GameData(player,storage,spells));
                    }
                    catch (FileNotFoundException ex)
                    {
                        System.out.println("ACCESS DENIED?");
                        ex.printStackTrace();
                    }
                    catch (IOException ex)
                    {
                        ex.printStackTrace();
                        //throw new RuntimeException(ex);
                    }
                }
            });

            this.stage.setScene(guiController.startMainMenu());
            stage.setTitle("Group 1 Architect Game");
            this.stage.setResizable(false);


            this.stage.show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public static void main(String[] args)
    {
        launch(args);
    }

}
//end class Server
