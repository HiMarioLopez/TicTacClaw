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
import java.net.URL;
import java.util.ResourceBundle;

import static com.sun.org.apache.xalan.internal.xsltc.compiler.util.Type.Node;
public class homeScreenController {

    @FXML
    private Button coopButton,multiplayerButton,settingButton,exitButton;


    public homeScreenController(){
    }

    public void coopAction(ActionEvent event){
        System.out.println("User press Co-op");
    }

    public void multiplayerAction(ActionEvent event){
        System.out.println("User press Multiplayer");
    }

    public void settingAction(ActionEvent event){
        System.out.println("User press Settings");
    }

    public void exitAction(ActionEvent event){
        System.out.println("User press Exit");
    }


}
