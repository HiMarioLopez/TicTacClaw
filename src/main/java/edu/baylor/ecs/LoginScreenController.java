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

public class LoginScreenController implements Initializable{

    @FXML
    private Button loginButton, registerButton;

    @FXML
    private Hyperlink forgotPassword;

    @FXML
    private Scene homeScene;


    @Override
    public void initialize(URL location, ResourceBundle resources) {
    }

    public LoginScreenController(){
    }

    //loginButton
    public void loginAction(ActionEvent event) throws IOException{
        System.out.println("User press Login button");
        Parent homeSceneParent = FXMLLoader.load(getClass().getResource("/homeScreen.fxml"));
        homeScene = new Scene(homeSceneParent);
        homeScene.getStylesheets().add("/homeScreen.css");

        Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();
        window.setScene(homeScene);
        window.setMinWidth(450);
        window.setMinHeight(500);

        window.setMaxWidth(650);
        window.setMaxHeight(600);
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
