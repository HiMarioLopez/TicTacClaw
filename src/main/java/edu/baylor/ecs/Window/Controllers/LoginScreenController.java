package edu.baylor.ecs.Window.Controllers;

import edu.baylor.ecs.MediaPlayer.MediaBox;
import edu.baylor.ecs.Window.MasterWindow;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import javafx.scene.Node;
import javafx.event.ActionEvent;
import org.jasypt.util.password.StrongPasswordEncryptor;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

import static edu.baylor.ecs.Database.ConnectToServer.login;
import static edu.baylor.ecs.Database.ConnectToServer.register;

/** Author: Brandon Mork. */
public class LoginScreenController extends MasterWindow
        implements Initializable {

    /** the Button to login and register.
     */
    @FXML
    private Button loginButton, registerButton;

    /** the TextField for the username.
     */
    @FXML
    private TextField username;

    /** the PasswordField for the password.
     */
    @FXML
    private PasswordField password;

    /** Author: Brandon Mork. */
    public LoginScreenController() {
        System.out.println("Login was created");
    }

    @Override
    public final void initialize(final URL location,
                                 final ResourceBundle resources) {
        defaultInit();
    }

    /** Author: Brandon Mork.
     * the user will go to Home Screen and music will start if successful.
     * @param event the user clicking the button
     * @throws IOException if there is a problem going to the next screen
     * @throws SQLException if there is a problem connecting to database */
    public final void loginAction(final ActionEvent event)
            throws IOException, SQLException {
        System.out.println("User press Login button");

        String strUsername = username.getText();
        String strPassword = password.getText();

        strUsername = strUsername.toLowerCase();

        if (login(strUsername, strPassword, "Users")) {
            System.out.println("Login successful!");
            this.connectToHome();
            setWindow((Stage) ((Node) event.getSource())
                    .getScene().getWindow());
            getWindow().setScene(getCurrentScene());

            MediaBox.getInstance().playMediaBox();

            getWindow().show();
        } else {
            System.out.println("ERROR! Invalid credentials. Please try again.");
        }
    }

    /** Author: Brandon Mork.
     * @param event the user clicking the button
     * @throws IOException if there is a problem going to the next screen
     * @throws SQLException if there is a problem connecting to database */
    public final void registerAction(final ActionEvent event)
            throws IOException, SQLException {
        System.out.println("User press Register button");

        StrongPasswordEncryptor passwordEncryptor
                = new StrongPasswordEncryptor();
        String encryptedPassword
                = passwordEncryptor.encryptPassword(password.getText());

        if (register(
                username.getText().toLowerCase(),
                encryptedPassword,
                "Users")) {
            System.out.println("Registration successful!");
            this.connectToHome();
            setWindow((Stage) ((Node) event.getSource())
                    .getScene().getWindow());
            getWindow().setScene(getCurrentScene());
            getWindow().show();
        } else {
            System.out.println("ERROR! Registration unsuccessful."
                    + " Is this username already in use?");
        }

    }
}
