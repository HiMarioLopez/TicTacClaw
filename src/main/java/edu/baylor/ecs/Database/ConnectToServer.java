/*
 * Author: Mario Arturo Lopez
 * File: ConnectToServer.java
 * Project: Final Project
 * Course: CSI 3130/3371 Spring 2018
 * Due Date: TDB
 */

package edu.baylor.ecs.Database;

import org.jasypt.util.password.StrongPasswordEncryptor;

import java.sql.*;

public class ConnectToServer {
    private static final String DB_DRIVER = "com.mysql.cj.jdbc.Driver";
    private static final String DB_CONNECTION = "jdbc:mysql://" +
            "gryzl.ckgzixjdcowi.us-east-2.rds.amazonaws.com:" +
            "3306/gryzl?autoReconnect=true";
    private static final String DB_USER = "user";
    private static final String DB_PASSWORD = "password";

    public static void main(String args[]) throws SQLException {
        createTable();
    }

    public static void createTable() throws SQLException {
        PreparedStatement statement = null;
        Connection dbConnection = null;
        try {
            Class.forName(DB_DRIVER);
            dbConnection = DriverManager.getConnection(DB_CONNECTION,DB_USER, DB_PASSWORD);

            // Drop table if it existed previously in our database;
            statement = dbConnection.prepareStatement("DROP TABLE IF EXISTS Users");
            statement.executeUpdate();

            // Create table in our database
            statement = dbConnection.prepareStatement("CREATE TABLE Users (" +
                    "usr_Name VARCHAR(20) NOT NULL PRIMARY KEY, " +
                    "usr_Password VARCHAR(255) NOT NULL ) ");
            statement.executeUpdate();
            dbConnection.close();

            System.out.println("Table was created!");

        } catch (Exception e) {
            e.printStackTrace();
            System.out.println(e.getMessage());
        } finally {
            if(dbConnection != null)
                dbConnection.close();
            if(statement != null)
                statement.close();
        }
    }

    public static boolean register(String usr_Name, String usr_Password) throws SQLException {
        boolean registrationStatus = true;
        Connection dbConnection = null;
        PreparedStatement statement = null;
        try {
            Class.forName(DB_DRIVER);
            dbConnection = DriverManager.getConnection(DB_CONNECTION,DB_USER, DB_PASSWORD);

            statement = dbConnection.prepareStatement("SELECT * FROM Users WHERE usr_Name=?");
            statement.setString(1, usr_Name);
            ResultSet rs = statement.executeQuery();

            if(rs.next()) {
                registrationStatus = false;
            } else {
                dbConnection.close();

                dbConnection = DriverManager.getConnection(DB_CONNECTION, DB_USER, DB_PASSWORD);
                statement = dbConnection.prepareStatement("INSERT INTO Users " +
                        "(usr_Name, usr_Password) " +
                        "VALUES (?, ?)");
                statement.setString(1, usr_Name);
                statement.setString(2, usr_Password);

                statement.executeUpdate();

                dbConnection.close();
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        } finally {
            if(dbConnection != null)
                dbConnection.close();
            if(statement != null)
                statement.close();
        }

        return registrationStatus;
    }

    public static boolean login(final String usr_Name, final String usr_Password) throws SQLException {
        boolean loginStatus = false;
        PreparedStatement statement = null;
        Connection dbConnection = null;
        try {
            Class.forName(DB_DRIVER);
            dbConnection = DriverManager.getConnection(DB_CONNECTION,DB_USER, DB_PASSWORD);

            statement = dbConnection.prepareStatement("SELECT usr_Password FROM Users " +
                    "WHERE usr_Name=?");
            statement.setString(1, usr_Name);
            ResultSet rs = statement.executeQuery();

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
        } finally {
            if(statement != null)
                statement.close();
            if(dbConnection != null)
                dbConnection.close();
        }

        return loginStatus;
    }
}