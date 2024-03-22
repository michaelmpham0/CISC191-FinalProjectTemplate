import edu.sdccd.cisc191.template.Enemies.Goblin;
import edu.sdccd.cisc191.template.Enemies.Enemy;
import edu.sdccd.cisc191.template.GameData;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class OOPTest {
    /**
     * Tests to see if instances of Enemy can use Enemy's methods
     */

    @Test
    public void testPolymorphism() {
        //Create new Goblin, which is an instance of Enemy
        Enemy goblin = new Goblin();

        //Tests whether Goblin is an instance of Enemy
        assertTrue(goblin instanceof Enemy);

        //Tests whether Enemy's methods on the Goblin

        assertEquals("Goblin",goblin.getName());
        assertEquals(80,goblin.getHealth());
        assertEquals(50,goblin.getDamage());

        //Create new GameData, which is not an instance of Enemy
        GameData notEnemy = new GameData();

        //GameData cannot use Enemy's methods by default and is not an instance of Enemy, so tests will only result in error
    }
}
