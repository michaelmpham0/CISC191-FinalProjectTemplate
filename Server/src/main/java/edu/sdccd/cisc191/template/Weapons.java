package edu.sdccd.cisc191.template;

public class Weapons {
    private final String weaponName;
    private final String weaponDescription;
    private final int weaponDamage;

    public Weapons() {
        weaponName = "unknown";
        weaponDescription = "unknown";
        weaponDamage = 0;
    }
    // Constructor
    public Weapons(String name, String description, int damage) {
        weaponName = name;
        weaponDescription = description;
        weaponDamage = damage;
    }

    // Getters
    public String getWeaponName() {
        return this.weaponName;
    }

    public String getWeaponDescription() {
        return this.weaponDescription;
    }

    public int getWeaponDamage() {
        return this.weaponDamage;
    }
}