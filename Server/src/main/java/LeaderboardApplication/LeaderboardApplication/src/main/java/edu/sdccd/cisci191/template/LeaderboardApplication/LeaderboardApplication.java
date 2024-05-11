package edu.sdccd.cisci191.template.LeaderboardApplication;

import org.h2.tools.Server;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.sql.SQLException;

//Run this first for H2 databases to work
@SpringBootApplication
public class LeaderboardApplication {

	public static void main(String[] args) {
		SpringApplication.run(LeaderboardApplication.class, args);
	}

	public Server inMemoryDBServer( ) throws SQLException{
		return Server.createTcpServer("tcp","-tcpAllowOthers","tcpPort","5432");
	}
}
