import edu.sdccd.cisc191.template.GUI.CharacterCreationMenu;
import edu.sdccd.cisc191.template.GUI.GUIController;
import edu.sdccd.cisc191.template.GUI.GUIMain;
import edu.sdccd.cisc191.template.GameData;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import jdk.jfr.internal.tool.Main;
import org.junit.jupiter.api.Test;

import java.io.*;

import static org.junit.jupiter.api.Assertions.*;

public class JavaFXTest {
    /**
     * Tests to see if JavaFX button changes
     */

    @Test
    public void testButtonTextChange() {
        Label label = new Label("Click Me");
        AnchorPane root = new AnchorPane();
        root.getChildren().add(label);


        assertEquals("Click Me", label.getText());
    }
}
