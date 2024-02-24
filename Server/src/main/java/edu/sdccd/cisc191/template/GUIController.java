package edu.sdccd.cisc191.template;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import javafx.scene.text.Font;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.text.Text;
import javafx.scene.shape.*;
import javafx.scene.text.TextAlignment;

import java.awt.Toolkit;
import java.awt.Dimension;
import java.util.ArrayList;


public class GUIController extends GUIMain {

    private  Scene scene;


    Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
    double screenWidth = screenSize.getWidth()*0.8;
    double screenHeight = screenSize.getHeight()*0.8;

    private Text createText(String textString, String font, String color , double size, double xPos, double yPos) {
        Text text = new Text(textString);
        text.setFont(new Font(font,size));
        text.setFill(Paint.valueOf(color));
        text.setTranslateX(xPos);
        text.setTranslateY(yPos);
        return text;
    }
    private Button createButton(String textString, String styleType,double width, double height, double xPos, double yPos) {
        Button button = new Button(textString);
        button.getStyleClass().add(styleType);
        button.setPrefSize(width,height);
        button.setTranslateX(xPos);
        button.setTranslateY(yPos);
        return button;
    }

    private void newRefreshIntro(String playerClass,Label leftText,Label rightText)
    {
        player.setClass(playerClass);
        String leftString = "";
        leftString += "Health\n"+player.getHealth()+"\n\n";
        leftString += "Attack\n"+player.getAttack()+"\n\n";
        leftString += "Mana\n"+player.getMana()+"\n\n";
        leftString += "Gold\n"+player.getGold()+"\n\n";
        leftText.setText(leftString);
        rightText.setText(TextDisplay.getText(playerClass+"Intro"));
    }

    private  void refreshIntro(int playerClass, javaFXPlayer player,Text hp, Text atk, Text mana, Text gold, Text item1, Text item2, Text item3, Text item4) {
        player.setUp(playerClass);
        hp.setText("Health: "+ player.getHealth());
        atk.setText("Attack: " + player.getAttack());
        mana.setText("Mana: " + player.getMana());
        gold.setText("Gold: " + player.getGold());
    }

