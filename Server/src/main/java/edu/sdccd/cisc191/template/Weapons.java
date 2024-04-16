package edu.sdccd.cisc191.template;

import java.io.Serializable;

public class Weapons extends Items implements Serializable {
    private int weaponDamage;

    public Weapons() {
        itemName = "None";
        itemDesc = "";
        weaponDamage = 0;
    }
    // Constructor
    public Weapons(String name, String description, int damage,int rarity) {
        itemName = name;
        itemDesc = description;
        weaponDamage = damage;
        this.rarity = rarity;
    }



    // Getters
    public int getWeaponDamage() {
        return weaponDamage;
    }
}