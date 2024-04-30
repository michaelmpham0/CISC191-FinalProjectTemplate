package edu.sdccd.cisci191.template.LeaderboardApplication.repo;

import edu.sdccd.cisci191.template.LeaderboardApplication.model.H2Player;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PlayerRepo extends JpaRepository<H2Player,Long> {

}
