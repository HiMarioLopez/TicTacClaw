package edu.baylor.ecs;

import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.scene.Parent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.event.ActionEvent;
import java.net.URL;
import java.util.ResourceBundle;

public class settingScreenController extends UI implements Initializable{

    @FXML
    private ChoiceBox resolutionBox,fullscreenBox;

    @FXML
    private Slider volumeSlider;

    @FXML
    private CheckBox muteBox;

    @FXML
    private Button backButton;


    public settingScreenController(){

    }

    public void resolutionAction(ActionEvent event){

    }

    public void fullscreenAction(ActionEvent event){

    }

    public void volumeSliderAction(ActionEvent event){

    }

    public void muteBoxAction(ActionEvent event){

    }

    
    public void backAction(ActionEvent event){

    }





    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }
}
