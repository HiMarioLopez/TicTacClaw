/*
 * Author: Mario Arturo Lopez
 * File: connectToServer.java
 * Project: Final Project
 * Course: CSI 3130/3371 Spring 2018
 * Due Date: TDB
 */

package edu.baylor.ecs;

import java.sql.*;

public class connectToServer {
    private static final String DB_DRIVER = "com.mysql.jdbc.Driver";
    private static final String DB_CONNECTION = "jdbc:mysql://" +
            "tictacclaw-aws.ckgzixjdcowi.us-east-2.rds.amazonaws.com:" +
            "3306/tictacclaw?autoReconnect=true&useSSL=false";
    private static final String DB_USER = "mario";
    private static final String DB_PASSWORD = "Gibson123";

    public static boolean register(String usr_Name, String usr_Password) {
        boolean registrationStatus = true;
        try {
            Class.forName(DB_DRIVER);
            Connection dbConnection = DriverManager.getConnection(DB_CONNECTION,DB_USER, DB_PASSWORD);

            Statement statement = dbConnection.createStatement();
            ResultSet rs = statement.executeQuery("select * from users where usr_Name='" + usr_Name + "'");

            if(rs.next()) {
                registrationStatus = false;
            } else {
                dbConnection.close();

                dbConnection = DriverManager.getConnection(DB_CONNECTION, DB_USER, DB_PASSWORD);
                statement = dbConnection.createStatement();
                statement.executeUpdate("insert into users " +
                        "(usr_Name, usr_Password, usr_HighScore, usr_ImageIndex) " +
                        "values ('" + usr_Name + "', '" + usr_Password + "', null, 0)");

                dbConnection.close();
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

        return registrationStatus;
    }

    public static boolean login(String usr_Name, String usr_Password) {
        boolean loginStatus = false;
        try {
            Class.forName(DB_DRIVER);
            Connection dbConnection = DriverManager.getConnection(DB_CONNECTION,DB_USER, DB_PASSWORD);

            Statement statement = dbConnection.createStatement();
            ResultSet rs = statement.executeQuery("select * from users where usr_Name='" +
                                                        usr_Name + "' AND usr_Password='" +
                                                        usr_Password + "'");

            if(rs.next())
                loginStatus = true;

            dbConnection.close();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

        return loginStatus;
    }
}