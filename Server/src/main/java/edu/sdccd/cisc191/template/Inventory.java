package edu.sdccd.cisc191.template;
import java.lang.reflect.Array;
import java.util.ArrayList;
public class Inventory {
    private final ArrayList<Weapons> weapons = new ArrayList<>();
    private final ArrayList<Items> inventory = new ArrayList<>();

    //private int size; -- was unused, so I just commented it out for now

    public Inventory(String userClass) {
        switch (userClass.toLowerCase()) {
            case "knight":
                weapons.add(new Weapons("Rusty Longsword", "A rusted heirloom sword that has been passed down countless generations.", 12));
                weapons.add(new Weapons("Wooden Shield", "A protective gear, used to lower incoming damage.", 0));
                inventory.add(new Items("Flask of Crimson Tears", "Used to replenish missing health", 3));
                inventory.add(new Items("Flask of Cerulean Tears", "Used to replenish missing mana", 1));
                break;
            case "wizard":
                weapons.add(new Weapons("Weathered Staff", "A tall wooden staff, worn down by time and use.", 15));
                inventory.add(new Items("Spell Book", "A magical tome, used to increase number of spells you can hold to 10.", 1));
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
       //         inventory.add(new Items("Wooden Arrows", "The staple ammunition of a ranger", 15));
                weapons.add(new Weapons("Short Dagger", "A dagger designed for swift strikes and close-quarters combat", 8));
                inventory.add(new Items("Flask of Crimson Tears", "Used to replenish missing health", 2));
                inventory.add(new Items("Flask of Cerulean Tears", "Used to replenish missing mana", 2));
                break;
            default:
                System.out.println("Unknown class.");
                break;
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

