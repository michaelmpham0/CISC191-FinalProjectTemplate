package edu.sdccd.cisc191.template.LeaderboardSystem;
import edu.sdccd.cisc191.template.Player;

import java.sql.*;

public class Database {
    private static final String URL = "jdbc:sqlite:/Users/doluis/Desktop/architectdatabase.db";
    private static Connection conn = null;

    // creates a connection to the DB browser database
    public static Connection connect() {
        if (conn == null) {
            try {
                // creates a connection to the database
                conn = DriverManager.getConnection(URL);
                System.out.println("Connection to SQLite has been established.");
            } catch (SQLException e) {
                System.out.println("Error when connecting to SQLite: " + e.getMessage());
            }
        }
        return conn;
    }

    // closes connection after done with process
    public static void closeConnection() {
        if (conn != null) {
            try {
                conn.close();
                conn = null;
                System.out.println("Connection to SQLite has been closed.");
            } catch (SQLException e) {
                System.out.println("Error when closing the SQLite connection: " + e.getMessage());
            }
        }
    }

    //retrieves and prints player details from the database
    public static void printPlayerDetails(String playerName) {
        String query = "SELECT * FROM leaderboard WHERE player_name = ?";

        try (Connection conn = Database.connect();
             PreparedStatement preparedStatement = conn.prepareStatement(query)) {

            preparedStatement.setString(1, playerName);
            ResultSet rs = preparedStatement.executeQuery();

            while (rs.next()) {
                System.out.println("Name: " + rs.getString("player_name") +
                        ", Class: " + rs.getString("class") +
                        ", Level: " + rs.getInt("level"));
            }
        } catch (SQLException e) {
            System.out.println("Query failed: " + e.getMessage());
        }
    }

    //inserts details of Player object into database.
    public static void insertPlayerDetails(Player player) {
        String sql = "INSERT INTO leaderboard (player_name, class, level, score) VALUES (?, ?, ?, ?)";

        try (Connection conn = Database.connect();
             PreparedStatement preparedStatement = conn.prepareStatement(sql)) {

            preparedStatement.setString(1, player.getName());
            preparedStatement.setString(2, player.getPlayerClass());
            preparedStatement.setInt(3, player.getLevel());
            preparedStatement.setInt(4, player.getScore());

            int affectedRows = preparedStatement.executeUpdate();
            if (affectedRows > 0) {
                System.out.println("Player added successfully!");
            } else {
                System.out.println("A problem occurred and no rows were added.");
            }

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }
}
