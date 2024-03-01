package edu.sdccd.cisc191.template.GUI;

import edu.sdccd.cisc191.template.Player;
import javafx.application.Application;
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

    Player player = new Player();

    protected static Stage stage;
    @Override
    public void start(Stage stage) throws Exception {
        try {
            this.stage = stage;
            GUIController guiController = new GUIController();

            this.stage.setOnCloseRequest(e -> {
                System.out.println("GAME IS CLOSING, SAVE HERE");
                FileOutputStream saveFile;
                try {
                    saveFile = new FileOutputStream("Save");
                    try {
                        ObjectOutputStream objWriter = new ObjectOutputStream(saveFile);
                        objWriter.writeObject(player);
                        /**
                         * ObjectOutputStream objWriter = new ObjectOutputStream(saveFile);
                         * objWriter.writeObject(player.getName());
                         * objWriter.writeObject(player.getPlayerClass());
                         * objWriter.writeInt(player.getLevel());
                         * objWriter.writeInt(player.getHealth());
                         * objWriter.writeInt(player.getAttack());
                         * objWriter.writeInt(player.getMana());
                         */

                    } catch (IOException ex) {
                        throw new RuntimeException(ex);
                    }

                } catch (FileNotFoundException ex) {
                    throw new RuntimeException(ex);
                }


            });

            this.stage.setScene(guiController.showMainMenu(player));
            stage.setTitle("Group 1 Architect Game");
            this.stage.setResizable(false);


            this.stage.show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}