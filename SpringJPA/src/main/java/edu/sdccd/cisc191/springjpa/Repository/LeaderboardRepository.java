package edu.sdccd.cisc191.springjpa.Repository;

import edu.sdccd.cisc191.springjpa.Models.Player;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface LeaderboardRepository extends CrudRepository<Player,String> {
    List<Player> findByPlayerName(String playerName);

}
