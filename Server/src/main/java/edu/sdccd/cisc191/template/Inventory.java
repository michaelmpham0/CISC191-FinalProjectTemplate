package edu.sdccd.cisc191.template;
import java.lang.reflect.Array;
import java.util.ArrayList;
public class Inventory {
    private final ArrayList<Weapons> weapons = new ArrayList<>();
    private final ArrayList<Items> inventory = new ArrayList<>();
    private int size;

    public Inventory(String userClass) {
        switch (userClass.toLowerCase()) {
            case "knight":
                weapons.add(new Weapons("Rusty Longsword", "A starter sword, found on the grounds of the dungeon.", 12));
                weapons.add(new Weapons("Wooden Shield", "A protective gear, used to lower incoming damage.", 0));
                inventory.add(new Items("Flask of Crimson Tears", "Used to replenish missing health", 3));
                inventory.add(new Items("Flask of Cerulean Tears", "Used to replenish missing mana", 1));
                break;
            case "wizard":
                weapons.add(new Weapons("Weathered Staff", "A starter staff, worn down by time and use.", 15));
                inventory.add(new Items("Spell book", "A magical tome, used to increase number of spells you can hold to 10.", 1));
                inventory.add(new Items("Flask of Crimson Tears", "Used to replenish missing health", 3));
                inventory.add(new Items("Flask of Cerulean Tears", "Used to replenish missing mana", 3));
                break;
            case "barbarian":
                weapons.add(new Weapons("Crude Axe", "An unadorned axe, crudely fashioned from rough materials", 18));
                inventory.add(new Items("Frenzy Potion", "A vial of potent elixir, used to raise overall stats.", 1));
                inventory.add(new Items("Flask of Crimson Tears", "Used to replenish missing health", 2));
                inventory.add(new Items("Flask of Cerulean Tears", "Used to replenish missing mana", 1));
                break;
            case "ranger":
                weapons.add(new Weapons("Rugged Longbow", "A used longbow, a weapon of choice for rangers who roam the untamed wilderness", 10));
                inventory.add(new Items("Wooden Arrows", "The staple ammunition of a ranger", 15));
                weapons.add(new Weapons("Short Dagger", "A dagger designed for swift strikes and close-quarters combat", 8));
                inventory.add(new Items("Flask of Crimson Tears", "Used to replenish missing health", 2));
                inventory.add(new Items("Flask of Cerulean Tears", "Used to replenish missing mana", 2));
                break;
            default:
                System.out.println("Unknown class.");
                break;
        }
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
                case 4: // Bow: Ranger
                    if (userClass.equalsIgnoreCase("Ranger")) {
                        System.out.println("Bow has been applied to Ranger. ATK: +5");
                        validClass = true;
                    } else {
                        System.out.println("Bow can only be applied to Ranger.");
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
    public String printWeapons() {
        String weaponList = "";
        for (int i = 0; i < weapons.size(); i++) {
            int weaponNumber = i + 1;
            Weapons weapon = weapons.get(i);
            weaponList += "[" + weaponNumber + "]" + " - " + weapon.getWeaponName() + ": " + weapon.getWeaponDescription() + "\n";
        }
        return weaponList;
    }

    public String printItems() {
        String itemList = "";
        for (int i = 0; i < inventory.size(); i++) {
            int itemNumber = i + 1;
            Items item = inventory.get(i);
            itemList += "[" + itemNumber + "]" + " - " + item.getItemName() + ": " + item.getItemDesc() + "\n";
        }
        return itemList;
    }
}

