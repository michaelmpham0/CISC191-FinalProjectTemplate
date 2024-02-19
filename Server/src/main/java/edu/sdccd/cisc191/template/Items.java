package edu.sdccd.cisc191.template;

public class Items {
    private String itemName;
    private String itemDesc;
    private int stackSize;
    public Items(){
        itemName = "unknown";
        stackSize = 1;
    }
    public Items(String inName, String inDesc, int inStack){
        itemName = inName;
        itemDesc = inDesc;
        stackSize = inStack;
    }

    public String getItemName() {
        return itemName;
    }
    public String getItemDesc(){
        return itemDesc;
    }

    public int getStackSize(){
        return stackSize;
    }

    public void setItemName(String inName){
        itemName = inName;
    }
    public void setItemDesc(String inDesc){
        itemDesc = inDesc;
    }
    public void setStackSize(int size){
        stackSize = size;
    }

}

