package edu.sdccd.cisc191.template.LeaderboardNetworking;

import java.util.ArrayList;
import java.util.List;

public class Leaderboard {
    private List<LeaderboardEntry> entries = new ArrayList<>();

    public void addEntry(LeaderboardEntry entry) {
        entries.add(entry);
        entries.sort((e1, e2) -> Integer.compare(e2.getLevel(), e1.getLevel())); // Sort in descending order of level
    }

    public List<LeaderboardEntry> getEntries() {
        return entries;
    }
}
