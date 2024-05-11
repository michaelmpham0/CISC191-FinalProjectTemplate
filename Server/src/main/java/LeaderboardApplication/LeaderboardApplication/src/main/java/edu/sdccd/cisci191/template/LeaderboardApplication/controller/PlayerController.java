package edu.sdccd.cisci191.template.LeaderboardApplication.controller;

import edu.sdccd.cisci191.template.LeaderboardApplication.model.H2Player;
import edu.sdccd.cisci191.template.LeaderboardApplication.service.PlayerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class PlayerController {
    @Autowired
    private PlayerService playerService;

    @PostMapping("/players")
    public H2Player createPlayer(@RequestBody H2Player h2Player) {
        playerService.save(h2Player);
        return h2Player;
    }
}
