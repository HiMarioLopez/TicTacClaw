/*
 * Author: Mario Arturo Lopez
 * File: connectToServer.java
 * Project: Final Project
 * Course: CSI 3130/3371 Spring 2018
 * Due Date: TDB
 */

package edu.baylor.ecs;

import org.jasypt.util.password.StrongPasswordEncryptor;

import java.sql.*;

public class connectToServer {
    private static final String DB_DRIVER = "com.mysql.cj.jdbc.Driver";
    private static final String DB_CONNECTION = "jdbc:mysql://" +
            "gryzl.ckgzixjdcowi.us-east-2.rds.amazonaws.com:" +
            "3306/gryzl?autoReconnect=true";
    private static final String DB_USER = "user";
    private static final String DB_PASSWORD = "password";

    public static void main(String args[]) {
        createTable();
    }

    /*
     * Creates table Users in our MySQL database
     * Attributes:
     * char[20] usr_Name (Cannot be null)
     * char[255] usr_Password (Cannot be null)
     */
    public static void createTable() {
        try {
            Class.forName(DB_DRIVER);
            Connection dbConnection = DriverManager.getConnection(DB_CONNECTION,DB_USER, DB_PASSWORD);

            // Drop table if it existed previously in our database;
            Statement statement = dbConnection.createStatement();
            statement.executeUpdate("DROP TABLE IF EXISTS Users");

            // Create table in our database
            statement = dbConnection.createStatement();
            statement.executeUpdate("CREATE TABLE Users (" +
                    "usr_Name VARCHAR(20) NOT NULL PRIMARY KEY, " +
                    "usr_Password VARCHAR(255) NOT NULL ) ");
            dbConnection.close();

            System.out.println("Table was created!");

        } catch (Exception e) {
            e.printStackTrace();
            System.out.println(e.getMessage());
        }
    }

    public static boolean register(String usr_Name, String usr_Password) {
        boolean registrationStatus = true;
        try {
            Class.forName(DB_DRIVER);
            Connection dbConnection = DriverManager.getConnection(DB_CONNECTION,DB_USER, DB_PASSWORD);

            Statement statement = dbConnection.createStatement();
            ResultSet rs = statement.executeQuery("SELECT * FROM Users WHERE usr_Name='" + usr_Name + "'");

            if(rs.next()) {
                registrationStatus = false;
            } else {
                dbConnection.close();

                dbConnection = DriverManager.getConnection(DB_CONNECTION, DB_USER, DB_PASSWORD);
                statement = dbConnection.createStatement();

                statement = dbConnection.createStatement();
                statement.executeUpdate("INSERT INTO Users " +
                        "(usr_Name, usr_Password) " +
                        "VALUES ('" + usr_Name + "', '" + usr_Password + "')");

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
            ResultSet rs = statement.executeQuery("SELECT usr_Password FROM Users " +
                    "WHERE usr_Name='" + usr_Name + "'");

            rs.next();

            StrongPasswordEncryptor passwordEncryptor = new StrongPasswordEncryptor();
            String encryptedPassword = rs.getString("usr_Password");
            
            if (passwordEncryptor.checkPassword(usr_Password, encryptedPassword)) {
                loginStatus = true;
            }
            dbConnection.close();
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println(e.getMessage());
        }

        return loginStatus;
    }
}