import edu.sdccd.cisc191.template.Inventory;
import edu.sdccd.cisc191.template.Items;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class Module1Test {
    @Test
    public void testAddItem() {
        Inventory inventory = new Inventory("knight");
        inventory.add(new Items("Test Item", "test items", 1));
        assertTrue(inventory.containsItem("Test Item"));
    }

    @Test
    public void testRemoveItem() {
        Inventory inventory = new Inventory("knight");
        Items newItem = new Items("Test Item", "test items", 1);
        inventory.add(newItem);
        inventory.removeItem(newItem);
        assertFalse(inventory.containsItem("Test Item"));
    }
}
