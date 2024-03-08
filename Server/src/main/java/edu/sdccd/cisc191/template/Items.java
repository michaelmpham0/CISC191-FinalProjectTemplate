package edu.sdccd.cisc191.template;

import java.io.Serializable;

import edu.sdccd.cisc191.template.GUI.GUIMain;

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
        this.useDesc = "...";
    }

    public String useDesc;
    public Items(String inName, String inDesc, int inStack,String useDesc){
        itemName = inName;
        itemDesc = inDesc;
        holdSize = inStack;
        this.useDesc = useDesc;
    }


    public String getItemName() {
        return itemName;
    }
    public String getItemDesc(){
        return itemDesc;
    }
    public String getUseDesc(){
        return useDesc;
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
                Player playerRefrence = GUIMain.getPlayer();
                playerRefrence.restoreHealth(20);
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

