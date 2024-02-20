package edu.sdccd.cisc191.template;

import javafx.scene.paint.Paint;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.text.Text;
import javafx.scene.Parent;
import javafx.scene.layout.AnchorPane;
import javafx.fxml.FXML;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.io.IOException;

public class GUIController extends GUIMain {

    private  Stage stage;
    private  Scene scene;

   public Scene showMenu() throws IOException {

        AnchorPane root = new AnchorPane();
        root.prefHeight(600);
        root.prefWidth(600);

        scene = new Scene(root);

        scene.setFill(Paint.valueOf("Black"));

        Text text = new Text();
        text.setFont(Font.font("Arial",100));
        text.setText("Title");
        text.setFill(Paint.valueOf("White"));
        text.setX(50);
        text.setY(50);

        root.getChildren().addAll(text);
        
        return scene;
    }

}
