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

        this.connectToHome();
        setWindow((Stage)((Node)event.getSource()).getScene().getWindow());
        getWindow().setScene(getCurrentScene());

        //mediaBox.playMediaBox();

        getWindow().show();
    }

    //registerButton
    public void registerAction(ActionEvent event){
        System.out.println("User press Register button");

    }

    //forgotPassword
    public void forgotAction(ActionEvent event){
        System.out.println("User press Forgot Password");
    }
}
