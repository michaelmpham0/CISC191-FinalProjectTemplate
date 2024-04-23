package edu.sdccd.cisc191.template;

import edu.sdccd.cisc191.template.Effects.StatusEffect;
import edu.sdccd.cisc191.template.GUI.DeathMenu;
import edu.sdccd.cisc191.template.GUI.GUIController;
import edu.sdccd.cisc191.template.LeaderboardSystem.Database;
import javafx.animation.PauseTransition;
import javafx.util.Duration;

import java.io.Serializable;
import java.util.LinkedList;
import  java.util.Scanner;
import java.util.HashMap;

/**
 * Stats used for the player:
 * HP = Health
 * ATK = Attack
 * MANA = Mana
 * GOLD = Gold
 */
public class Player extends Entity implements Serializable {

   //HashMap to store class info. Stats correspond top from bottom of the above list, left to right.
    static HashMap<String, int[]> classStats = new HashMap<String, int[]>()
    {{
        put("Knight", new int[]{40,10,30,30});
        put("Wizard", new int[]{18,5,100,50});
        put("Barbarian", new int[]{35,18,10,0});
        put("Ranger", new int[]{25,12,20,5});
    }};

    //increase stats by this amount every level up
    static HashMap<String, int[]> classStatsGrowth = new HashMap<String, int[]>()
    {{
        // health, attack, mana
        put("Knight", new int[]{6,3,10});
        put("Wizard", new int[]{2,1,20});
        put("Barbarian", new int[]{4,8,0});
        put("Ranger", new int[]{3,5,5});
    }};
    private final LinkedList<StatusEffect> statusEffectsLinkedList = new LinkedList<>();
    public LinkedList<StatusEffect> getStatusList(){
        return statusEffectsLinkedList;
    }
    public int numberOfStatusesInList(){return statusEffectsLinkedList.size();}
    private int HP,maxHP,ATK,GOLD,MANA,maxMana,level;
    private int playerScore = 0;
    // maybe get score equal to enemy gold drop?
    // possible score equation:
    // score * ((1)+(playerLevel*0.1))
    //ex: if level is 5, score is multiplied by 1.5
    private int exp = 0;
    private int maxExp = 100;
    private double defenseMultiplier = 1.0;
    private Weapons currentWeapon = new Weapons();
    private Tools currentTool = new Tools();

    private String status = "None";

    public void setCurrentWeapon(Weapons currentWeapon) {
        this.currentWeapon = currentWeapon;
    }
    public void equipWeaponOrTool(Inventory playerInventory,Items newItem)
    {

        if (newItem instanceof Weapons)
        {
            playerInventory.unequip(currentWeapon);
            currentWeapon = (Weapons) newItem;
        }
        else
        {
            playerInventory.unequip(currentTool);
            currentTool = (Tools) newItem;
        }
        playerInventory.equip(newItem);
    }


    public Weapons getCurrentWeapon() {

        return currentWeapon;
    }
    public void setCurrentTool(Tools currentTool) {
        this.currentTool = currentTool;
    }
    public Tools getCurrentTool() {

        return currentTool;
    }

    private String Name,Class;

    /**
     * Creates player, default constructor
     */
    public Player(){
        level = 1;
        Name = Class = "Unknown";
    }

    String classList[] = {"Knight","Wizard","Barbarian","Ranger"};
    public String[] getClassList()
    {
        return  classList;
    }

    /**
     * Simple constructor
     */
    public Player(String Name, String Class){
        level =  1;
        this.Name = Name;
        this.Class = Class;
    }

    public String getName(){
        return Name;
    }

    public int getLevel()
    {
        return level;
    }

    public String getPlayerClass(){
        return Class;
    }

    public int getGold(){
        return  GOLD;
    }
    public void setGold(int newGold)
    {
        GOLD = newGold;
    }
    public int getMana(){
        return  MANA;
    }
    public int getAttack(){
        return  ATK;
    }
    public int getHealth(){return  HP;}
    public int getMaxHealth(){
        return  maxHP;
    }

