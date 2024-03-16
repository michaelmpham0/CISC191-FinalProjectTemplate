package edu.sdccd.cisc191.template;

import java.io.Serializable;


public class Items implements Serializable {
    public String itemName;
    public String itemDesc;
    public int holdSize;
    public Items() {
        itemName = "unknown";
        holdSize = 1;
    }
    public Items(String inName, String inDesc, int inStack){
        itemName = inName;
        itemDesc = inDesc;
        holdSize = inStack;
    }

    public String getItemName() {
        return itemName;
    }
    public String getItemDesc(){
        return itemDesc;
    }

    public int getStackSize(){
        return holdSize;
    }

    public void setItemName(String inName){
        itemName = inName;
    }
    public void setItemDesc(String inDesc){
        itemDesc = inDesc;
    }
    public void setStackSize(int size){
        holdSize = size;
    }
    public void useItem()
    {
        System.out.println("Tried To Use: "+itemName);
        switch (itemName)
        {
            case "Flask of Crimson Tears":
                System.out.println("Use Health Potion");

                break;
            default:
                System.out.println("Item Not Usable");
        }
    }
    @Override
    public String toString() {
        return itemName;
    }
}