    private void newShowIntro(Player player)
    {
        //main container to align things to center
        BorderPane root = new BorderPane();
        root.getStylesheets().add("styleSheet.css");
        root.setPrefSize(screenWidth,screenHeight);

        //second container to align things to center, is slightly smaller than full screen
        VBox vBox = new VBox();
        //sets this vbox to center with the border pane
        root.setCenter(vBox);
        vBox.setAlignment(Pos.CENTER);
        vBox.setMaxSize(screenWidth*0.9,screenHeight*0.9);
        vBox.setSpacing(0.0);
        vBox.setPadding(new Insets(0,0,0,0));
        vBox.getStylesheets().add("styleSheet.css");

        //VBox container at the top to contain text and class list
        //when adding things to vbox, the first thing added will be at the top, second thing below it, etc
        VBox topContainer = new VBox();
        vBox.getChildren().add(topContainer);
        topContainer.setAlignment(Pos.TOP_CENTER);
        topContainer.setPrefSize(screenWidth,screenHeight);
        topContainer.setMaxSize(screenWidth,screenHeight);
        topContainer.getStylesheets().add("styleSheet.css");

        Label titleText = new Label();
        titleText.setAlignment(Pos.CENTER);
        titleText.setText("Character Creation");
        titleText.setPrefSize(screenWidth*0.3,screenHeight*0.05);
        titleText.setMaxSize(screenWidth*0.3,screenHeight*0.05);
        topContainer.getChildren().add(titleText);
        titleText.getStylesheets().add("styleSheet.css");
        //i set margins to 0, so i had more manual control over the placement
        VBox.setMargin(titleText,new Insets(0,0,0,0));

        HBox middleContainer = new HBox();

        double imageWidth = (screenHeight+screenWidth)/9;
        double imageHeight = (screenHeight+screenWidth)/6;

        middleContainer.getStylesheets().add("styleSheet.css");
        middleContainer.setAlignment(Pos.CENTER);
        middleContainer.setPrefSize(screenWidth*0.8,imageHeight*1.2);
        middleContainer.setMinSize(screenWidth*0.8,imageHeight*1.2);
        middleContainer.setMaxSize(screenWidth*0.8,imageHeight*1.2);
        middleContainer.setSpacing(screenWidth*0.025);
        HBox.setMargin(middleContainer,new Insets(0,0,0,0));
        middleContainer.setTranslateY(-screenHeight*0.175);
        vBox.getChildren().add(middleContainer);

        Label leftText = new Label();
        leftText.setVisible(false);
        leftText.setFont(new Font("Century",(screenHeight+screenWidth)/75));
        leftText.setTextAlignment(TextAlignment.CENTER);
        leftText.setAlignment(Pos.CENTER);
        leftText.setPrefSize(imageWidth*1.2,imageHeight*1.25);
        leftText.setMinSize(imageWidth*1.2,imageHeight*1.25);
        leftText.setMaxSize(imageWidth*1.2,imageHeight*1.25);
        leftText.setTranslateX(-screenWidth*0.025);
        middleContainer.getChildren().add(leftText);

        Label imageContainer = new Label();
        imageContainer.getStylesheets().add("styleSheet.css");
        imageContainer.getStyleClass().add("noBorder");
        imageContainer.setAlignment(Pos.CENTER);
        imageContainer.setPrefSize(imageWidth,imageHeight);
        imageContainer.setMinSize(imageWidth,imageHeight);
        imageContainer.setMaxSize(imageWidth,imageHeight);
        middleContainer.getChildren().add(imageContainer);
        Image newImage = new Image("file:Server/src/main/resources/Images/knightclass.png");
        ImageView imageView = new ImageView(newImage);
        imageView.setPreserveRatio(true);
        imageView.setFitHeight(imageHeight);
        imageView.setFitWidth(imageWidth);
        imageContainer.setGraphic(imageView);
        imageView.setVisible(false);

        Button confirmButton = createButton("Confirm","Button2",screenWidth*0.1,screenHeight*0.025,0,0);
        confirmButton.setVisible(false);
        confirmButton.setTranslateY(-screenHeight*0.2);
        vBox.setMargin(confirmButton,new Insets(0,0,0,0));
        vBox.getChildren().add(confirmButton);

        Label rightText = new Label();
        rightText.setVisible(false);
        rightText.setWrapText(true);
        rightText.setFont(new Font("Century",(screenHeight+screenWidth)/80));
        rightText.setTextAlignment(TextAlignment.CENTER);
        rightText.setAlignment(Pos.CENTER);
        rightText.setPrefSize(imageWidth*1.3,imageHeight*1.25);
        rightText.setMinSize(imageWidth*1.3,imageHeight*1.25);
        rightText.setMaxSize(imageWidth*1.3,imageHeight*1.25);
        rightText.setTranslateX(screenWidth*0.025);
        middleContainer.getChildren().add(rightText);

        HBox hBox = new HBox();
        //hbox container to contain the class list
        hBox.setAlignment(Pos.CENTER);
        //padding, the empty space between buttons and the border
        hBox.setPadding(new Insets(screenWidth*0.015,screenWidth*0.015,screenWidth*0.015,screenWidth*0.015));
        //spacing, the horizontal distance between each button
        hBox.setSpacing(screenWidth*0.025);
        hBox.setPrefSize(screenWidth*0.8,screenHeight*0.1);
        hBox.setMaxSize(screenWidth*0.8,screenHeight*0.1);
        //moves the class list slightly down so it's not colliding with the title
        hBox.setTranslateY(screenHeight*0.7);
        hBox.getStylesheets().add("styleSheet.css");
        hBox.getStyleClass().add("borders");
        topContainer.getChildren().add(hBox);
        VBox.setMargin(hBox,new Insets(0,0,0,0));

        String classList[] = {"Knight","Wizard","Barbarian","Ranger"};
        for (int i=1;i<=4;i++)
        {
            //the width and height of the button is huge, because it seems to automatically scale it to fit the HBox
            Button newButton = createButton(classList[i-1],"Button2",8000,8000,0,0);

            int index = i;
            newButton.setOnAction(e -> {
                if (imageView.isVisible() == false)
                {
                    confirmButton.setVisible(true);
                    imageView.setVisible(true);
                    leftText.setVisible(true);
                    rightText.setVisible(true);
                }
                newRefreshIntro(classList[index-1],leftText,rightText);
                System.out.println(classList[index -1]);
            });

            hBox.getChildren().add(newButton);
        }

        this.stage.setScene(new Scene(root));
    }

