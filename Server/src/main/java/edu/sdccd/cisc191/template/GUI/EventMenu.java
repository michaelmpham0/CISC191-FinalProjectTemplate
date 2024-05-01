package edu.sdccd.cisc191.template.GUI;

import edu.sdccd.cisc191.template.Inventory;
import edu.sdccd.cisc191.template.Player;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ProgressBar;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.TextAlignment;

import java.util.HashMap;


public class EventMenu extends GUIController
{

    //prompt = the text box
    static HashMap<String, String> introEvent = new HashMap<String, String>()
    {{
        put("Prompt1.1", "You stand before the ruins of an ancient dungeon. Whatever forces or circumstances brought you here do not matter. Standing beneath a crumbling stone archway, you take your first step down the depths of the dungeon.");
        put("Answer1.1", "Enter The Dungeon");
        put("Prompt2.1", "Having entered the dungeon proper, a cold atmosphere suddenly settles upon you. Despite the proximity to the surface, the air grows stale and musty.");
        put("Answer2.1", "Continue Forward");
        put("Prompt3.1", "You don't break your stride and march onwards. A tickle runs down your spine, bringing you to a halt. You notice a murky fog in front of you. The air becomes stifling, unsure what lies ahead of you. An unsettling intuition grabs your attention to a large door in the side of the hall.");
        put("Answer3.1", "Continue Forward");
        put("Answer3.2","Take The Side Path");
    }};
    protected static int dialogueBranch = 1;
    protected  static int dialogueSubBranch = 1;
    static HashMap<String, String> currentDialogueTree = null;

    protected static void eventMenu(String eventName)
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
        middleTextBox.getStylesheets().add("styleSheet.css");
        middleTextBox.getStyleClass().add("borders");
        middleTextBox.setFont(new Font("Times New Roman",(screenHeight+screenWidth)/75));
        middleTextBox.setTextAlignment(TextAlignment.CENTER);
        middleTextBox.setWrapText(true);
        middleTextBox.setAlignment(Pos.CENTER);
        middleTextBox.setPrefSize(screenWidth*0.8,screenHeight*0.55);
        middleTextBox.setMinSize(screenWidth*0.8,screenHeight*0.55);
        middleTextBox.setMaxSize(screenWidth*0.8,screenHeight*0.55);
        middleTextBox.setTranslateY(-screenHeight*0.1);
        vBox.getChildren().add(middleTextBox);

        HBox buttonContainer = new HBox();
        buttonContainer.getStylesheets().add("styleSheet.css");
        buttonContainer.getStyleClass().add("borders");
        buttonContainer.setAlignment(Pos.CENTER);
        buttonContainer.setPrefSize(screenWidth*0.8,screenHeight*0.1);
        buttonContainer.setMinSize(screenWidth*0.8,screenHeight*0.1);
        buttonContainer.setMaxSize(screenWidth*0.8,screenHeight*0.1);
        buttonContainer.setTranslateY(screenHeight*0.05);
        buttonContainer.setPadding(new Insets(screenWidth*0.015,screenWidth*0.015,screenWidth*0.015,screenWidth*0.015));
        buttonContainer.setSpacing(screenWidth*0.025);
        vBox.getChildren().add(buttonContainer);

        lastScene = new Scene(root);
        stage.setScene(lastScene);

        dialogueBranch = 1;
        dialogueSubBranch = 1;
        boolean inDialogue = true;

        switch (eventName)
        {
            case "Intro":
                currentDialogueTree = introEvent;
            default:
        }
        updateDialogue(middleTextBox,buttonContainer);

    }

    private static void traverseDialogue(int subBranch)
    {
        dialogueBranch += 1;
        dialogueSubBranch = subBranch;
    }
    private static void updateDialogue(Label middleTextBox,HBox buttonContainer)
    {
        buttonContainer.getChildren().clear();
        if (currentDialogueTree != null)
        {
            middleTextBox.setText(currentDialogueTree.get("Prompt"+dialogueBranch+"."+dialogueSubBranch));
            for (int i = 0;i <4;i++)
            {
                if (currentDialogueTree.get("Answer"+dialogueBranch+"."+i) != null)
                {
                    Button newButton = createButton(currentDialogueTree.get("Answer"+dialogueBranch+"."+i),"Button2","Times New Roman",100,8000,8000,0,0);
                    int subBranch = i;
                    newButton.setOnAction(e -> {
                        traverseDialogue(subBranch);
                        updateDialogue(middleTextBox,buttonContainer);
                    });

                    buttonContainer.getChildren().add(newButton);
                }
            }
        }
    }

}
