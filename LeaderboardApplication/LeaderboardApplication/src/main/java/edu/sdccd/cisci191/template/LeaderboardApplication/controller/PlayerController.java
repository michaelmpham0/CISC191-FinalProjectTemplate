package edu.sdccd.cisci191.template.LeaderboardApplication.controller;

import edu.sdccd.cisci191.template.LeaderboardApplication.model.H2Player;
import edu.sdccd.cisci191.template.LeaderboardApplication.repo.PlayerRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/players")
public class PlayerController {
    @Autowired
    private PlayerRepo playerRepo;

    @GetMapping("/getAllPlayers")
    public ResponseEntity<List<H2Player>> getAllPlayers(){
        try {
            List<H2Player> h2PlayerList = new ArrayList<>();
            playerRepo.findAll().forEach(h2PlayerList::add);

            if (h2PlayerList.isEmpty()){
                return new ResponseEntity<>(h2PlayerList,HttpStatus.NO_CONTENT);
            }

            return new ResponseEntity<>(HttpStatus.OK);
        } catch (Exception e){
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/getPlayerById/{id}")
    public ResponseEntity<H2Player> getPlayerById(@PathVariable Long id){
        Optional<H2Player> playerData = playerRepo.findById(id);

        if (playerData.isPresent()){
            return new ResponseEntity<>(playerData.get(),HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @PostMapping("/addPlayer")
    public ResponseEntity<H2Player> addPlayer(@RequestBody H2Player h2Player){
        try {
            H2Player h2PlayerObject = playerRepo.save(h2Player);

            return new ResponseEntity<>(h2PlayerObject,HttpStatus.OK);
        }
        catch (Exception e){
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping("/updatePlayerById/{id}")
    public  ResponseEntity<H2Player> updatePlayerById(@PathVariable Long id, @RequestBody H2Player newH2PlayerData){
        Optional<H2Player> oldPlayerData = playerRepo.findById(id);

        if (oldPlayerData.isPresent()){
            H2Player foundH2PlayerData = oldPlayerData.get();
            foundH2PlayerData.setPlayerScore(newH2PlayerData.getPlayerScore());
            foundH2PlayerData.setPlayerClass(newH2PlayerData.getPlayerClass());
            foundH2PlayerData.setPlayerName(newH2PlayerData.getPlayerName());
            foundH2PlayerData.setPlayerLevel(newH2PlayerData.getPlayerLevel());

            H2Player h2PlayerObject = playerRepo.save(foundH2PlayerData);
            return new ResponseEntity<>(h2PlayerObject,HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @DeleteMapping("/deletePlayerById/{id}")
    public  ResponseEntity<HttpStatus> deletePlayerById(@PathVariable Long id){
        playerRepo.deleteById(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
