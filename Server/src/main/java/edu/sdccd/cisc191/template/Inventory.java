package edu.sdccd.cisc191.template;
import java.io.Serializable;
import java.lang.reflect.Array;
import java.util.ArrayList;
public class Inventory implements Serializable {
    private final ArrayList<Items> inventory = new ArrayList<>();

    //private int size; -- was unused, so I just commented it out for now

    public Inventory(String userClass) {
        switch (userClass.toLowerCase()) {
            case "knight":
                inventory.add(new Weapons("Rusty Longsword", "A rusted heirloom sword that has been passed down countless generations.", 12));
                inventory.add(new Tools("Wooden Shield", "A protective gear, used to lower incoming damage."));
                inventory.add(new Items("Flask of Crimson Tears", "Used to replenish missing health", 3));
                inventory.add(new Items("Flask of Cerulean Tears", "Used to replenish missing mana", 1));
                break;
            case "wizard":
                inventory.add(new Weapons("Weathered Staff", "A tall wooden staff, worn down by time and use.", 15));
                inventory.add(new Tools("Spell Book", "A magical tome, used to increase number of spells you can hold to 10."));
                inventory.add(new Items("Flask of Crimson Tears", "Used to replenish missing health", 3));
                inventory.add(new Items("Flask of Cerulean Tears", "Used to replenish missing mana", 3));
                break;
            case "barbarian":
                inventory.add(new Weapons("Crude Axe", "An unadorned axe, crudely fashioned from rough materials", 18));
                inventory.add(new Items("Frenzy Potion", "A vial of potent elixir, used to raise overall stats.", 1));
                inventory.add(new Items("Flask of Crimson Tears", "Used to replenish missing health", 2));
                inventory.add(new Items("Flask of Cerulean Tears", "Used to replenish missing mana", 1));
                break;
            case "ranger":
                inventory.add(new Weapons("Rugged Longbow", "A used longbow, a weapon of choice for rangers who roam the untamed wilderness", 10));
                inventory.add(new Items("Wooden Arrows", "The staple ammunition of a ranger", 15));
                inventory.add(new Weapons("Short Dagger", "A dagger designed for swift strikes and close-quarters combat", 8));
                inventory.add(new Items("Flask of Crimson Tears", "Used to replenish missing health", 2));
                inventory.add(new Items("Flask of Cerulean Tears", "Used to replenish missing mana", 2));
                break;
            default:
                System.out.println("Unknown class.");
                break;
        }
        inventory.add(new Weapons("test weapon", "meant to test switching weapons", 12));
    }

    /*
    public String printWeapons() {
        String weaponList = "";

        for (int i = 1;i<= 2;i++)
        {
            String weaponType;
            if (i == 1)
            {
                weaponType = "Weapon";
            }
            else
            {
                weaponType = "Tool";
            }
            try
            {
                Weapons weapon = weapons.get(i);
                weaponList += "[" + weaponType + "]" + " - " + weapon.getItemName() + ": " + weapon.getItemDesc() + "\n";
            }
            catch (IndexOutOfBoundsException e)
            {
                weaponList += "[" + weaponType + "]" + " - " + "None\n";
            }

        }

        /*
        for (int i = 0; i < weapons.size(); i++) {
            int weaponNumber = i + 1;
            Weapons weapon = weapons.get(i);
            weaponList += "[" + weaponNumber + "]" + " - " + weapon.getItemName() + ": " + weapon.getItemDesc() + "\n";
        }
        return weaponList;
    }
     */

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
        inventory.remove(equipItem);
    }

    public void useOneItem(Items itemToRemove)
    {
        itemToRemove.setStackSize(itemToRemove.getStackSize()-1);
        if (itemToRemove.getStackSize()<1)
        {
            inventory.remove(itemToRemove);
        }
    }

    public void add (Items newItem)
    {
        inventory.add(newItem);
    }
    public void removeItem (Items newItem)
    {
        inventory.remove(newItem);
    }
    public boolean containsItem(String itemId) {
        for (Items item : inventory) {
            if (item.getItemName().equals(itemId)) {
                return true;
            }
        }
        return false;
    }

    public ArrayList<Items> getInventory() {
        return inventory;
    }

    public String printItems() {
        String itemList = "";
        for (int i = 0; i < inventory.size(); i++) {
            int itemNumber = i + 1;
            Items item = inventory.get(i);
            itemList += "[" + itemNumber + "]" + " - " + item.getItemName() + " x"+item.getStackSize()+": " + item.getItemDesc() + "\n";
        }
        return itemList;
    }
}

