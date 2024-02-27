package edu.sdccd.cisc191.template;

public class Weapons extends Items {
    private int weaponDamage;

    public Weapons() {
        itemName = "None";
        itemDesc = "";
        weaponDamage = 0;
    }
    // Constructor
    public Weapons(String name, String description, int damage) {
        itemName = name;
        itemDesc = description;
        weaponDamage = damage;
    }



    // Getters
    public int getWeaponDamage() {
        return weaponDamage;
    }
}