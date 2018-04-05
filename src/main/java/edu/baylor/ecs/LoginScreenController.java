package edu.baylor.ecs;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import javafx.scene.Parent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.event.ActionEvent;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import static edu.baylor.ecs.connectToServer.login;
import static edu.baylor.ecs.connectToServer.register;

public class LoginScreenController extends MasterWindow{

    @FXML
    private Button loginButton, registerButton;

    @FXML
    private Hyperlink forgotPassword;

    @FXML
    private TextField username;

    @FXML
    private PasswordField password;

    public LoginScreenController(){
        //System.out.println("Created LoginScreenController");
    }

    //loginButton
    public void loginAction(ActionEvent event) throws IOException{
        System.out.println("User press Login button");

        if (login(username.getText(), password.getText())) {
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
    public void registerAction(ActionEvent event){
        System.out.println("User press Register button");

        if (register(username.getText(), password.getText())) {
            System.out.println("Registration successful!");
        } else {
            System.out.println("ERROR! Registration unsuccessful. Did you forget your password?");
        }

    }

    //forgotPassword
    public void forgotAction(ActionEvent event){
        System.out.println("User press Forgot Password");
    }
}
