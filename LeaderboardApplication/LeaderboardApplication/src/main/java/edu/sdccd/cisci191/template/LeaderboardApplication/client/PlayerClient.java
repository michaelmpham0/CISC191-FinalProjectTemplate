package edu.sdccd.cisci191.template.LeaderboardApplication.client;

import edu.sdccd.cisci191.template.LeaderboardApplication.model.H2Player;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

public class PlayerClient {
    private static final String BASE_URL = "http://localhost:5432/users";

    private final RestTemplate restTemplate=new RestTemplate();

    public PlayerClient() {
     //   this.restTemplate = new RestTemplate();
    }

    public H2Player getPlayerById(Long id) {
        ResponseEntity<H2Player> responseEntity = restTemplate.getForEntity(BASE_URL + "/" + id, H2Player.class);
        return responseEntity.getBody();
    }

    public H2Player addPlayer(H2Player h2Player) {
        ResponseEntity<H2Player> responseEntity = restTemplate.postForEntity(BASE_URL, h2Player, H2Player.class);
        return responseEntity.getBody();
    }

}
