package edu.sdccd.cisc191.template.GUI;


import edu.sdccd.cisc191.template.GameData;
import edu.sdccd.cisc191.template.Items;
import edu.sdccd.cisc191.template.Player;
import javafx.animation.Timeline;
import javafx.animation.KeyFrame;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ProgressBar;
import javafx.scene.layout.VBox;
import javafx.util.Duration;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;
import java.io.*;


import java.awt.*;


public class GUIController extends GUIMain {

    protected  Scene scene;
    public static Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
    public static double screenWidth = screenSize.getWidth()*0.8;
    public static double screenHeight = screenSize.getHeight()*0.8;
    private static Timeline turnDelay;
    protected static boolean pauseGame = false;
    protected static Items usedItem = null;
    protected static boolean inMenu = false;
    //variable to track when item is used, so to use your turn during combat

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

    static  protected ProgressBar createHealthBar()
    {
        ProgressBar healthBar = new ProgressBar();
        healthBar.getStylesheets().add("styleSheet.css");
        healthBar.getStyleClass().add("healthBar");
        healthBar.setPrefSize(screenWidth*0.2,screenHeight*0.025);
        healthBar.setMinSize(screenWidth*0.2,screenHeight*0.025);
        healthBar.setMaxSize(screenWidth*0.2,screenHeight*0.025);
        healthBar.setProgress((double) player.getHealth() /player.getMaxHealth());
        return  healthBar;
    }
    static  protected ProgressBar createManaBar()
    {
        ProgressBar manaBar = new ProgressBar();
        manaBar.getStylesheets().add("styleSheet.css");
        manaBar.getStyleClass().add("manaBar");
        manaBar.setPrefSize(screenWidth*0.2,screenHeight*0.025);
        manaBar.setMinSize(screenWidth*0.2,screenHeight*0.025);
        manaBar.setMaxSize(screenWidth*0.2,screenHeight*0.025);
        manaBar.setTranslateY(screenHeight*0.035);
        manaBar.setProgress((double) player.getMana() /player.getMaxMana());
        return  manaBar;
    }

    public static void updateHealthAndMana()
    {
        if (currentHealthBar != null)
        {
            currentHealthBar.setProgress((double) player.getHealth() /player.getMaxHealth());
        }
        if (currentManaBar != null)
        {
            currentManaBar.setProgress((double) player.getMana() /player.getMaxMana());
        }
        if (currentHealthBarText != null)
        {
            currentHealthBarText.setText(player.getHealth()+"/"+player.getMaxHealth());
        }
        if (currentManaBarText != null)
        {
            currentManaBarText.setText(player.getMana()+"/"+player.getMaxMana());
        }
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
       String savePath = "C:/Users/"+System.getProperty("user.name")+"/Documents/ArchitectSaveFile.ser";
       try {
           saveFile = new FileInputStream(savePath);
           try
           {
               ObjectInputStream objectInputStream = new ObjectInputStream(saveFile);
               GameData saveData = (GameData) objectInputStream.readObject();
               return saveData;
           } catch (InvalidClassException ex) {
               System.out.println("SAVE INCOMPATIBLE VERSION");
               GameData saveData = new GameData();
               return saveData;
               //throw new RuntimeException(ex);
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
        inMenu = false;
        if (previousStage.equals("Combat"))
        {
            stage.setScene(lastScene);
        }
        else
        {
            ExploreMenu.exploreMenu();
        }
        /*
       switch (previousStage) {
       case "Explore":

           break;
           case "Combat":
               CombatMenu.combatMenu();
               break;
       }
        */
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

        String filePath = "C:/Users/"+System.getProperty("user.name")+"/Documents/ArchitectSaveFile.ser";
        File file = new File(filePath);
        if (file.exists())
        {
            hasSave = true;
        }

        if (hasSave == true)
        {
            GameData saveData = loadGame();
            if (saveData.isWrongVersion() == false)
            {
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
            else
            {
                System.out.println("Save data wrong version.");
            }


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
