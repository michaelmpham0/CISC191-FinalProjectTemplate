package edu.sdccd.cisc191.template;

import java.io.Serializable;

public class Items implements Serializable {
    public String itemName;
    public String itemDesc;
    public String useDesc;
    public int holdSize;
    public Items next;

    public int rarity;

    public Items() {
        this.itemName = "unknown";
        this.itemDesc = "unknown";
        this.holdSize = 1;
        this.useDesc = "...";
        this.rarity = 0;
        this.next = null;
    }
    public Items(String inName, String inDesc, int inStack,String useDesc,int rarity){
        this.itemName = inName;
        this.itemDesc = inDesc;
        this.holdSize = inStack;
        this.useDesc = useDesc;
        this.useDesc = useDesc;
        this.rarity = rarity;
    }


    public void add(Items newItem) {
        if (itemName.equals(newItem.getItemName()))
        {
            //same item, increase stack instead of adding new item
            setStackSize(getStackSize()+newItem.getStackSize());
        }
        else
        {
            if (this.next == null)
            {
                this.next = newItem;
            } else
            {
                if (this.next.itemName.equals(newItem.itemName))
                {
                    //same item, increase stack instead of adding new item
                    this.next.setStackSize(this.next.getStackSize()+newItem.getStackSize());
                }
                else
                {
                    this.next.add(newItem);
                }
            }
        }
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
                Server.getPlayer().restoreHealth(200);
                break;
            case "Flask of Cerulean Tears":
                System.out.println("Use Mana Potion");
                Server.getPlayer().restoreMana(40);
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

