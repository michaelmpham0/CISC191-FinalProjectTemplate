package edu.sdccd.cisc191.springjpa.Services;

import edu.sdccd.cisc191.springjpa.Models.Option;
import edu.sdccd.cisc191.springjpa.Models.Player;
import edu.sdccd.cisc191.springjpa.Repository.LeaderboardRepository;
import edu.sdccd.cisc191.springjpa.Repository.OptionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Vector;

@Service
public class LeaderboardService {
    @Autowired
    LeaderboardRepository lbRepo;

    @Autowired
    OptionRepository optionRepo;

    public List<Player> findAll(){
        return (List<Player>)lbRepo.findAll();
     }

     public Option save(Option option){
        return optionRepo.save(option);
     }

    public Player save(Player Player){
        return lbRepo.save(Player);
    }
}
