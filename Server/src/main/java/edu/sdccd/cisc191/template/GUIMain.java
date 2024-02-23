package edu.sdccd.cisc191.template;

import javafx.application.Application;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.fxml.FXMLLoader;

/**
* GUI using javafx
*/

public class GUIMain extends Application {
    public static void main(String[] args)
    {
       launch(args);
    }

    protected  static Stage stage;
    @Override
    public void start(Stage stage) throws Exception {
        try {
            this.stage = stage;
            GUIController guiController = new GUIController();

            this.stage.setScene(guiController.showMainMenu());
            stage.setTitle("Group 1 Architect Game");
            this.stage.setResizable(false);


            this.stage.show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}