package edu.sdccd.cisc191.template.GUI;

import edu.sdccd.cisc191.template.Inventory;
import edu.sdccd.cisc191.template.Player;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

public class StatusMenu extends GUIController {
    protected static void statusMenu()
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

        Label titleText = createLabel("Status","Times New Roman",90,0.3,0.05);
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

        Label leftText = createLabel("","Times New Roman",100,0.25,0.5);
        leftText.setAlignment(Pos.CENTER);
        leftText.getStyleClass().add("noBorder");
        textContainer.getChildren().add(leftText);

        String leftString = "";
        leftString += "Level - "+player.getLevel()+"\n\n";
        leftString += "EXP - "+player.getExperience()+"/"+player.getMaxExperience()+"\n\n";
        leftString += "Health - "+player.getHealth()+"/"+player.getMaxHealth()+"\n\n";
        leftString += "Mana - "+player.getMana()+"/"+player.getMaxMana()+"\n\n";
        leftString += "Attack - "+player.getAttack()+"\n\n";
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

        Label nameLabel = createLabel("Name - "+player.getName(),"Times New Roman",120,0.25,0.025);
        nameLabel.setAlignment(Pos.CENTER);
        nameLabel.setWrapText(true);
        nameLabel.getStyleClass().add("noBorder");
        rightTextBox.getChildren().add(nameLabel);

        Label weaponLabel = createLabel("","Times New Roman",120,0.25,0.15);
        weaponLabel.setAlignment(Pos.CENTER);
        weaponLabel.getStyleClass().add("noBorder");
        weaponLabel.setWrapText(true);
        weaponLabel.setText("Current Weapon - "+player.getCurrentWeapon()+"\n"+player.getCurrentWeapon().getItemDesc()+"\nDamage: "+player.getCurrentWeapon().getWeaponDamage());
        weaponLabel.setTranslateY(screenHeight*0.025);
        rightTextBox.getChildren().add(weaponLabel);

        Label toolLabel = createLabel("","Times New Roman",120,0.25,0.15);
        toolLabel.setAlignment(Pos.CENTER);
        toolLabel.getStyleClass().add("noBorder");
        toolLabel.setWrapText(true);
        toolLabel.setText("Current Tool - "+player.getCurrentTool()+"\n"+player.getCurrentTool().getItemDesc());
        toolLabel.setTranslateY(screenHeight*0.05);
        rightTextBox.getChildren().add(toolLabel);

        Button confirmButton = createButton("Go Back","Button2","Times New Roman",100,0.1,0.025,0,0);
        confirmButton.setTranslateY(screenHeight*0.05);
        vBox.getChildren().add(confirmButton);
        confirmButton.setOnAction(e -> {
            previousSceneCheck();
        });

        stage.setScene(new Scene(root));
    }
}
