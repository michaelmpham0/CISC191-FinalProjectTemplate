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
    private Label createLabel(String text,String fontType,double fontDividor,double width,double height)
    {
        Label newLabel = new Label();
        newLabel.setText(text);
        newLabel.getStylesheets().add("styleSheet.css");
        newLabel.setFont(new Font(fontType,(screenHeight+screenWidth)/fontDividor));
        newLabel.setMinSize(screenWidth*width,screenHeight*height);
        newLabel.setPrefSize(screenWidth*width,screenHeight*height);
        newLabel.setMaxSize(screenWidth*width,screenHeight*height);
        newLabel.setTextAlignment(TextAlignment.CENTER);
        newLabel.setAlignment(Pos.CENTER);
        newLabel.getStylesheets().add("styleSheet.css");
        return newLabel;
    }


    private void refreshIntro(Player player,Label leftText,Label rightText)
    {

        String leftString = "";
        leftString += "Health\n"+player.getHealth()+"\n\n";
        leftString += "Attack\n"+player.getAttack()+"\n\n";
        leftString += "Mana\n"+player.getMana()+"\n\n";
        leftString += "Gold\n"+player.getGold()+"\n\n";
        leftText.setText(leftString);
        rightText.setText(TextDisplay.getText(player.getPlayerClass()+"Intro"));
    }

    private void statusMenu(Player player,Inventory storage)
    {
        BorderPane root = new BorderPane();
        root.getStylesheets().add("styleSheet.css");
        root.getStyleClass().add("borders");
        root.setPrefSize(screenWidth,screenHeight);

        VBox vBox = new VBox();
        root.setCenter(vBox);
        vBox.setAlignment(Pos.CENTER);
        vBox.setMaxSize(screenWidth*0.9,screenHeight*0.9);
        vBox.setSpacing(0.0);
        vBox.setPadding(new Insets(0,0,0,0));
        vBox.getStylesheets().add("styleSheet.css");

        Label titleText = createLabel("Status","Century",90,0.3,0.05);
        titleText.setTranslateY(-screenHeight*0.1);
        vBox.getChildren().add(titleText);

        HBox textContainer =  new HBox();
        textContainer.setAlignment(Pos.CENTER);
        textContainer.setPrefSize(screenWidth*0.5,screenHeight*0.5);
        textContainer.setMaxSize(screenWidth*0.5,screenHeight*0.5);
        textContainer.setTranslateY(-screenHeight*0.05);
        textContainer.getStylesheets().add("styleSheet.css");
        textContainer.getStyleClass().add("borders");
        vBox.getChildren().add(textContainer);

        Label leftText = createLabel("","Century",80,0.25,0.5);
        leftText.setAlignment(Pos.CENTER);
        leftText.getStyleClass().add("noBorder");
        textContainer.getChildren().add(leftText);

        String leftString = "";
        leftString += "Level - "+player.getLevel()+"\n\n";
        leftString += "Health - "+player.getHealth()+"\n\n";
        leftString += "Attack - "+player.getAttack()+"\n\n";
        leftString += "Mana - "+player.getMana()+"\n\n";
        leftString += "Gold - "+player.getGold()+"\n\n";
        leftText.setText(leftString);

        VBox rightTextBox = new VBox();
        rightTextBox.setAlignment(Pos.CENTER);
        rightTextBox.setPrefSize(screenWidth*0.25,screenHeight*0.5);
        rightTextBox.setMinSize(screenWidth*0.25,screenHeight*0.5);
        rightTextBox.setMaxSize(screenWidth*0.25,screenHeight*0.5);
        rightTextBox.getStylesheets().add("styleSheet.css");
        rightTextBox.getStyleClass().add("noBorder");
        textContainer.getChildren().add(rightTextBox);

        Label nameLabel = createLabel("Name - "+player.getName(),"Century",120,0.25,0.025);
        nameLabel.setAlignment(Pos.CENTER);
        nameLabel.setWrapText(true);
        nameLabel.getStyleClass().add("noBorder");
        rightTextBox.getChildren().add(nameLabel);

        Label weaponLabel = createLabel("","Century",120,0.25,0.15);
        weaponLabel.setAlignment(Pos.CENTER);
        weaponLabel.getStyleClass().add("noBorder");
        weaponLabel.setWrapText(true);
        weaponLabel.setText("Current Weapon - "+player.getCurrentWeapon()+"\n"+player.getCurrentWeapon().getItemDesc()+"\nDamage: "+player.getCurrentWeapon().getWeaponDamage());
        weaponLabel.setTranslateY(screenHeight*0.025);
        rightTextBox.getChildren().add(weaponLabel);

        Label toolLabel = createLabel("","Century",120,0.25,0.15);
        toolLabel.setAlignment(Pos.CENTER);
        toolLabel.getStyleClass().add("noBorder");
        toolLabel.setWrapText(true);
        toolLabel.setText("Current Tool - "+player.getCurrentTool()+"\n"+player.getCurrentTool().getItemDesc());
        toolLabel.setTranslateY(screenHeight*0.05);
        rightTextBox.getChildren().add(toolLabel);

        Button confirmButton = createButton("Go Back","Button2",screenWidth*0.1,screenHeight*0.025,0,0);
        confirmButton.setTranslateY(screenHeight*0.05);
        vBox.getChildren().add(confirmButton);
        confirmButton.setOnAction(e -> {
            exploreMenu(player,storage);
        });

        stage.setScene(new Scene(root));
    }

    private Items currentItem = null;

    private void updateItem(Items item,Label name,Label description,Label count){
        currentItem = item;
        name.setText(item.getItemName());
        description.setText(item.getItemDesc());
        count.setText("x" + item.getHoldSize() + " "+item.getItemName());
    }

    private void itemMenu(Player player, Inventory storage){
        BorderPane root = new BorderPane();
        root.setPrefSize(screenWidth,screenHeight);
        root.setMinSize(screenWidth,screenHeight);
        root.setMaxSize(screenWidth,screenHeight);
        root.getStylesheets().add("styleSheet.css");
        root.getStyleClass().add("borders");

        VBox vbox = new VBox();
        root.setCenter(vbox);
        vbox.setAlignment(Pos.TOP_CENTER);
        vbox.setPrefSize(screenWidth*0.9,screenHeight*0.9);
        vbox.setMinSize(screenWidth*0.9,screenHeight*0.9);
        vbox.setMaxSize(screenWidth*0.9,screenHeight*0.9);
        vbox.getStylesheets().add("styleSheet.css");

        Label titleText = new Label();
        titleText.setAlignment(Pos.CENTER);
        titleText.setText("Inventory");
        titleText.setPrefSize(screenWidth*0.3,screenHeight*0.05);
        titleText.setMinSize(screenWidth*0.3,screenHeight*0.05);
        titleText.setMaxSize(screenWidth*0.3,screenHeight*0.05);
        titleText.setTranslateY(screenHeight*0.05);
        titleText.getStylesheets().add("styleSheet.css");
        vbox.getChildren().add(titleText);

        HBox itemContainer = new HBox();
        itemContainer.setAlignment(Pos.CENTER);
        itemContainer.setPrefSize(screenWidth*0.8,screenHeight*0.5);
        itemContainer.setMinSize(screenWidth*0.8,screenHeight*0.5);
        itemContainer.setMaxSize(screenWidth*0.8,screenHeight*0.5);
        itemContainer.setTranslateY(screenHeight*0.1);
        itemContainer.getStylesheets().add("styleSheet.css");
        itemContainer.getStyleClass().add("noBorder");
        vbox.getChildren().add(itemContainer);

        VBox itemList = new VBox();
        itemList.setAlignment(Pos.TOP_CENTER);
        itemList.setPrefSize(screenWidth*0.35,screenHeight*2);
        itemList.setMinSize(screenWidth*0.35,screenHeight*2);
        itemList.setMaxSize(screenWidth*0.35,screenHeight*2);
        itemList.setTranslateX(-screenWidth*0.025);
        itemList.getStylesheets().add("styleSheet.css");
        itemList.getStyleClass().add("borders");
        itemList.setPadding(new Insets(screenWidth*0.015,screenWidth*0.015,screenWidth*0.015,0));
        itemList.setSpacing((screenHeight+screenWidth)/300);
        //itemContainer.getChildren().add(itemList);


        ScrollPane scrollPane = new ScrollPane();
        scrollPane.setFitToHeight(true);
        scrollPane.setFitToWidth(true);
        scrollPane.setPrefSize(screenWidth*0.3,screenHeight*0.5);
        scrollPane.setMinSize(screenWidth*0.3,screenHeight*0.5);
        scrollPane.setMaxSize(screenWidth*0.3,screenHeight*0.5);
        scrollPane.setMinViewportWidth(screenWidth*0.3);
        scrollPane.setMinViewportHeight(screenHeight*0.5);
        scrollPane.setPrefViewportWidth(screenWidth*0.3);
        scrollPane.setPrefViewportHeight(screenHeight*0.5);
        scrollPane.getStylesheets().add("styleSheet.css");
        itemContainer.getChildren().add(scrollPane);

        scrollPane.setContent(itemList);
        scrollPane.setPannable(false);
        scrollPane.setVbarPolicy(ScrollPane.ScrollBarPolicy.ALWAYS);
        scrollPane.setHbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);

        //Borderpane for easier organization
        VBox itemDetails = new VBox();
        itemDetails.setAlignment(Pos.CENTER);
        itemDetails.setPrefSize(screenWidth*0.35,screenHeight*0.5);
        itemDetails.setMinSize(screenWidth*0.35,screenHeight*0.5);
        itemDetails.setMaxSize(screenWidth*0.35,screenHeight*0.5);
        itemDetails.setTranslateX(screenWidth*0.025);
        itemDetails.setPadding(new Insets(0,0,0,0));
        itemDetails.getStylesheets().add("styleSheet.css");
        itemDetails.getStyleClass().add("borders");
        itemContainer.getChildren().add(itemDetails);

        //Details for each item when clicked
        Label itemName = createLabel("Item Name","Century",100,0.3,0.1);
        itemName.setTextAlignment(TextAlignment.CENTER);
        itemName.setWrapText(true);
        itemName.setTranslateY(-screenHeight*0.075);
        itemName.getStylesheets().add("styleSheet.css");
        itemName.getStyleClass().add("noBorder");
        itemDetails.getChildren().add(itemName);

        Label itemDescription = createLabel("Item Description","Century",100,0.3,0.1);
        itemDescription.setTextAlignment(TextAlignment.CENTER);
        itemDescription.setWrapText(true);
        itemDescription.getStylesheets().add("styleSheet.css");
        itemDescription.getStyleClass().add("noBorder");
        itemDetails.getChildren().add(itemDescription);

        Label itemCount = createLabel("x Count Item","Century",100,0.3,0.1);
        itemCount.setTextAlignment(TextAlignment.CENTER);
        itemCount.setWrapText(true);
        itemCount.setTranslateY(screenHeight*0.075);
        itemCount.getStylesheets().add("styleSheet.css");
        itemCount.getStyleClass().add("noBorder");
        itemDetails.getChildren().add(itemCount);

        itemName.setVisible(false);
        itemDescription.setVisible(false);
        itemCount.setVisible(false);

        //Creates buttons for each item
        for (Items invItems: storage.getInventory()) {
            Button itemButton = createButton(invItems.getItemName(),"Button2",screenWidth*0.25,screenHeight*0.075,0,0);
            itemButton.setWrapText(true);
            itemList.getChildren().add(itemButton);
            //Item Button activation
            itemButton.setOnAction(e -> {
                if (itemName.isVisible() == false)
                {
                    itemName.setVisible(true);
                    itemDescription.setVisible(true);
                    itemCount.setVisible(true);
                }
                updateItem(invItems,itemName,itemDescription,itemCount);
            });
        }

        //this hides itemdetails for some reason
        Button useButton = createButton("Use Item","Button2",screenWidth*0.1,screenHeight*0.05,0,0);
        itemDetails.getChildren().add(useButton);
        useButton.setTranslateY(screenHeight*0.1);
        useButton.setOnAction(e ->{
            System.out.println(currentItem);
            if (currentItem instanceof Weapons || currentItem instanceof Tools)
            {
                player.equipWeaponOrTool(storage,currentItem);
            }
            itemMenu(player,storage);
        });

        Button backButton = createButton("Go Back","Button2",screenWidth*0.1,screenHeight*0.05,0,0);
        backButton.setTranslateY(screenHeight*0.2);
        vbox.getChildren().add(backButton);
        backButton.setOnAction(e -> {
            exploreMenu(player,storage);
                });


 //     root.setRight(useButton);

        stage.setScene(new Scene(root));
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

    private void exploreMenu(Player player,Inventory storage)
    {
        BorderPane root = new BorderPane();
        root.getStylesheets().add("styleSheet.css");
        root.setPrefSize(screenWidth,screenHeight);

        VBox vBox = new VBox();
        root.setCenter(vBox);
        vBox.setAlignment(Pos.CENTER);
        vBox.setMaxSize(screenWidth*0.9,screenHeight*0.9);
        vBox.setSpacing(0.0);
        vBox.setPadding(new Insets(0,0,0,0));

        vBox.getStylesheets().add("styleSheet.css");
        //vBox.getStyleClass().add("borders");

        Label middleTextBox = new Label();
        middleTextBox.setVisible(false);
        middleTextBox.getStylesheets().add("styleSheet.css");
        middleTextBox.setFont(new Font("Century",(screenHeight+screenWidth)/75));
        middleTextBox.setTextAlignment(TextAlignment.CENTER);
        middleTextBox.setAlignment(Pos.CENTER);
        middleTextBox.setPrefSize(screenWidth*0.8,screenHeight*0.45);
        middleTextBox.setMinSize(screenWidth*0.8,screenHeight*0.45);
        middleTextBox.setMaxSize(screenWidth*0.8,screenHeight*0.45);
        middleTextBox.setTranslateY(-screenHeight*0.075);

        vBox.getChildren().add(middleTextBox);

        HBox buttonContainer = new HBox();
        buttonContainer.getStylesheets().add("styleSheet.css");
        buttonContainer.getStyleClass().add("borders");
        buttonContainer.setAlignment(Pos.CENTER);
        buttonContainer.setPrefSize(screenWidth*0.8,screenHeight*0.1);
        buttonContainer.setMinSize(screenWidth*0.8,screenHeight*0.1);
        buttonContainer.setMaxSize(screenWidth*0.8,screenHeight*0.1);
        buttonContainer.setTranslateY(screenHeight*0.175);
        buttonContainer.setPadding(new Insets(screenWidth*0.015,screenWidth*0.015,screenWidth*0.015,screenWidth*0.015));
        buttonContainer.setSpacing(screenWidth*0.025);
        vBox.getChildren().add(buttonContainer);

        String[] buttonList = {"Continue Forward","Check Status","Spells","Items"};

        for (int i=1;i<=4;i++)
        {
            //the width and height of the button is huge, because it seems to automatically scale it to fit the HBox
            Button newButton = createButton(buttonList[i-1],"Button2",8000,8000,0,0);

            int index = i;
            newButton.setOnAction(e -> {
                switch (index)
                {
                    case 1:
                        // Continue Forward
                        break;
                    case 2:
                        // Check Status
                        statusMenu(player,storage);
                        //stage.setScene(new Scene(root));
                        break;
                    case 3:
                        // Spells
                        break;
                    case 4:
                        itemMenu(player,storage);
                        // Items
                        break;
                }
            });

            buttonContainer.getChildren().add(newButton);
        }

        stage.setScene(new Scene(root));
    }

    private void showIntro(Player player)
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
        nameField.setText("Enter Name");
        nameField.setVisible(false);
        nameField.setTranslateY(screenWidth*0.025);
        nameField.setPrefSize(screenWidth*0.15,screenHeight*0.02);
        nameField.setMaxSize(screenWidth*0.15,screenHeight*0.02);
        topContainer.getChildren().addAll(nameField);

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

        //left text box for stats
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


        //images for the classes


        Label imageContainer = new Label();
        imageContainer.getStylesheets().add("styleSheet.css");
        imageContainer.getStyleClass().add("noBorder");
        imageContainer.setAlignment(Pos.CENTER);
        imageContainer.setPrefSize(imageWidth,imageHeight);
        imageContainer.setMinSize(imageWidth,imageHeight);
        imageContainer.setMaxSize(imageWidth,imageHeight);
        middleContainer.getChildren().add(imageContainer);

        /*
        Image newImage = new Image("file:Server/src/main/resources/Images/knightclass.png");
        ImageView imageView = new ImageView(newImage);
        imageView.setPreserveRatio(true);
        imageView.setFitHeight(imageHeight);
        imageView.setFitWidth(imageWidth);
        imageContainer.setGraphic(imageView);
        imageView.setVisible(false);

         */


        Button confirmButton = createButton("Confirm","Button2",screenWidth*0.1,screenHeight*0.025,0,0);
        confirmButton.setVisible(false);
        confirmButton.setTranslateY(-screenHeight*0.2);
        vBox.setMargin(confirmButton,new Insets(0,0,0,0));
        vBox.getChildren().add(confirmButton);

        //right text box for class backstory
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


        String classList[] = player.getClassList();
        Map<String, Image> classImages = new HashMap<>();
        classImages.put("Knight", new Image("file:Server/src/main/resources/Images/knightclass.png"));
        classImages.put("Wizard", new Image("file:Server/src/main/resources/Images/wizardclass.png"));
        classImages.put("Barbarian", new Image("file:Server/src/main/resources/Images/barbarianclass.png"));
        classImages.put("Ranger", new Image("file:Server/src/main/resources/Images/rangerclass.png"));

        for (int i = 0; i < classList.length; i++)
        {

            //the width and height of the button is huge, because it seems to automatically scale it to fit the HBox
            String className = classList[i];
            Button newButton = createButton(className,"Button2",8000,8000,0,0);

            int index = i;
            newButton.setOnAction(e -> {

            Image classImage = new Image(classImages.get(className).toString());
            ImageView newImageView = new ImageView(classImage);
            newImageView.setPreserveRatio(true);
            newImageView.setFitHeight(imageHeight);
            newImageView.setFitWidth(imageWidth);
            imageContainer.setGraphic(newImageView);


                if (newImageView.isVisible() == false)
                {
                    nameField.setVisible(true);
                    confirmButton.setVisible(true);
                    newImageView.setVisible(true);
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
                if (!nameField.getText().equals("Enter Name"))
                {
                    player.setName(nameField.getText());
                    // else, name is "Unknown"
                }

                Inventory storage = new Inventory(player.getPlayerClass());
                ArrayList<Items> playerInventoryList = storage.getInventory();
                Boolean foundWeapon = false;
                Boolean foundTool = false;

                //equip variables, because i got an error for modifiying an array while looping through it
                Items equipWeapon = player.getCurrentWeapon();
                Items equipTool = player.getCurrentTool();

                // For each loop to go through starting inventory and equip weapons
                for (Items item : playerInventoryList)
                {
                    if (item instanceof Weapons && foundWeapon == false)
                    {
                        if (player.getCurrentWeapon().getItemName().equals("None"))
                        {
                            foundWeapon = true;
                            equipWeapon = item;
                        }
                    }
                    else if (item instanceof Tools && foundTool == false)
                    {
                        if (player.getCurrentTool().getItemName().equals("None"))
                        {
                            foundTool = true;
                            equipTool = item;
                        }
                    }
                }
                player.equipWeaponOrTool(storage,equipWeapon);
                player.equipWeaponOrTool(storage,equipTool);
                exploreMenu(player,storage);
            }
        });

        this.stage.setScene(new Scene(root));
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

        Text text = createText("Game","Century","White",(screenHeight+screenWidth)/15,0,screenHeight*0.1);
        Button btn1 = createButton("Start","Button1",screenWidth*0.1,screenHeight*0.025,0,screenHeight*0.3);
        Button quitButton = createButton("Quit","Button1",screenWidth*0.1,screenHeight*0.025,0,screenHeight*0.325);

        btn1.setOnAction(e ->
        {
        //Intro
            showIntro(player);
        });
        quitButton.setOnAction(e ->
       {
           System.exit(0);
       });

        vbox.getChildren().addAll(text,btn1,quitButton,divider);
        
        return scene;
    }

}
