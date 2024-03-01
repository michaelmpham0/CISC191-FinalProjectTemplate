package edu.sdccd.cisc191.template;

import java.io.Serializable;

public class Tools extends Items implements Serializable {

    public Tools() {
        itemName = "None";
        itemDesc = "";
    }
    // Constructor
    public Tools(String name, String description) {
        itemName = name;
        itemDesc = description;
    }
    // Getters


}