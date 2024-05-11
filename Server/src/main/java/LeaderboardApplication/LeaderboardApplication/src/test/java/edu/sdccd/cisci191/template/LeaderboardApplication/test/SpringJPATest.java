package edu.sdccd.cisci191.template.LeaderboardApplication.test;

import edu.sdccd.cisci191.template.LeaderboardApplication.LeaderboardApplication;
import edu.sdccd.cisci191.template.LeaderboardApplication.controller.PlayerController;
import edu.sdccd.cisci191.template.LeaderboardApplication.model.H2Player;
import edu.sdccd.cisci191.template.LeaderboardApplication.repo.PlayerRepo;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest()
public class SpringJPATest {

       @Autowired
       private PlayerRepo playerRepo;

       @Test
       public void testSaveUser() {
              // given
              H2Player player = new H2Player();
              player.setPlayerName("Player1");
              player.setPlayerClass("Knight");

              // when
              H2Player savedUser = playerRepo.save(player);

              // then
              assertNotNull(savedUser.getPlayerID());
              assertEquals(savedUser.getPlayerName(),"Player1");
              assertEquals(savedUser.getPlayerClass(),"Knight");
       }

       @Test
       public void testFindUserByUsername() {
              // given
              H2Player player = new H2Player();
              player.setPlayerName("Player1");
              player.setPlayerClass("Knight");
              playerRepo.save(player);

              // when
              H2Player foundUser = playerRepo.findByPlayerName("Player1");

              // then
               assertNotNull(foundUser);
               assertEquals(foundUser.getPlayerName(),player.getPlayerName());
               assertEquals(foundUser.getPlayerClass(),player.getPlayerClass());
       }
}
