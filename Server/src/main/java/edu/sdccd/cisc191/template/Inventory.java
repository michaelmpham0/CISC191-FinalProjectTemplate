package edu.sdccd.cisc191.template;
import java.util.ArrayList;
public class Inventory {
    private ArrayList<Items> inventory = new ArrayList<Items>();
    private int size;

    public Inventory() {
        inventory.add(new Items("Sword", "A sharp weapon, can only be used with Knight, ATK+", 1));
        inventory.add(new Items("Staff", "A magical weapon, can only be used with Wizard, MANA+", 1));
        inventory.add(new Items("Health Potion", "A yummy potion, can be used with any class, HP+", 3));
        inventory.add(new Items("Gun", "A dangerous firearm, can only be used with Hunter, ATK+", 1));
        inventory.add(new Items("Axe", "A heavy weapon, can only be used with Barbarian, ATK+", 1));
        size = 7;

    }

    public void useItem(int itemNumber, String userClass) {
        boolean validClass = false;

        while (!validClass) {
            switch (itemNumber) {
                case 1: // Sword: Knight
                    if (userClass.equalsIgnoreCase("Knight")) {
                        System.out.println("Sword has been applied to Knight. ATK: +5");
                        validClass = true;
                    } else {
                        System.out.println("Sword can only be applied to Knight.");
                    }
                    break;
                case 2: // Staff: Wizard
                    if (userClass.equalsIgnoreCase("Wizard")) {
                        System.out.println("Staff has been applied to Wizard. MANA: +5");
                        validClass = true;
                    } else {
                        System.out.println("Staff can only be applied to Wizard.");
                    }
                    break;
                case 3: // Health Potion: Any
                    System.out.println("Health Potion has been applied to " + userClass + ". HP: +10");
                    validClass = true;
                    break;
                case 4: // Gun: Hunter
                    if (userClass.equalsIgnoreCase("Hunter")) {
                        System.out.println("Gun has been applied to Hunter. ATK: +5");
                        validClass = true;
                    } else {
                        System.out.println("Gun can only be applied to Hunter.");
                    }
                    break;
                case 5: // Axe: Barbarian
                    if (userClass.equalsIgnoreCase("Barbarian")) {
                        System.out.println("Axe has been applied to Barbarian. ATK: +5");
                        validClass = true;
                    } else {
                        System.out.println("Axe can only be applied to Barbarian. ");
                    }
                    break;
                default:
                    System.out.println("Invalid item number. Please enter a valid item number.");
                    break;
            }

        }
    }
    public String printInventory() {
        String inventoryList = "";
        for (int i = 0; i < inventory.size(); i++) {
            int itemNumber = i + 1;
            Items item = inventory.get(i);
            inventoryList += "[" + itemNumber + "]" + " - " + item.getItemName() + ": " + item.getItemDesc() + "\n";
        }
        return inventoryList;
    }
}

