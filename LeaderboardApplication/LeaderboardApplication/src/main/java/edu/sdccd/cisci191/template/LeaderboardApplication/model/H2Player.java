package edu.sdccd.cisci191.template.LeaderboardApplication.model;

import jakarta.persistence.*;

@Entity
@Table(name = "Players")
public class H2Player {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long playerID;
    private String playerName;
    private String playerClass;
    private int playerScore;
    private int playerLevel;

    public long getPlayerID() {
        return playerID;
    }

    public void setPlayerID(long playerID) {
        this.playerID = playerID;
    }

    public String getPlayerName() {
        return playerName;
    }

    public H2Player() {
    }

    public H2Player(Long playerID, String playerName, String playerClass, int playerScore, int playerLevel) {
        this.playerID = playerID;
        this.playerName = playerName;
        this.playerClass = playerClass;
        this.playerScore = playerScore;
        this.playerLevel = playerLevel;
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

    public int getPlayerScore() {
        return playerScore;
    }

    public void setPlayerScore(int playerScore) {
        this.playerScore = playerScore;
    }

    public int getPlayerLevel() {
        return playerLevel;
    }

    public void setPlayerLevel(int playerLevel) {
        this.playerLevel = playerLevel;
    }

    @Override
    public String toString() {
        return "Player{" +
                "playerID=" + playerID +
                ", playerName='" + playerName + '\'' +
                ", playerClass='" + playerClass + '\'' +
                ", playerScore=" + playerScore +
                ", playerLevel=" + playerLevel +
                '}';
    }

}
