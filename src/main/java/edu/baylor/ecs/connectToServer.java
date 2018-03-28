package edu.baylor.ecs;

import java.sql.*;

public class connectToServer {
    private static final String DB_DRIVER = "com.mysql.jdbc.Driver";
    private static final String DB_CONNECTION = "jdbc:mysql://localhost:3306/tictacclaw;";
    private static final String DB_USER = "root";
    private static final String DB_PASSWORD = "Gibson123";

    public static void main(String[] argv) {
        try {

            try {
                Class.forName("com.mysql.jdbc.Driver");
            } catch (ClassNotFoundException e) {
                throw new RuntimeException("Cannot find the driver in the classpath!", e);
            }

            query();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    private static void query() {
        try {
            Class.forName("com.mysql.jdbc.Driver");
            Connection dbConnection = DriverManager.getConnection("jdbc:mysql://localhost:3306/tictacclaw?autoReconnect=true&useSSL=false","root","Gibson123");

            Statement statement = dbConnection.createStatement();
            ResultSet rs = statement.executeQuery("select * from users");

            while(rs.next()) {
                System.out.println(
                        "usr_ID: " + rs.getInt(1) + "\n" +
                        "Username: " + rs.getString(2) + "\n" +
                        "usr_Password: " + rs.getString(3) + "\n" +
                        "usr_HighScore: " + rs.getInt(4) + "\n" +
                        "usr_ImageIndex: " + rs.getInt(5));
            }

            dbConnection.close();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
}