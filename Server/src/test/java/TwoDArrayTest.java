import edu.sdccd.cisc191.template.Enemies.EnemyHandler;
import org.junit.jupiter.api.Test;

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
