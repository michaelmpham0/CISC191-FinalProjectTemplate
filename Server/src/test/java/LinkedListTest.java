import edu.sdccd.cisc191.template.Items;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;


public class LinkedListTest
{
    @Test
    public void testItemStacking() {
        Items inventory = new Items();
        Items existingItem = new Items("Test item", "test stack", 1, "test stack", 1);
        Items newItem = new Items("Test item", "test stack", 4, "test stack", 1);

        inventory.add(existingItem);
        inventory.add(newItem);

        assertEquals(5, inventory.next.getStackSize());
    }
}
