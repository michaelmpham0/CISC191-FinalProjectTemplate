package edu.sdccd.cisc191.template.GUI;

import edu.sdccd.cisc191.template.Abilities;
import edu.sdccd.cisc191.template.Items;
import edu.sdccd.cisc191.template.Tools;
import edu.sdccd.cisc191.template.Weapons;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.TextAlignment;

public class SpellsMenu extends GUIController{
    protected static Abilities currentItem = null;

    protected static void updateItem(Abilities abilities, Label name, Label description, Label cost){
        currentItem = abilities;
        name.setText(abilities.getAbilityName());
        description.setText(abilities.getAbilityDesc());
        cost.setText("Cost : " + abilities.getAbilityCost() + " Mana");
    }

    protected static void spellsMenu(){
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
        titleText.setText("Spells");
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
        Label itemName = createLabel("Spell Name","Times New Roman",100,0.3,0.1);
        itemName.setTextAlignment(TextAlignment.CENTER);
        itemName.setWrapText(true);
        itemName.setTranslateY(-screenHeight*0.075);
        itemName.getStylesheets().add("styleSheet.css");
        itemName.getStyleClass().add("noBorder");
        itemDetails.getChildren().add(itemName);

        Label itemDescription = createLabel("Spell Description","Times New Roman",100,0.3,0.1);
        itemDescription.setTextAlignment(TextAlignment.CENTER);
        itemDescription.setWrapText(true);
        itemDescription.getStylesheets().add("styleSheet.css");
        itemDescription.getStyleClass().add("noBorder");
        itemDetails.getChildren().add(itemDescription);

        Label itemCount = createLabel("x Mana Cost","Times New Roman",100,0.3,0.1);
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
        for (Abilities spellsAbilities: spells.getSpells()) {
            Button itemButton = createButton(spellsAbilities.getAbilityName(),"Button2","Times New Roman",100,0.25,0.075,0,0);
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
                updateItem(spellsAbilities,itemName,itemDescription,itemCount);
            });
        }

        Button useButton = createButton("Use Spell","Button2","Times New Roman",100,0.1,0.05,0,0);
        itemDetails.getChildren().add(useButton);
        useButton.setTranslateY(screenHeight*0.1);
        useButton.setOnAction(e ->{
            System.out.println(currentItem);
            if (currentItem != null)
            {
                currentItem.useAbility(player);
                GUIController.updateHealthAndMana();
                usedSpell = currentItem;
                previousSpell = usedSpell;
                previousSceneCheck();
            }

        });

        Button backButton = createButton("Go Back","Button2","Times New Roman",100,0.1,0.05,0,0);
        backButton.setTranslateY(screenHeight*0.2);
        vbox.getChildren().add(backButton);
        backButton.setOnAction(e -> {
            previousSceneCheck();
        });
        stage.setScene(new Scene(root));
    }

}
