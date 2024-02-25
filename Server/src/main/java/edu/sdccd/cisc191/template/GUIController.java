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

    private void updateItem(Items item,Text name,Text description,Text count){
        name.setText(item.getItemName());
        description.setText(item.getItemDesc());
        count.setText("x" + item.getHoldSize() + " "+item.getItemName());
    }

    private void itemMenu(Player player){
        BorderPane root = new BorderPane();
        root.getStylesheets().add("styleSheet.css");
        root.getStyleClass().add("borders");
        root.setPrefSize(screenWidth,screenHeight);

        ScrollPane scrollPane = new ScrollPane();
        
        root.setCenter(scrollPane);
        scrollPane.setMaxSize(screenWidth*0.5,screenHeight*0.2);
        scrollPane.setTranslateY(-screenHeight*0.3);
        scrollPane.setTranslateX(-screenWidth*0.1);
        scrollPane.getStylesheets().add("styleSheet.css");

        Label titleText = new Label();
        titleText.setAlignment(Pos.CENTER);
        titleText.setText("Inventory");
        titleText.setPrefSize(screenWidth*0.3,screenHeight*0.05);
        titleText.setMaxSize(screenWidth*0.3,screenHeight*0.05);
        root.setTop(titleText);
        titleText.setTranslateY(screenHeight*0.05);
        titleText.setTranslateX(screenWidth*0.2);
        titleText.getStylesheets().add("styleSheet.css");

        HBox itemContainer = new HBox();
        itemContainer.setSpacing(25);

        //Console check
        Inventory inventoryCheck = new Inventory(player.getPlayerClass());
        System.out.println(inventoryCheck.printItems());

        //Borderpane for easier organization
        BorderPane itemDetails = new BorderPane();
        itemDetails.setMaxSize(screenWidth*0.05,screenHeight*0.75);
        itemDetails.setTranslateY(screenHeight*0.01);
        itemDetails.setTranslateX(-screenWidth*0.1);
        itemDetails.setPadding(new Insets(0,0,0,0));
        itemDetails.getStylesheets().add("styleSheet.css");
        itemDetails.getStyleClass().add("borders");

        //Details for each item when clicked
        Text itemName = createText("Item Name","Century","White",(screenHeight+screenWidth)/100,0,screenHeight*0.05);
        itemName.setTextAlignment(TextAlignment.CENTER);
        itemName.setWrappingWidth(screenWidth*0.2);

        Text itemDescription = createText("Item Description","Century","White",(screenHeight+screenWidth)/100,0,0);
        itemDescription.setTextAlignment(TextAlignment.CENTER);
        itemDescription.setWrappingWidth(screenWidth*0.2);

        Text itemCount = createText("xCount Item","Century","White",(screenHeight+screenWidth)/100,0,-screenHeight*0.05);
        itemCount.setTextAlignment(TextAlignment.CENTER);
        itemCount.setWrappingWidth(screenWidth*0.2);

        itemDetails.setTop(itemName);
        itemDetails.setCenter(itemDescription);
        itemDetails.setBottom(itemCount);

        //Creates buttons for each item
        for (Items invItems: inventoryCheck.getInventory()) {
            Button itemButton = createButton(invItems.getItemName(),"Button2",screenWidth*0.1,screenHeight*0.15,0,0);
            itemButton.setWrapText(true);
            itemContainer.getChildren().add(itemButton);
            //Item Button activation
            itemButton.setOnAction(e -> {
                updateItem(invItems,itemName,itemDescription,itemCount);
            });
        }

        //this hides itemdetails for some reason
     //   Button useButton = createButton("Use","Button2",screenWidth*0.1,screenHeight*0.05,-screenWidth*0.05,screenHeight*0.8);

        Button backButton = createButton("Back","Button2",screenWidth*0.1,screenHeight*0.05,screenWidth*0.05,screenHeight*0.9);

        backButton.setOnAction(e -> {
            exploreMenu(player);
                });

        root.setLeft(backButton);
        root.setRight(itemDetails);
 //       root.setRight(useButton);

        scrollPane.setContent(itemContainer);
        scrollPane.setPannable(true);
        scrollPane.setHbarPolicy(ScrollPane.ScrollBarPolicy.ALWAYS);

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

    private void statusMenu(Player player)
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

        Label titleText = new Label();
        titleText.setAlignment(Pos.CENTER);
        titleText.setText("Status");
        titleText.setPrefSize(screenWidth*0.3,screenHeight*0.05);
        titleText.setMaxSize(screenWidth*0.3,screenHeight*0.05);
        titleText.setTranslateY(-screenHeight*0.1);
        vBox.getChildren().add(titleText);
        titleText.getStylesheets().add("styleSheet.css");

        HBox textContainer =  new HBox();
        textContainer.setAlignment(Pos.CENTER);
        textContainer.setPrefSize(screenWidth*0.5,screenHeight*0.5);
        textContainer.setMaxSize(screenWidth*0.5,screenHeight*0.5);
        textContainer.setTranslateY(-screenHeight*0.05);
        textContainer.getStylesheets().add("styleSheet.css");
        textContainer.getStyleClass().add("borders");
        vBox.getChildren().add(textContainer);

        Label leftText = new Label();
        leftText.setFont(new Font("Century",(screenHeight+screenWidth)/65));
        leftText.setTextAlignment(TextAlignment.LEFT);
        leftText.setAlignment(Pos.CENTER_LEFT);
        leftText.setPrefSize(screenWidth*0.25,screenHeight*0.5);
        leftText.setMinSize(screenWidth*0.25,screenHeight*0.5);
        leftText.setMaxSize(screenWidth*0.25,screenHeight*0.5);
        leftText.getStylesheets().add("styleSheet.css");
        leftText.getStyleClass().add("noBorder");
        textContainer.getChildren().add(leftText);

        Label rightText = new Label();
        rightText.setFont(new Font("Century",(screenHeight+screenWidth)/75));
        rightText.setTextAlignment(TextAlignment.CENTER);
        rightText.setAlignment(Pos.CENTER_RIGHT);
        rightText.setPrefSize(screenWidth*0.25,screenHeight*0.5);
        rightText.setMinSize(screenWidth*0.25,screenHeight*0.5);
        rightText.setMaxSize(screenWidth*0.25,screenHeight*0.5);
        rightText.getStylesheets().add("styleSheet.css");
        rightText.getStyleClass().add("noBorder");
        textContainer.getChildren().add(rightText);

        updateStatus(player,leftText,rightText);

        stage.setScene(new Scene(root));
    }

    private void exploreMenu(Player player)
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
                        statusMenu(player);
                        //stage.setScene(new Scene(root));
                        break;
                    case 3:
                        // Spells
                        break;
                    case 4:
                        itemMenu(player);
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
        for (int i=1;i<=4;i++)
        {
            //the width and height of the button is huge, because it seems to automatically scale it to fit the HBox
            Button newButton = createButton(classList[i-1],"Button2",8000,8000,0,0);

            int index = i;
            newButton.setOnAction(e -> {
                if (imageView.isVisible() == false)
                {
                    nameField.setVisible(true);
                    confirmButton.setVisible(true);
                    imageView.setVisible(true);
                    leftText.setVisible(true);
                    rightText.setVisible(true);
                }
                player.setClass(classList[index-1]);
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
                exploreMenu(player);
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