    public int getMaxMana(){
        return  maxMana;
    }

    public double getDefenseMultiplier() {
        return defenseMultiplier;
    }
    private boolean isGuarding = false;
    public boolean getGuarding()
    {
        return isGuarding;
    }
    public void setGuarding(boolean newGuard)
    {
        isGuarding = newGuard;
    }
    public void setDefenseMultiplier(double playerDefenseMultiplier) {
        this.defenseMultiplier = playerDefenseMultiplier;
    }

    public void setMana(int newMana){
        MANA = newMana;
        if(MANA < 0){
            MANA = 0;
        }

    }

    public void takeDamage(int damage){
        HP-=damage;
        if(HP < 0){
            HP = 0;
            PauseTransition delay = new PauseTransition(Duration.seconds(2));
            delay.setOnFinished(event -> {

                Player deadPlayer = new Player();
                Database.insertPlayerDetails(deadPlayer);

                DeathMenu.deathMenu();
            });
            delay.play();
        }
    }

    public void setHealth(int newHealth){
       HP = newHealth;
    }

    public void restoreHealth(int healAmount)
    {
        HP += healAmount;
        if (HP > maxHP)
        {
            HP = maxHP;
        }
    }

    public void restoreMana(int healAmount) {
        MANA += healAmount;
        if (MANA > maxMana)
        {
            MANA = maxMana;
        }
    }

    public void setClass(String setClass)
    {
        Class = setClass;
        maxHP = HP = classStats.get(Class)[0];
        ATK = classStats.get(Class)[1];
        MANA = maxMana = classStats.get(Class)[2];
        GOLD = classStats.get(Class)[3];
    }
    public void setName(String newName)
    {
        Name = newName;
    }

    public int getExperience() {
        return exp;
    }


    //method to update and increase stats each level up
    private void updateStats()
    {
        maxHP = HP = classStats.get(Class)[0]+(classStatsGrowth.get(Class)[0]*(level-1));
        ATK = classStats.get(Class)[1]+(classStatsGrowth.get(Class)[1]*(level-1));
        MANA = maxMana = classStats.get(Class)[2]+(classStatsGrowth.get(Class)[2]*(level-1));
        for (Abilities abs : Spells.getSpells()){
            abs.scaleAbility(level);
        }
    }

    public void setExperience(int exp) {
        this.exp = exp;
    }
    public void gainExperience(int gainXP)
    {
        exp += gainXP;
        if (exp >= maxExp)
        {
            // Level Up
            level += 1;
            updateStats();
            GUIController.updateHealthAndMana();
            int extraXP = exp-maxExp;
            exp = extraXP;
            maxExp = 100*level;
        }
    }
    public void gainScore(int increaseScore)
    {
        System.out.println("INCREASE SCORE BY :" + increaseScore);
        playerScore += increaseScore;
    }
    public int getScore()
    {
        return playerScore;
    }


    public int getMaxExperience() {
        return maxExp;
    }

    public void setUp(){
        TextDisplay displayText = new TextDisplay();
        PromptDisplay displayPrompt = new PromptDisplay();
        PromptController controlPrompt = new PromptController();

        Scanner keyboard = new Scanner(System.in);

        System.out.print("Enter your name: ");
        Name = keyboard.next();

        System.out.println("----------------");

        displayText.display("ClassInstructions");
        displayPrompt.display("Classes");

        int promptChoice = controlPrompt.answerPrompt(4,false);

        Class = classList[promptChoice-1];

        maxHP = HP = classStats.get(Class)[0];
        ATK = classStats.get(Class)[1];
        MANA = classStats.get(Class)[2];
        GOLD = classStats.get(Class)[3];
    }

    private boolean foughtProwler = false;
    public boolean getFoughtProwler()
    {
        return foughtProwler;
    }

    public void setFoughtProwler(boolean b)
    {
        foughtProwler = b;
    }
}
