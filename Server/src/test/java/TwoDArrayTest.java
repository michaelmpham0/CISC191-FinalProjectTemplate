import edu.sdccd.cisc191.template.Enemies.Goblin;
import edu.sdccd.cisc191.template.Enemies.Grogoroth;
import edu.sdccd.cisc191.template.Enemies.Minotaur;
import edu.sdccd.cisc191.template.Enemy;
import edu.sdccd.cisc191.template.EnemyHandler;
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

public class TwoDArrayTest {
    /**
     * Tests to if the 2D Arrays are checked correctly.
     */

    @Test
    public void test2DArray() {
        //Goblins are present for row 0 and row 1
        assertTrue(EnemyHandler.checkValidEnemy("Goblin",0));
        assertTrue(EnemyHandler.checkValidEnemy("Goblin",1));

        //Goblins are not present for row 2 and below
        assertFalse(EnemyHandler.checkValidEnemy("Goblin",2));

        //Likewise, Grogorth doesn't show up until row 2
        assertFalse(EnemyHandler.checkValidEnemy("Grogoroth",0));
        assertFalse(EnemyHandler.checkValidEnemy("Grogoroth",1));

        //nonEnemy is not a listed enemy in the 2d array
        assertFalse(EnemyHandler.checkValidEnemy("nonEnemy",2));
    }
}
