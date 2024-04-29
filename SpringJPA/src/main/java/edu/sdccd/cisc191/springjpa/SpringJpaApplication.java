package edu.sdccd.cisc191.springjpa;

import edu.sdccd.cisc191.springjpa.Models.Option;
import edu.sdccd.cisc191.springjpa.Models.Player;
import edu.sdccd.cisc191.springjpa.Services.LeaderboardService;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;
import org.h2.tools.Server;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@SpringBootApplication
public class SpringJpaApplication extends Application {
	public ConfigurableApplicationContext springContext;

	public static void main(String[] args) {
		launch(SpringJpaApplication.class, args);
	}

	@Override
	public void start(Stage stage) throws Exception {
		LeaderboardService lbService = springContext.getBean(LeaderboardService.class);
		stage.setTitle("Players");
		HBox root = new HBox();

		for (Player player : lbService.findAll()){
			Button btn = new Button();
			btn.setText(player.getPlayerName());
			btn.setOnAction(event -> {
				Alert alert = new Alert(Alert.AlertType.INFORMATION);
				alert.setTitle("Player Info");
				alert.setHeaderText("Options");
				alert.setContentText(player.getOptionsAsString());
				alert.show();
			});
			root.getChildren().add(btn);
		}

		stage.setScene(new Scene(root,800,800));
		stage.show();
	}

	@Override
	public void stop() throws Exception {
		springContext.stop();
	}

	@Override
	public void init() throws Exception {
		springContext = SpringApplication.run(SpringJpaApplication.class);

		LeaderboardService lbService = springContext.getBean(LeaderboardService.class);

		Option pName = new Option("Phu");
		Option pClass = new Option("Knight");
		Option pLevel = new Option("1");
		Option pScore = new Option("0");

		lbService.save(pName);
		lbService.save(pClass);
		lbService.save(pLevel);
		lbService.save(pScore);

		List<Option> optionsList = new ArrayList<>();
		optionsList.add(pName);
		optionsList.add(pClass);
		optionsList.add(pLevel);
		optionsList.add(pScore);

		Player phu = new Player("Phu","Knight",1,0,optionsList);
		lbService.save(phu);

		Player tigy = new Player("Tigy","Wizard",2,5,null);
		lbService.save(tigy);

		for(Player player : lbService.findAll()){
			System.out.println(player.toString());
		}
	}

	public Server inMemoryDBServer() throws SQLException{
		return Server.createTcpServer("-tcp","-tcpAllowOthers","-tcpPort","9092");
	}

}
