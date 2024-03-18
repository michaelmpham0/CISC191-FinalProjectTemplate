import edu.sdccd.cisc191.template.GUI.GUIController;
import edu.sdccd.cisc191.template.GameData;
import org.junit.jupiter.api.Test;

import java.io.*;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class GameDataTest {

    /**
     * Tests to see if there is a valid file in your save directory and is able to run it.
     * If there is no file in the save directory, throws an error message "no file found."
     */
    @Test
    public void validSaveFileTest() {
        // Arrange
        FileInputStream saveFile;
        String savePath = System.getProperty("user.home") + "/Documents/ArchitectSaveFile.ser";
        try {
            saveFile = new FileInputStream(savePath);
            try {
                ObjectInputStream objectInputStream = new ObjectInputStream(saveFile);
                GameData saveData = (GameData) objectInputStream.readObject();
                // Act
                GameData actual = GUIController.loadGame();
                // Assert
                assertEquals(saveData.getClass(), actual.getClass());
            } catch (InvalidClassException ex) {
                // Assert
                GameData expected = new GameData();
                GameData actual = GUIController.loadGame();
                assertEquals(expected.getClass(), actual.getClass());
            } catch (IOException ex) {
                ex.printStackTrace();
            } catch (ClassNotFoundException ex) {
                ex.printStackTrace();
            }
        } catch (FileNotFoundException ex) {
            System.err.println("No file found");
        }
    }
}
