package edu.sdccd.cisc191.template;

public class Items {
    private String itemName;
    private String itemDesc;
    private int holdSize;
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

    public int getHoldSize(){
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

}

