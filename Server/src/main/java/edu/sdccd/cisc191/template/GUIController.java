package edu.sdccd.cisc191.template;

import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.Parent;
import javafx.fxml.FXMLLoader;
import javafx.fxml.FXML;

import java.awt.event.ActionEvent;
import java.io.IOException;

public class GUIController extends GUIMain {

    private  Stage stage;
    private  Scene scene;
    private Parent root;

    @FXML
    private Button startButton;

    @FXML
   public void showStats() throws IOException {

        root = FXMLLoader.load(getClass().getResource("/Stats.fxml"));

        stage = (Stage)startButton.getScene().getWindow();

        stage.setScene(new Scene(root));
        stage.setResizable(false);

        stage.show();
    }

}
