package edu.baylor.ecs.Database;

import org.jasypt.util.password.StrongPasswordEncryptor;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

/**
 * Testing our ConnectToServer class.
 */
class ConnectToServerTest {

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

    /**
     * Creating new test table on our database
     * and inserting fake data to test with.
     * @throws SQLException in case of failed query.
     */
    @BeforeEach
    void setUp() throws SQLException {
        PreparedStatement statement = null;
        Connection dbConnection = null;
        String name, password;
        StrongPasswordEncryptor passwordEncryptor
                = new StrongPasswordEncryptor();
        String encryptedPassword;

        try {
            Class.forName(DB_DRIVER);
            dbConnection = DriverManager.
                    getConnection(DB_CONNECTION, DB_USER, DB_PASSWORD);

            // Create table in our database
            statement = dbConnection.prepareStatement(
                    "CREATE TABLE TestUsers ("
                    + "usr_Name VARCHAR(20) NOT NULL PRIMARY KEY, "
                    + "usr_Password VARCHAR(255) NOT NULL ) ");
            statement.executeUpdate();

            System.out.println("Table \"TestUsers\" was created!");

            dbConnection.close();

            dbConnection = DriverManager
                    .getConnection(DB_CONNECTION, DB_USER, DB_PASSWORD);

            statement = dbConnection.prepareStatement("INSERT "
                    + "INTO TestUsers VALUES ( ?, ?)");

            name = "Mario";
            password = "password";
            encryptedPassword = passwordEncryptor.encryptPassword(password);

            statement.setString(1, name);
            statement.setString(2, encryptedPassword);

            statement.executeUpdate();

            dbConnection.close();

            dbConnection = DriverManager
                    .getConnection(DB_CONNECTION, DB_USER, DB_PASSWORD);

            System.out.println("User \"Mario\" was inserted"
                    + " into the Database!");

            statement = dbConnection.prepareStatement("INSERT "
                    + "INTO TestUsers VALUES ( ?, ?)");

            name = "Brandon";
            password = "password";
            encryptedPassword = passwordEncryptor.encryptPassword(password);

            statement.setString(1, name);
            statement.setString(2, encryptedPassword);

            statement.executeUpdate();

            System.out.println("User \"Brandon\" was inserted"
                    + " into the Database!");

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

    /**
     * Dropping test table we just build on database.
     * @throws SQLException in case of failed query.
     */
    @AfterEach
    void tearDown() throws SQLException {
        PreparedStatement statement = null;
        Connection dbConnection = null;
        try {
            Class.forName(DB_DRIVER);
            dbConnection = DriverManager.
                    getConnection(DB_CONNECTION, DB_USER, DB_PASSWORD);

            // Drop table if it existed previously in our database;
            statement = dbConnection.
                    prepareStatement("DROP TABLE IF EXISTS TestUsers");
            statement.executeUpdate();

            System.out.println("Table \"TestUsers\" was dropped!");

        }  catch (Exception e) {
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

    /**
     * This tests for new user registration.
     * We assert true. Expect to be able to add user.
     * @throws SQLException in case of failed query.
     */
    @Test
    void registerAssertTrue() throws SQLException {
        assertTrue(ConnectToServer.register(
                "New",
                "User",
                "TestUsers"));
    }

    /**
     * Test the login on a user that is not in the database.
     * This assertsFalse, we expect to "fail" login.
     * @throws SQLException in case of failed query.
     */
    @Test
    void loginAssertFalse() throws SQLException {
        StrongPasswordEncryptor passwordEncryptor
                = new StrongPasswordEncryptor();
        String encryptedPassword = passwordEncryptor.
                encryptPassword("Cerny");

        assertFalse(ConnectToServer.login(
                "Tomas",
                encryptedPassword,
                "TestUsers"));
    }

    /**
     * Tests a login on a user that is in the DB.
     * We assertTrue.
     * @throws SQLException in case of failed query.
     */
    @Test
    void loginAssertTrue() throws SQLException {
        assertTrue(ConnectToServer.login(
                "Mario",
                "password",
                "TestUsers"));
    }
}
