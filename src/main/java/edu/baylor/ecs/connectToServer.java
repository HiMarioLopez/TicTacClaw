package edu.baylor.ecs;

import java.sql.*;

public class connectToServer {
    private static final String DB_DRIVER = "com.mysql.jdbc.Driver";
    private static final String DB_CONNECTION = "jdbc:mysql://tictacclawinstance.ckgzixjdcowi.us-east-2.rds.amazonaws.com:3306/tictacclaw?autoReconnect=true&useSSL=false";
    private static final String DB_USER = "mario";
    private static final String DB_PASSWORD = "Gibson123";

    public static void main(String[] argv) {
        try {
            query();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    //private static void query

    private static void query() {
        try {
            Class.forName(DB_DRIVER);
            Connection dbConnection = DriverManager.getConnection(DB_CONNECTION,DB_USER, DB_PASSWORD);

            Statement statement = dbConnection.createStatement();
            ResultSet rs = statement.executeQuery("select * from users");

            while(rs.next()) {
                System.out.println(
                        "usr_ID: " + rs.getInt(1) + "\n" +
                        "usr_Name: " + rs.getString(2) + "\n" +
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