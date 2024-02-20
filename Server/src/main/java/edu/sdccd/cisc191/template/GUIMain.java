package edu.sdccd.cisc191.template;

import javafx.application.Application;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.Parent;
import javafx.fxml.FXMLLoader;

/**
* GUI using javafx
*/

public class GUIMain extends Application {


    public static void main(String[] args)
    {
       launch(args);
    }

    @Override
    public void start(Stage stage) throws Exception {
        try {
            Parent root = FXMLLoader.load(getClass().getResource("/MenuScene.fxml"));

            stage.setScene(new Scene(root));
            stage.setResizable(false);

            stage.show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
