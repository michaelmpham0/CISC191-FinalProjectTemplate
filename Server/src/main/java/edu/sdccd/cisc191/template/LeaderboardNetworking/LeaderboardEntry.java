package edu.sdccd.cisc191.template.LeaderboardNetworking;

import java.io.Serializable;

public class LeaderboardEntry implements Serializable {
    private String name;
    private int level;

    public LeaderboardEntry(String playerName, int level) {
        this.name = playerName;
        this.level = level;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getLevel() {
        return level;
    }

    public void setLevel(int level) {
        this.level = level;
    }
}
