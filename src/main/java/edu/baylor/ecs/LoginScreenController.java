package edu.baylor.ecs;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Button;
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
    private boolean removeThisLater = true;

    @FXML
    private Scene homeScene;


    @Override
    public void initialize(URL location, ResourceBundle resources) {
    }

    public LoginScreenController()
    {

    }

    public void loginAction(){
        if(removeThisLater){
            //openHomeScene();
        }
    }

    public void changeSceneHome(ActionEvent event) throws IOException{
        Parent homeSceneParent = FXMLLoader.load(getClass().getResource("/homeScreen.fxml"));
        homeScene = new Scene(homeSceneParent);

        Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();
        window.setScene(homeScene);
        window.show();
    }
}
