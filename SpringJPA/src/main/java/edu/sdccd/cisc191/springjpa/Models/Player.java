package edu.sdccd.cisc191.springjpa.Models;

import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collector;
import java.util.stream.Collectors;

@Entity
public class Player {
    @Id
    private String playerName;
    private String playerClass;
    private int playerLevel;
    private int playerScore;

    @OneToMany(fetch = FetchType.EAGER)
    private List<Option> options;

    public Player(String playerName, String playerClass, int playerLevel, int playerScore, List<Option> options) {
        this.playerName = playerName;
        this.playerClass = playerClass;
        this.playerLevel = playerLevel;
        this.playerScore = playerScore;
        this.options = options;
    }
    public Player(){}

    public String getPlayerName() {
        return playerName;
    }

    public void setPlayerName(String playerName) {
        this.playerName = playerName;
    }

    public String getPlayerClass() {
        return playerClass;
    }

    public void setPlayerClass(String playerClass) {
        this.playerClass = playerClass;
    }

    public int getPlayerLevel() {
        return playerLevel;
    }

    public void setPlayerLevel(int playerLevel) {
        this.playerLevel = playerLevel;
    }

    public int getPlayerScore() {
        return playerScore;
    }

    public void setPlayerScore(int playerScore) {
        this.playerScore = playerScore;
    }

    public String getOptionsAsString(){
        return options.stream().map(option -> option.getDetails()).collect(Collectors.joining(","));
    }

    public List<Option> getOptions() {
        return options;
    }

    public void setOptions(List<Option> options) {
        this.options = options;
    }

    @Override
    public String toString() {
        return "Player{" +
                "playerName='" + playerName + '\'' +
                ", playerClass='" + playerClass + '\'' +
                ", playerLevel=" + playerLevel +
                ", playerScore=" + playerScore +
                ", options=" + options +
                '}';
    }
}
