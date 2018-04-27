/*
 * Author: Mario Arturo Lopez
 * File: ConnectToServer.java
 * Project: Final Project
 * Course: CSI 3130/3371 Spring 2018
 * Due Date: TDB
 */

package edu.baylor.ecs.Database;

import org.jasypt.util.password.StrongPasswordEncryptor;

import java.sql.ResultSet;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;


/** Author: Mario Lopez. */
public final class ConnectToServer {

    /** Driver to be used for our database type. */
    private static final String DB_DRIVER = "com.mysql.cj.jdbc.Driver";

    /** Endpoint link to be able to connect to our database
     * (this will not change). */
    private static final String DB_CONNECTION = "jdbc:mysql://"
            + "gryzl.ckgzixjdcowi.us-east-2.rds.amazonaws.com:"
            + "3306/gryzl?autoReconnect=true";

    /** Authorized user to access our server. */
    private static final String DB_USER = "user";

    /** Password credentials for database login to execute queries. */
    private static final String DB_PASSWORD = "password";

    /** Author: Mario Lopez. */
    private ConnectToServer() { }

    /** Author: Mario Lopez.
     * @param args arguments passed by user into main.
     * @throws SQLException incase method calls result in query issue.
     */
    public static void main(final String[] args) throws SQLException {
        createTable();
    }

    /** Author: Mario Lopez.
     * Creates table entitled Users inside the database.
     * @throws SQLException incase issues with queries.
     */
    private static void createTable() throws SQLException {
        PreparedStatement statement = null;
        Connection dbConnection = null;
        try {
            Class.forName(DB_DRIVER);
            dbConnection = DriverManager.
                    getConnection(DB_CONNECTION, DB_USER, DB_PASSWORD);

            // Drop table if it existed previously in our database;
            statement = dbConnection.
                    prepareStatement("DROP TABLE IF EXISTS Users");
            statement.executeUpdate();

            // Create table in our database
            statement = dbConnection.prepareStatement("CREATE TABLE Users ("
                    + "usr_Name VARCHAR(20) NOT NULL PRIMARY KEY, "
                    + "usr_Password VARCHAR(255) NOT NULL ) ");
            statement.executeUpdate();
            dbConnection.close();

            System.out.println("Table was created!");

        } catch (Exception e) {
            e.printStackTrace();
            System.out.println(e.getMessage());
        } finally {
            if (dbConnection != null) {
                dbConnection.close();
            }
            if (statement != null) {
                statement.close();
            }
        }
    }

    /** Author: Mario Lopez.
     * Creates table entitled Users inside the database.
     * @return boolean that notified whether registration was successful.
     * @param usrName user's unique identifier that will be stored
     *                lowercase into the login server.
     * @param usrPassword user's password that will be encrypted
     *                    before storing onto the server.
     * @param tableName name of table you will be inserting into
     * @throws SQLException in case there is an issue running the queries.
     */
    public static boolean register(final String usrName,
                                   final String usrPassword,
                                   final String tableName)
            throws SQLException {
        boolean registrationStatus = true;
        Connection dbConnection = null;
        PreparedStatement statement = null;
        ResultSet rs = null;
        try {
            Class.forName(DB_DRIVER);
            dbConnection = DriverManager
                    .getConnection(DB_CONNECTION, DB_USER, DB_PASSWORD);

            statement = dbConnection
                    .prepareStatement("SELECT * FROM " + tableName
                            + " WHERE usr_Name=?");
            statement.setString(1, usrName);
            rs = statement.executeQuery();

            if (rs.next()) {
                registrationStatus = false;
            } else {
                dbConnection.close();

                dbConnection = DriverManager
                        .getConnection(DB_CONNECTION, DB_USER, DB_PASSWORD);
                statement = dbConnection
                        .prepareStatement("INSERT INTO " + tableName
                                + " (usr_Name, usr_Password) "
                                + "VALUES (?, ?)");
                statement.setString(1, usrName);
                statement.setString(2, usrPassword);

                statement.executeUpdate();

                dbConnection.close();
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        } finally {
            if (dbConnection != null) {
                dbConnection.close();
            }
            if (statement != null) {
                statement.close();
            }
            if (rs != null) {
                rs.close();
            }
        }
        return registrationStatus;
    }

    /** Author: Mario Lopez.
     * Creates table entitled Users inside the database.
     * @return boolean that notified whether login was successful.
     * @param usrName the user's unique user name to be queried.
     * @param usrPassword the user's password to be compared
     *                     to password stored on server.
     * @param tableName name of table you will be inserting into
     * @throws SQLException in case there are issues running queries.
     */
    public static boolean login(final String usrName,
                                final String usrPassword,
                                final String tableName)
            throws SQLException {
        boolean loginStatus = false;
        PreparedStatement statement = null;
        Connection dbConnection = null;
        try {
            Class.forName(DB_DRIVER);
            dbConnection = DriverManager
                    .getConnection(DB_CONNECTION, DB_USER, DB_PASSWORD);

            statement = dbConnection
                    .prepareStatement("SELECT usr_Password FROM " + tableName
                            + " WHERE usr_Name=?");
            statement.setString(1, usrName);
            ResultSet rs = statement.executeQuery();

            rs.next();

            StrongPasswordEncryptor passwordEncryptor =
                    new StrongPasswordEncryptor();
            String encryptedPassword = rs.getString("usr_Password");

            if (passwordEncryptor.checkPassword(usrPassword,
                    encryptedPassword)) {
                loginStatus = true;
            }
            dbConnection.close();
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println(e.getMessage());
        } finally {
            if (statement != null) {
                statement.close();
            }
            if (dbConnection != null) {
                dbConnection.close();
            }
        }

        return loginStatus;
    }
}
