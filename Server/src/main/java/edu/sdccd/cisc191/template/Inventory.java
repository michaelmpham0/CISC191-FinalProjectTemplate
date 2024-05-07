package edu.sdccd.cisc191.template;
import java.io.Serializable;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.LinkedList;

public class Inventory implements Serializable {
    //private final LinkedList<Items> inventory = new LinkedList<>();

    Items inventory = new Items("Flask of Crimson Tears", "Used to replenish missing health",
            3,"You gulp down the bottle of thick liquid and feel immediately rejuvenated.",100);

    //private int size; -- was unused, so I just commented it out for now

    public Inventory(String userClass) {
        switch (userClass.toLowerCase()) {
            case "knight":
                inventory.add(new Weapons("Rusty Longsword", "A rusted heirloom sword that has been passed down countless generations.", 10,50));
                inventory.add(new Tools("Wooden Shield", "A protective gear, used to lower incoming damage."));
                inventory.add(new Items("Flask of Cerulean Tears", "Used to replenish missing mana", 2,"Power flows through your fingertips as your magical reserves are topped off. You regain 30 MP.",99));
                break;
            case "wizard":
                inventory.add(new Weapons("Weathered Staff", "A tall wooden staff, worn down by time and use.", 3,50));
                inventory.add(new Tools("Spell Book", "A magical tome, used to increase number of spells you can hold to 10."));
                inventory.add(new Items("Flask of Cerulean Tears", "Used to replenish missing mana", 2,"Power flows through your fingertips as your magical reserves are topped off. You regain 30 MP.",99));
                break;
            case "barbarian":
                inventory.add(new Weapons("Crude Axe", "An unadorned axe, crudely fashioned from rough materials", 12,50));
                inventory.add(new Items("Frenzy Potion", "A vial of potent elixir, used to raise overall stats.", 1,"Placeholder",5));
                inventory.add(new Items("Flask of Cerulean Tears", "Used to replenish missing mana", 2,"Power flows through your fingertips as your magical reserves are topped off. You regain 30 MP.",99));
                break;
            case "ranger":
                inventory.add(new Tools("Rugged Longbow", "A used longbow, a weapon of choice for rangers who roam the untamed wilderness"));
                inventory.add(new Items("Wooden Arrows", "The staple ammunition of a ranger", 15,"Placeholder",0));
                inventory.add(new Weapons("Short Dagger", "A dagger designed for swift strikes and close-quarters combat", 8,50));
                inventory.add(new Items("Flask of Cerulean Tears", "Used to replenish missing mana", 2,"Power flows through your fingertips as your magical reserves are topped off. You regain 30 MP.",99));
                break;
            default:
                System.out.println("Unknown class.");
                break;
        }
        inventory.add(new Weapons("test weapon", "meant to test switching weapons", 12,49));
    }

    public static Items mergeSort(Items head) {
        if (head == null || head.next == null) {
            return head;
        }

        Items middle = getMiddle(head);
        Items nextOfMiddle = middle.next;
        middle.next = null;

        Items left = mergeSort(head);
        Items right = mergeSort(nextOfMiddle);

        return merge(left, right);
    }
    private static Items merge(Items left, Items right) {
        Items result;

        if (left == null)
            return right;
        if (right == null)
            return left;

        if (left.rarity >= right.rarity) {
            result = left;
            result.next = merge(left.next, right);
        } else {
            result = right;
            result.next = merge(left, right.next);
        }
        return result;
    }

    private static Items getMiddle(Items head) {
        // fast pointer reaches the end of the linked list first, and when it does, slow pointer will be stopped at the middle
        Items slow = head;
        Items fast = head;
        while (fast != null && fast.next != null && fast.next.next != null) {
            slow = slow.next;
            fast = fast.next.next;
        }
        return slow;
    }

    public Items getInventoryHead() {
        return inventory;
    }
    public void addItem(Items newItem)
    {
        inventory.add(newItem);
    }

    public void remove(Items itemToRemove) {
        if (inventory == null) {
            return;
        }
        if (inventory == itemToRemove) {
            inventory = itemToRemove.next;
            itemToRemove.next = null;
            return;
        }

        Items foundItem = findItem(inventory, itemToRemove);

        if (foundItem != null) {
            foundItem.next = itemToRemove.next;
            itemToRemove.next = null;
        }
    }
    private Items findItem(Items head, Items target) {
        Items current = head;
        while (current != null && current.next != target) {
            current = current.next;
        }
        return current;
    }


    public void unequip(Items unequipItem)
    {
        if (unequipItem != null)
        {
            if (unequipItem.getItemName().equals("None") == false)
            {
                inventory.add(unequipItem);
            }
        }
    }
    public void equip(Items equipItem)
    {
        remove(equipItem);
    }

    public void useOneItem(Items itemToRemove)
    {
        itemToRemove.setStackSize(itemToRemove.getStackSize()-1);
        if (itemToRemove.getStackSize()<1)
        {
            remove(itemToRemove);
        }
    }

    //public LinkedList<Items> getInventory() {
    //return inventory;
    // }

    public String printItems() {
        String itemList = "";
        //for (int i = 0; i < inventory.size(); i++) {
        //int itemNumber = i + 1;
        //Items item = inventory.get(i);
        //itemList += "[" + itemNumber + "]" + " - " + item.getItemName() + " x"+item.getStackSize()+": " + item.getItemDesc() + "\n";
        //}
        return itemList;
    }
}

