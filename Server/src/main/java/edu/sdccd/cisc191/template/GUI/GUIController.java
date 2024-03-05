package edu.sdccd.cisc191.template.GUI;

import edu.sdccd.cisc191.template.GUI.CharacterCreationMenu;
import edu.sdccd.cisc191.template.GUI.GUIMain;
import edu.sdccd.cisc191.template.GameData;
import edu.sdccd.cisc191.template.Inventory;
import edu.sdccd.cisc191.template.Player;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;
import java.io.*;

import java.awt.*;


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
    static protected Button createButton(String textString, String styleType,String fontType,double fontDividor,double width, double height, double xPos, double yPos) {
        Button button = new Button(textString);
        button.setTextAlignment(TextAlignment.CENTER);
        button.getStyleClass().add(styleType);
        button.setWrapText(true);
        button.setFont(new Font(fontType,(screenHeight+screenWidth)/fontDividor));
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
    

    private void updateStatus(Player player, Label leftTextLabel, Label rightTextLabel)
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


   public GameData loadGame()
   {
       FileInputStream saveFile;
       String savePath = "Server/src/main/resources/SaveFile.ser";
       try {
           saveFile = new FileInputStream(savePath);
           try
           {
               ObjectInputStream objectInputStream = new ObjectInputStream(saveFile);
               GameData saveData = (GameData) objectInputStream.readObject();
               return saveData;
           } catch (IOException ex) {
               ex.printStackTrace();
               throw new RuntimeException(ex);
           } catch (ClassNotFoundException ex) {
               ex.printStackTrace();
               throw new RuntimeException(ex);
           }
       } catch (FileNotFoundException ex) {
           ex.printStackTrace();
           throw new RuntimeException(ex);
       }
   }

   public static void previousSceneCheck(){
       switch (previousStage) {
       case "Explore":
           ExploreMenu.exploreMenu();
           break;
           case "Combat":
               CombatMenu.combatMenu();
               break;
       }
   }

   public Scene showMainMenu(){

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
        Button startButton = createButton("New Game","Button1","Times New Roman",120,0.2,0.02,0,screenHeight*0.3);
        Button loadButton = createButton("Load Game","Button3","Times New Roman",120,0.2,0.02,0,screenHeight*0.325);
        Button quitButton = createButton("Quit","Button1","Times New Roman",120,0.2,0.02,0,screenHeight*0.35);

        loadButton.setOpacity(0.2);

        Boolean hasSave = false;

        String filePath = "Server/src/main/resources/SaveFile.ser";
        File file = new File(filePath);
        if (file.exists())
        {
            hasSave = true;
        }

        if (hasSave == true)
        {
            GameData saveData = loadGame();
            loadButton.getStyleClass().add("Button1");
            loadButton.setOpacity(1);

            loadButton.setOnAction(e ->
            {
                player = saveData.getPlayerData();
                storage = saveData.getInventoryData();
                ExploreMenu.exploreMenu();
            });
            loadButton.setOnMouseEntered(e ->
            {
                loadButton.setText(saveData.getPlayerData().getPlayerClass()+" - Level "+saveData.getPlayerData().getLevel());
            });
            loadButton.setOnMouseExited(e ->
            {
                loadButton.setText("Load Game");
            });
        }

       startButton.setOnAction(e ->
        {
        //Intro
            CharacterCreationMenu.showIntro();
        });
        quitButton.setOnAction(e ->
       {
           System.out.println("GAME IS CLOSING, SAVE HERE");
           System.exit(0);
       });

        vbox.getChildren().addAll(text,startButton,loadButton,quitButton,divider);

        return scene;
    }

}
