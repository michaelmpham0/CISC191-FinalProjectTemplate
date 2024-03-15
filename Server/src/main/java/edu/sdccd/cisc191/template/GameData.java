package edu.sdccd.cisc191.template;
import java.io.Serializable;


// GameData class to hold a Player, and Inventory object to be saved with OutputStream
public class GameData implements Serializable
{
    boolean wrongVersion = false;
    Inventory inventoryData;
    Player playerData;
    Spells spellsData;

    public GameData()
    {
        wrongVersion = true;
        playerData = new Player();
        inventoryData = new Inventory(playerData.getPlayerClass());
    }
    public GameData(Player savePlayer,Inventory saveInventory,Spells saveSpells)
    {
        playerData = savePlayer;
        inventoryData = saveInventory;
        spellsData = saveSpells;
    }
    public Player getPlayerData()
    {
        return  playerData;
    }
    public Inventory getInventoryData()
    {
        return  inventoryData;
    }

    public Spells getSpellsData()
    {
        return  spellsData;
    }
    public boolean isWrongVersion()
    {
        return  wrongVersion;
    }
}
