package edu.baylor.ecs;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class connectToServer {
    private static final String DB_DRIVER = "org.apache.derby.jdbc.EmbeddedDriver";
    private static final String DB_CONNECTION = "jdbc:derby:TicTacClaw;";
    private static final String DB_USER = "";
    private static final String DB_PASSWORD = "";

    public static void main(String[] argv) {
        try {
            createDbUserTable();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    private static void createDbUserTable() throws SQLException {
        Connection dbConnection = null;
        Statement statement = null;

        String createTableSQL = "DROP TABLE Users";

        /*
        String createTableSQL = " CREATE TABLE Users(" +
                "usr_ID INTEGER NOT NULL, " +
                "usr_Name VARCHAR(50) NOT NULL, " +
                "usr_Password VARCHAR(50) NOT NULL, " +
                "usr_HighScore INTEGER, " +
                "usr_ImageIndex INTEGER, " +
                "PRIMARY KEY (usr_ID) " + ")";
        */

        try {
            dbConnection = getDBConnection();
            statement = dbConnection.createStatement();
            System.out.println(createTableSQL);

            // Execute the SQL statement
            statement.execute(createTableSQL);
            System.out.println("Table \"User\" is created!");
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        } finally {
            if (statement != null) {
                statement.close();
            }
            if (dbConnection != null) {
                dbConnection.close();
            }
        }
    }

    private static Connection getDBConnection() {
        Connection dbConnection = null;
        try {
            Class.forName(DB_DRIVER);
        } catch (ClassNotFoundException e) {
            System.out.println(e.getMessage());
        }
        try {
            dbConnection = DriverManager.getConnection(DB_CONNECTION, DB_USER, DB_PASSWORD);
            return dbConnection;
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return dbConnection;
    }
}