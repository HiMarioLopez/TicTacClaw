package edu.baylor.ecs;

import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.scene.Parent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.event.ActionEvent;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;


public class homeScreenController extends UI implements Initializable{

    @FXML
    private Button coopButton,multiplayerButton,settingButton,exitButton;


    //private Scene homeScreen,settingScreen;
    //private Stage window;


    @Override
    public void initialize(URL location, ResourceBundle resources) {
    }

    public homeScreenController(){
        //homeScreen = exitButton.getScene();
        //window = (Stage)homeScreen.getWindow();
    }

    public void coopAction(ActionEvent event){
        System.out.println("User press Co-op");
    }

    public void multiplayerAction(ActionEvent event){
        System.out.println("User press Multiplayer");
    }

    public void settingAction(ActionEvent event) throws IOException {
        System.out.println("User press Settings");
        Parent homeSceneParent = FXMLLoader.load(getClass().getResource("/settingScreen.fxml"));
        settingScreen = new Scene(homeSceneParent);
        settingScreen.getStylesheets().add("/settingScreen.css");

        window = (Stage)((Node)event.getSource()).getScene().getWindow();
        window.setScene(settingScreen);
        window.setMinWidth(450);
        window.setMinHeight(500);

        window.setMaxWidth(650);
        window.setMaxHeight(600);
        window.show();

    }

    public void exitAction(ActionEvent event){
        System.out.println("User press Exit");
        Stage window = (Stage)exitButton.getScene().getWindow();
        this.closeProgram(window);
    }

}
