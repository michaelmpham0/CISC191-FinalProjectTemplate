package edu.sdccd.cisci191.template.LeaderboardApplication.service;

import edu.sdccd.cisci191.template.LeaderboardApplication.model.H2Player;
import edu.sdccd.cisci191.template.LeaderboardApplication.repo.PlayerRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PlayerService {

    @Autowired
    private PlayerRepo playerRepo;

    public void save(H2Player h2Player) {
        playerRepo.save(h2Player);
    }
}
