package edu.sdccd.cisc191.template;
import java.util.ArrayList;
public class Inventory {
    private ArrayList<Items> inventory = new ArrayList<Items>();
    private int size;

    public Inventory() {
        inventory.add(new Items("Sword", "A sharp weapon", 1));
        inventory.add(new Items("Staff", "A magical weapon", 1));
        inventory.add(new Items("Health Potion", "Restores health", 5));
        inventory.add(new Items("Gun", "A firearm", 1));
        size = 8;

    }

    public String printInventory() {
        String inventoryList = "";
        for (int i = 0; i < inventory.size(); i++) {
            int itemNumber = i + 1;
            Items item = inventory.get(i);
            inventoryList += itemNumber + ". " + item.getItemName() + ": " + item.getItemDesc() + "\n";
        }
        return inventoryList;

    }
}