import edu.sdccd.cisc191.template.Inventory;
import edu.sdccd.cisc191.template.Items;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class SortingTest {

    @Test
    public void testMergeSort() {

        Items newInventory = new Items("Item 1", "rarity 3", 1, "rarity 3", 3);
        newInventory.next = new Items("Item 2", "rarity 1", 1, "rarity 1", 1);
        newInventory.next.next = new Items("Item 3", "rarity 2", 1, "rarity 2", 2);

        Items sortedInventory = Inventory.mergeSort(newInventory);

        Items current = sortedInventory;
        while (current != null && current.next != null) {
            assertTrue(current.rarity >= current.next.rarity);
            current = current.next;
        }
    }
}