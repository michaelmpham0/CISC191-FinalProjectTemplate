package edu.sdccd.cisc191.template;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.text.Text;
import javafx.scene.control.TextField;
import javafx.scene.shape.*;
import javafx.scene.text.TextAlignment;


import java.awt.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;


public class GUIController extends GUIMain {

    protected  Scene scene;
    public static Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
    public static double screenWidth = screenSize.getWidth()*0.8;
    public static double screenHeight = screenSize.getHeight()*0.8;

    static protected Text createText(String textString, String font, String color , double size, double xPos, double yPos) {
        Text text = new Text(textString);
        text.setFont(new Font(font,size));
        text.setFill(Paint.valueOf(color));
        text.setTranslateX(xPos);
        text.setTranslateY(yPos);
        return text;
    }
    static protected Button createButton(String textString, String styleType,double width, double height, double xPos, double yPos) {
        Button button = new Button(textString);
        button.getStyleClass().add(styleType);
        button.setPrefSize(screenWidth*width,screenHeight*height);
        button.setTranslateX(xPos);
        button.setTranslateY(yPos);
        return button;
    }
    static protected Label createLabel(String text,String fontType,double fontDividor,double width,double height)
    {
        Label newLabel = new Label();
        newLabel.setText(text);
        newLabel.setWrapText(true);
        newLabel.setFont(new Font(fontType,(screenHeight+screenWidth)/fontDividor));
        newLabel.setMinSize(screenWidth*width,screenHeight*height);
        newLabel.setPrefSize(screenWidth*width,screenHeight*height);
        newLabel.setMaxSize(screenWidth*width,screenHeight*height);
        newLabel.setTextAlignment(TextAlignment.CENTER);
        newLabel.setAlignment(Pos.CENTER);
        newLabel.getStylesheets().add("styleSheet.css");
        return newLabel;
    }
    

    private void updateStatus(Player player,Label leftTextLabel,Label rightTextLabel)
    {
        String leftString = String.format("Health:");
        //return String.format("Health:%-5d \t Attack:%-5d \t\nMana:%-5d \t\t Gold:%d",HP,ATK,MANA,GOLD);

        leftString += "Health\tAttack\n";
        leftString += player.getHealth()+"\t"+player.getAttack()+"\n";
        leftString += "Mana\tGold\n";
        leftString += player.getMana()+"\t"+player.getGold();
        leftTextLabel.setText(leftString);
        //rightTextLabel.setText(leftString);
    }



   public Scene showMainMenu(Player player){

        VBox vbox = new VBox();
        vbox.setPrefSize(screenWidth,screenHeight);
        vbox.setAlignment(Pos.TOP_CENTER);
        vbox.setStyle("-fx-background-color: #000000;");
        scene = new Scene(vbox);
        scene.getStylesheets().add("styleSheet.css");

        Rectangle divider = new Rectangle();
        divider.setHeight(screenHeight*0.005);
        divider.setWidth(screenWidth*0.7);
        divider.setTranslateY(screenHeight*0.05);
        divider.setFill(Color.color(1,1,1));

        scene.setFill(Paint.valueOf("Black"));

        Text text = createText("Game","Times New Roman","White",(screenHeight+screenWidth)/15,0,screenHeight*0.1);
        Button btn1 = createButton("Start","Button1",0.1,0.05,0,screenHeight*0.3);
        Button quitButton = createButton("Quit","Button1",0.1,0.05,0,screenHeight*0.325);

        btn1.setOnAction(e ->
        {
        //Intro
            CharacterCreationMenu.showIntro(player);
        });
        quitButton.setOnAction(e ->
       {
           System.exit(0);
       });

        vbox.getChildren().addAll(text,btn1,quitButton,divider);

        return scene;
    }

}
