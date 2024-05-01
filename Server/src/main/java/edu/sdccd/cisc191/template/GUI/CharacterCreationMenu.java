package edu.sdccd.cisc191.template.GUI;

import edu.sdccd.cisc191.template.*;
import edu.sdccd.cisci191.template.LeaderboardApplication.model.H2Player;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import org.springframework.web.client.RestTemplate;

import java.io.InputStream;

public class CharacterCreationMenu extends GUIController {

    private static void refreshIntro(Player player, Label leftText, Label rightText)
    {

        String leftString = "";
        leftString += "Health\n"+player.getHealth()+"\n\n";
        leftString += "Attack\n"+player.getAttack()+"\n\n";
        leftString += "Mana\n"+player.getMana()+"\n\n";
        leftString += "Gold\n"+player.getGold()+"\n\n";
        leftText.setText(leftString);
        rightText.setText(TextDisplay.getText(player.getPlayerClass()+"Intro"));
    }
    public static void updateImage(ImageView imageView,String className)
    {
        InputStream inputStream = CharacterCreationMenu.class.getResourceAsStream("/Images/"+className.toLowerCase()+"class.png");
        Image newImage = new Image(inputStream);
        imageView.setImage(newImage);
    }

    public static void showIntro()
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


        //name field for typing player name
        TextField nameField = new TextField();
        nameField.setAlignment(Pos.CENTER);
        nameField.setPromptText("Enter Name");
        nameField.setVisible(false);
        nameField.setTranslateY(screenWidth*0.025);
        nameField.setPrefSize(screenWidth*0.125,screenHeight*0.005);
        nameField.setMaxSize(screenWidth*0.125,screenHeight*0.005);

        nameField.getOnMouseClicked();

        topContainer.getChildren().addAll(nameField);

        HBox middleContainer = new HBox();
        middleContainer.setMouseTransparent(true);
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

        //left text box for stats
        //Label leftText = new Label();
        Label leftText = createLabel("","Times New Roman",75,imageWidth*1.2,imageHeight*1.25);
        leftText.setMinSize(imageWidth*1.2,imageHeight*1.25);
        leftText.setMaxSize(imageWidth*1.2,imageHeight*1.25);
        leftText.setVisible(false);
        leftText.setTranslateX(-screenWidth*0.025);
        middleContainer.getChildren().add(leftText);


        //images for the classes


        Label imageContainer = createLabel("","Times New Roman",0,imageWidth,imageWidth);
        imageContainer.setMinSize(imageWidth,imageHeight);
        imageContainer.setMaxSize(imageWidth,imageHeight);
        imageContainer.getStyleClass().add("noBorder");
        imageContainer.setAlignment(Pos.CENTER);
        middleContainer.getChildren().add(imageContainer);


        InputStream inputStream = CharacterCreationMenu.class.getResourceAsStream("/Images/knightclass.png");
        Image newImage = new Image(inputStream);
        ImageView imageView = new ImageView(newImage);
        imageView.setPreserveRatio(true);
        imageView.setFitHeight(imageHeight);
        imageView.setFitWidth(imageWidth);
        imageContainer.setGraphic(imageView);
        imageView.setVisible(false);

        Button confirmButton = createButton("Confirm","Button2","Times New Roman",100,0.1,0.025,0,0);
        confirmButton.setVisible(false);
        confirmButton.setTranslateY(-screenHeight*0.2);
        vBox.setMargin(confirmButton,new Insets(0,0,0,0));
        vBox.getChildren().add(confirmButton);

        //right text box for class backstory
        Label rightText = createLabel("","Times New Roman",80,imageWidth*1.3,imageHeight*1.25);
        rightText.setMinSize(imageWidth*1.3,imageHeight*1.25);
        rightText.setMaxSize(imageWidth*1.3,imageHeight*1.25);
        rightText.setVisible(false);
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
        hBox.setTranslateY(screenHeight*0.65);
        hBox.getStylesheets().add("styleSheet.css");
        hBox.getStyleClass().add("borders");
        topContainer.getChildren().add(hBox);
        VBox.setMargin(hBox,new Insets(0,0,0,0));


        String classList[] = player.getClassList();

        for (int i = 0; i < classList.length; i++)
        {

            //the width and height of the button is huge, because it seems to automatically scale it to fit the HBox
            String className = classList[i];
            Button newButton = createButton(className,"Button2","Times New Roman",100,8000,8000,0,0);
            System.out.println(className);
            int index = i;
            newButton.setOnAction(e -> {
                updateImage(imageView,className);
                if (imageView.isVisible() == false)
                {
                    nameField.setVisible(true);
                    confirmButton.setVisible(true);
                    imageView.setVisible(true);
                    leftText.setVisible(true);
                    rightText.setVisible(true);
                }
                player.setClass(classList[index]);
                refreshIntro(player,leftText,rightText);
            });

            hBox.getChildren().add(newButton);
        }
        confirmButton.setOnAction(e ->
        {
            if (!player.getPlayerClass().equals("Unknown"))
            {
                if (!nameField.getText().trim().isEmpty())
                {
                    player.setName(nameField.getText());
                    // else, name is "Unknown"
                }

                //H2 database
                try {
                    H2Player h2Player = new H2Player(1L,player.getName(),player.getPlayerClass(),player.getScore(),player.getLevel());

                    RestTemplate restTemplate = new RestTemplate();
                    restTemplate.postForObject("http://localhost:5432/players", h2Player, H2Player.class);
                }
                catch (Exception eclair){
                    System.err.println("Spring JPA not running most likely.");
                }

                storage = new Inventory(player.getPlayerClass());
                spells = new Spells(player.getPlayerClass());
                Inventory playerInventoryList = storage;
                Boolean foundWeapon = false;
                Boolean foundTool = false;

                //equip variables, because i got an error for modifiying an array while looping through it
                Items equipWeapon = player.getCurrentWeapon();
                Items equipTool = player.getCurrentTool();

                // For each loop to go through starting inventory and equip weapons

                Items currentItem = playerInventoryList.getInventoryHead();
                while (currentItem != null) {
                    System.out.println(currentItem);
                    if (currentItem instanceof Weapons && foundWeapon == false)
                    {
                        if (player.getCurrentWeapon().getItemName().equals("None"))
                        {
                            foundWeapon = true;
                            equipWeapon = currentItem;
                        }
                    }
                    else if (currentItem instanceof Tools && foundTool == false)
                    {
                        if (player.getCurrentTool().getItemName().equals("None"))
                        {
                            foundTool = true;
                            equipTool = currentItem;
                        }
                    }
                    currentItem = currentItem.next;
                }

                player.equipWeaponOrTool(storage,equipWeapon);
                player.equipWeaponOrTool(storage,equipTool);
                EventMenu.eventMenu("Intro");
                //ExploreMenu.exploreMenu();
            }
        });

        stage.setScene(new Scene(root));
    }
}