    /*
    private void showIntro() {

        //main dude
        AnchorPane root = new AnchorPane();
        root.getStylesheets().add("styleSheet.css");
        root.setPrefSize(screenWidth,screenHeight);

        //class buttons
        HBox hbox = new HBox();
        hbox.setAlignment(Pos.CENTER);
        hbox.setPrefSize(screenWidth*.5,screenHeight*.2);
        hbox.setLayoutX(screenWidth*.25);
        hbox.setLayoutY(screenHeight*.01);

        javaFXPlayer player = new javaFXPlayer();

        //hbox children
        Button knightBtn = createButton("Knight",screenWidth*0.1,screenHeight*0.05,0,0);
        Button wizardBtn = createButton("Wizard",screenWidth*0.1,screenHeight*0.05,0,0);
        Button barbarianBtn = createButton("Barbarian",screenWidth*0.1,screenHeight*0.05,0,0);
        Button rangerBtn = createButton("Ranger",screenWidth*0.1,screenHeight*0.05,0,0);

        //class details container
        BorderPane bpane = new BorderPane();
        bpane.setPrefSize(screenWidth*.5,screenHeight*.75);
        bpane.setLayoutX(screenWidth*.2);
        bpane.setLayoutY(screenHeight*.2);

        //children for bpane
        Text introText = createText("ClassIntro","Century","White",15,0,0);
        VBox statVBox = new VBox();
        statVBox.setSpacing(25);
        VBox itemVBox = new VBox();
        itemVBox.setSpacing(50);

        //setting bpane children alignment
        bpane.setLeft(statVBox);
        bpane.setRight(itemVBox);
        bpane.setCenter(introText);

        //children for itemVbox
        Text item1Text = createText("Item 1: ","Century","White",15,0,0);
        Text item2Text = createText("Item 2: ","Century","White",15,0,0);
        Text item3Text = createText("Item 3: ","Century","White",15,0,0);
        Text item4Text = createText("Item 4: ","Century","White",15,0,0);

        //children for statVbox
        Text hpText = createText("Health: ","Century","White",15,0,0);
        Text atkText = createText("Attack: ","Century","White",15,0,0);
        Text manaText = createText("Mana: ","Century","White",15,0,0);
        Text goldText = createText("Gold: ","Century","White",15,0,0);

        //adding children to Vboxes
        statVBox.getChildren().addAll(hpText,atkText,manaText,goldText);
        itemVBox.getChildren().addAll(item1Text,item2Text,item3Text,item4Text);

        //anchor root children
        Text chooseText = createText("Choose Your Class","Century", "White",25,21,27);
        Button nextBtn = createButton("Continue",149,39,429,335);

        //class actions
        knightBtn.setOnAction(e -> {
            refreshIntro(0,player,hpText,atkText,manaText,goldText,item1Text,item2Text,item3Text,item4Text);
        });
        wizardBtn.setOnAction(e -> {
            refreshIntro(1,player,hpText,atkText,manaText,goldText,item1Text,item2Text,item3Text,item4Text);
        });
        barbarianBtn.setOnAction(e -> {
            refreshIntro(2,player,hpText,atkText,manaText,goldText,item1Text,item2Text,item3Text,item4Text);
        });
        rangerBtn.setOnAction(e -> {
            refreshIntro(3,player,hpText,atkText,manaText,goldText,item1Text,item2Text,item3Text,item4Text);
        });

        //next action
        nextBtn.setOnAction(e -> {
            try{
                gameLoop();
            }
            catch (NullPointerException exception){
                System.out.println("no class");
            }
        });

        //adding children to HBox and Root
        hbox.getChildren().addAll(knightBtn,wizardBtn,barbarianBtn,rangerBtn);
        root.getChildren().addAll(nextBtn,chooseText,hbox,bpane);

        this.stage.setScene(new Scene(root));
    }

     */

    /*
    private void gameLoop(){
        //TO DO: While Pit here similar to the one in Server

        showGameMenu();
    }

    private void showGameMenu() {
        AnchorPane root = new AnchorPane();
        root.getStylesheets().add("styleSheet.css");

        root.setPrefSize(600,400);

        HBox hbox = new HBox();
        hbox.setPrefSize(497,197);
        hbox.setLayoutX(52);
        hbox.setLayoutY(200);
        hbox.setSpacing(50);

        Button continueBtn = createButton("Continue Forward",149,39,0,0);
        Button statusBtn = createButton("Check Status",149,39,0,0);
        Button spellsBtn = createButton("Spells",149,39,0,0);
        Button itemsBtn = createButton("Items",149,39,0,0);

        hbox.getChildren().addAll(continueBtn,statusBtn,spellsBtn,itemsBtn);
        root.getChildren().addAll(hbox);

        this.stage.setScene(new Scene(root));
    }
     */

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

        Text text = createText("Game","Century","White",(screenHeight+screenWidth)/15,0,screenHeight*0.1);
        Button btn1 = createButton("Start","Button1",screenWidth*0.1,screenHeight*0.025,0,screenHeight*0.3);
        Button quitButton = createButton("Quit","Button1",screenWidth*0.1,screenHeight*0.025,0,screenHeight*0.325);

        btn1.setOnAction(e ->
        {
        //Intro
            newShowIntro(player);
        });
        quitButton.setOnAction(e ->
       {
           System.exit(0);
       });

        vbox.getChildren().addAll(text,btn1,quitButton,divider);
        
        return scene;
    }

}
