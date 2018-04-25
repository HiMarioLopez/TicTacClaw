package edu.baylor.ecs;

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
import java.security.GeneralSecurityException;
import java.util.ResourceBundle;

import static edu.baylor.ecs.connectToServer.login;
import static edu.baylor.ecs.connectToServer.register;

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
    public void loginAction(ActionEvent event) throws IOException {
        System.out.println("User press Login button");

        String str_username = username.getText();
        String str_password = password.getText();

        str_username = str_username.toLowerCase();

        if (login("mario", "123")) {
            System.out.println("Login successful!");
            this.connectToHome();
            setWindow((Stage)((Node)event.getSource()).getScene().getWindow());
            getWindow().setScene(getCurrentScene());
            //mediaBox.playMediaBox();
            getWindow().show();
        } else {
            System.out.println("ERROR! Invalid credentials. Please try again.");
        }
    }

    //registerButton
    public void registerAction(ActionEvent event) throws IOException {
        System.out.println("User press Register button");

        String str_username = username.getText();
        String str_password = password.getText();

        str_username = str_username.toLowerCase();

        StrongPasswordEncryptor passwordEncryptor = new StrongPasswordEncryptor();
        String encryptedPassword = passwordEncryptor.encryptPassword(str_password);

        if (register(str_username, encryptedPassword)) {
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
