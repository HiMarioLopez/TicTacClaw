package edu.baylor.ecs.Window.Controllers;

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

public class LoginScreenController extends MasterWindow implements Initializable{

    @FXML
    private Button loginButton, registerButton;

    @FXML
    private TextField username;

    @FXML
    private PasswordField password;

    public LoginScreenController(){
        System.out.println("Login was created");
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        defaultInit();
    }

    //loginButton
    public void loginAction(ActionEvent event) throws IOException, SQLException {
        System.out.println("User press Login button");

        String str_username = username.getText();
        String str_password = password.getText();

        str_username = str_username.toLowerCase();

        if (login(str_username, str_password)) {
            System.out.println("Login successful!");
            this.connectToHome();
            setWindow((Stage)((Node)event.getSource()).getScene().getWindow());
            getWindow().setScene(getCurrentScene());
            getWindow().show();
        } else {
            System.out.println("ERROR! Invalid credentials. Please try again.");
        }
    }

    //registerButton
    public void registerAction(ActionEvent event) throws IOException, SQLException {
        System.out.println("User press Register button");

        StrongPasswordEncryptor passwordEncryptor = new StrongPasswordEncryptor();
        String encryptedPassword = passwordEncryptor.encryptPassword(password.getText());

        if (register(username.getText().toLowerCase(), encryptedPassword)) {
            System.out.println("Registration successful!");
            this.connectToHome();
            setWindow((Stage)((Node)event.getSource()).getScene().getWindow());
            getWindow().setScene(getCurrentScene());
            //mediaBox.playMediaBox();
            getWindow().show();
        } else {
            System.out.println("ERROR! Registration unsuccessful. Is this username already in use?");
        }

    }
}
