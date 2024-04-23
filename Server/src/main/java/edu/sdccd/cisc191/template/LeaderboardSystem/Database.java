package edu.sdccd.cisc191.template.LeaderboardSystem;
import edu.sdccd.cisc191.template.Player;

import java.io.InputStream;
import java.sql.*;

public class Database {
    static String databasePath = "jdbc:sqlite:" + System.getProperty("user.home") + "/Documents/architectdatabase.db";
    static InputStream inputStream = Database.class.getResourceAsStream("/architectdatabase.db");
    //System.getProperty("user.home") + "/Documents/ArchitectSaveFile.ser"
    private static Connection conn = null;
    // creates a connection to the DB browser database
    public static Connection connect() {
        if (conn == null) {
            try {
                // creates a connection to the database
                conn = DriverManager.getConnection("jdbc:sqlite:"+inputStream.toString());
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

    //retrieves and prints ALL player details from the database
    public static void printAllPlayersDetails() {
        String query = "SELECT * FROM leaderboard";

        try (Connection conn = Database.connect();
             PreparedStatement preparedStatement = conn.prepareStatement(query)) {

            ResultSet rs = preparedStatement.executeQuery();

            while (rs.next()) {
                System.out.println(
                        "Name: " + rs.getString("player_name") +
                        ", Class: " + rs.getString("class") +
                        ", Level: " + rs.getInt("level") +
                        ", Score: " + rs.getInt("score")
                );
            }
        } catch (SQLException e) {
            System.out.println("Query failed: " + e.getMessage());
        } finally {
            Database.closeConnection();
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

            System.out.println(player.getName());
            System.out.println(player.getPlayerClass());
            System.out.println(player.getLevel());
            System.out.println(player.getScore());

            int affectedRows = preparedStatement.executeUpdate();
            if (affectedRows > 0) {
                System.out.println("Player added successfully!");
            } else {
                System.out.println("A problem occurred and no rows were added.");
            }

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        } finally {
            Database.closeConnection();
        }
    }
}
