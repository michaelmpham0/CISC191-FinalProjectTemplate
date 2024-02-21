package edu.sdccd.cisc191.template;

import javafx.geometry.Pos;
import javafx.scene.layout.*;
import javafx.scene.paint.Paint;
import javafx.scene.text.Font;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.text.Text;

public class GUIController extends GUIMain {

    private  Scene scene;

    private Text createText(String textString, String font, String color , double size, double xPos, double yPos) {
        Text text = new Text(textString);
        text.setFont(new Font(font,size));
        text.setFill(Paint.valueOf(color));
        text.setTranslateX(xPos);
        text.setTranslateY(yPos);
        return text;
    }
    private Button createButton(String textString, double width, double height, double xPos, double yPos) {
        Button button = new Button(textString);
        button.setPrefSize(width,height);
        button.setTranslateX(xPos);
        button.setTranslateY(yPos);
        return button;
    }

    private  void refreshIntro(int playerClass, javaFXPlayer player,Text hp, Text atk, Text mna, Text gld, Text itm1, Text itm2, Text itm3, Text itm4) {
        player.setUp(playerClass);
        hp.setText("Health: "+ player.getHealth());
        atk.setText("Attack: " + player.getAttack());
        mna.setText("Mana: " + player.getMana());
        gld.setText("Gold: " + player.getGold());
    }

    private void showIntro() {

        //main dude
        AnchorPane root = new AnchorPane();
        root.setPrefSize(600,400);

        //class buttons
        HBox hbox = new HBox();
        hbox.setAlignment(Pos.CENTER);
        hbox.setPrefSize(337,75);
        hbox.setLayoutX(132);
        hbox.setLayoutY(14);

        javaFXPlayer player = new javaFXPlayer();

        //hbox children
        Button knightBtn = createButton("Knight",149,39,0,0);
        Button wizardBtn = createButton("Wizard",149,39,0,0);
        Button barbarianBtn = createButton("Barbarian",149,39,0,0);
        Button rangerBtn = createButton("Ranger",149,39,0,0);

        //class details container
        BorderPane bpane = new BorderPane();
        bpane.setPrefSize(380,303);
        bpane.setLayoutX(32);
        bpane.setLayoutY(71);

        //children for bpane
        Text introText = createText("ClassIntro","Arial","Black",15,0,0);
        VBox statVBox = new VBox();
        statVBox.setSpacing(25);
        VBox itemVBox = new VBox();
        itemVBox.setSpacing(50);

        //setting bpane children alignment
        bpane.setLeft(statVBox);
        bpane.setRight(itemVBox);
        bpane.setCenter(introText);

        //children for itemVbox
        Text item1Text = createText("Item 1: ","Arial","Black",15,0,0);
        Text item2Text = createText("Item 2: ","Arial","Black",15,0,0);
        Text item3Text = createText("Item 3: ","Arial","Black",15,0,0);
        Text item4Text = createText("Item 4: ","Arial","Black",15,0,0);

        //children for statVbox
        Text hpText = createText("Health: ","Arial","Black",15,0,0);
        Text atkText = createText("Attack: ","Arial","Black",15,0,0);
        Text mnaText = createText("Mana: ","Arial","Black",15,0,0);
        Text gldText = createText("Gold: ","Arial","Black",15,0,0);

        //adding children to Vboxes
        statVBox.getChildren().addAll(hpText,atkText,mnaText,gldText);
        itemVBox.getChildren().addAll(item1Text,item2Text,item3Text,item4Text);

        //anchor root children
        Text chooseText = createText("Choose Your Class","Arial", "Black",25,21,27);
        Button nextBtn = createButton("Continue",149,39,429,335);

        //class actions
        knightBtn.setOnAction(e -> {
            refreshIntro(0,player,hpText,atkText,mnaText,gldText,item1Text,item2Text,item3Text,item4Text);
        });
        wizardBtn.setOnAction(e -> {
            refreshIntro(1,player,hpText,atkText,mnaText,gldText,item1Text,item2Text,item3Text,item4Text);
        });
        barbarianBtn.setOnAction(e -> {
            refreshIntro(2,player,hpText,atkText,mnaText,gldText,item1Text,item2Text,item3Text,item4Text);
        });
        rangerBtn.setOnAction(e -> {
            refreshIntro(3,player,hpText,atkText,mnaText,gldText,item1Text,item2Text,item3Text,item4Text);
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

    private void gameLoop(){
        //TO DO: While Pit here similar to the one in Server

        showGameMenu();
    }
    private void showGameMenu() {
        AnchorPane root = new AnchorPane();
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

   public Scene showMainMenu(){

        VBox vbox = new VBox();
       vbox.setPrefSize(600,400);
       vbox.setAlignment(Pos.TOP_CENTER);


        scene = new Scene(vbox);

        scene.setFill(Paint.valueOf("Black"));

        Text text = createText("Game","Arial","Black",100,0,0);
        Button btn1 = createButton("Start",200,50,0,25);
       // btn1.setStyle("-fx-background-radius: 25; -fx-background-color: white;");

         btn1.setOnAction(e -> {
        //Intro
             showIntro();
        });

       vbox.getChildren().addAll(text,btn1);
        
        return scene;
    }

}
