package edu.sdccd.cisc191.template.LeaderboardSystem;
import java.sql.*;


public class Database {
    private static final String URL = "jdbc:sqlite:/Users/doluis/Desktop/architectdatabase.db";

    public static Connection connect() {
        Connection conn = null;

        try {
            // creates a connection to the database
            conn = DriverManager.getConnection(URL);
            System.out.println("Connection to SQLite has been established.");
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return conn;
    }

    public static void printPlayerDetails(String playerName) {
        String query = "SELECT * FROM leaderboard WHERE player_name = ?";

        try (Connection conn = Database.connect();
             PreparedStatement pstmt = conn.prepareStatement(query)) {

            pstmt.setString(1, playerName);
            ResultSet rs = pstmt.executeQuery();

            while (rs.next()) {
                System.out.println("Name: " + rs.getString("player_name") +
                        ", Class: " + rs.getString("class") +
                        ", Level: " + rs.getInt("level"));
            }
        } catch (SQLException e) {
            System.out.println("Query failed: " + e.getMessage());
        }
    }
}
