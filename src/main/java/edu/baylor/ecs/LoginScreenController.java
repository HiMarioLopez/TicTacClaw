package edu.baylor.ecs;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Hyperlink;
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


    public LoginScreenController(){
        //System.out.println("Created LoginScreenController");
    }

    //loginButton
    public void loginAction(ActionEvent event) throws IOException{
        System.out.println("User press Login button");

        this.connectResources();
        window = (Stage)((Node)event.getSource()).getScene().getWindow();
        window.setScene(getHomeScreen());

        //mediaBox.playMediaBox();

        window.show();
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
