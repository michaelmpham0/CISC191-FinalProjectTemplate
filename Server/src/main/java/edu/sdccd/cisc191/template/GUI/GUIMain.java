package edu.sdccd.cisc191.template.GUI;

import edu.sdccd.cisc191.template.Player;
import javafx.application.Application;
import javafx.stage.Stage;

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

            this.stage.setOnCloseRequest(e ->{
                System.out.println("GAME IS CLOSING, SAVE HERE");
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