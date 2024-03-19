import edu.sdccd.cisc191.template.GUI.CharacterCreationMenu;
import edu.sdccd.cisc191.template.GUI.GUIController;
import edu.sdccd.cisc191.template.GameData;
import edu.sdccd.cisc191.template.Player;
import javafx.application.Application;
import javafx.embed.swing.JFXPanel;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.StackPane;
import jdk.jfr.internal.tool.Main;
import org.junit.jupiter.api.Test;
import java.io.*;

import static org.junit.jupiter.api.Assertions.*;

public class JavaFXTest {
    /**
     * Tests to see if JavaFX button changes
     */
        @Test
        public void ButtonTest() {
            new JFXPanel();
            // Create a button and make it functional
            Button button = new Button("Click Me!");
            button.setOnAction(e -> {
                button.setText("textChanged");
            });
            button.fire();

            // Create a layout to hold the button
            StackPane root = new StackPane();
            root.getChildren().add(button);

            // Create a scene with the layout
            Scene scene = new Scene(root, 300, 200);
            assertEquals("textChanged", button.getText());
        }
}

