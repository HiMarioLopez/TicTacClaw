package edu.baylor.ecs;

import javafx.application.Application;
import javafx.collections.FXCollections;
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

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class settingScreenController extends MasterWindow implements Initializable{

    @FXML
    private ChoiceBox resolutionBox,fullscreenBox;

    @FXML
    private Slider volumeSlider;

    @FXML
    private CheckBox muteBox;

    @FXML
    private Button backButton;


    public settingScreenController(){
        System.out.println("Created settings");
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        //resolutionBox.getItems().clear();
        System.out.println("Called init");
        getWindow().setMinWidth(650);
        getWindow().setMinHeight(650);

        getWindow().setMaxHeight(750);
        getWindow().setMaxWidth(750);

        resolutionBox.getItems().addAll("1280 x 1024", "1600 x 1200" , "1680 x 1050" , "1920 x 1080");

        fullscreenBox.getItems().addAll("Windowed", "Windowed Fullscreen");
    }

    public void resolutionAction(ActionEvent event){

    }

    public void fullscreenAction(ActionEvent event){

    }

    public void volumeSliderAction(ActionEvent event){

    }

    public void muteBoxAction(ActionEvent event){

    }

    
    public void backAction(ActionEvent event) throws IOException {
        this.connectToHome();
        setWindow((Stage)((Node)event.getSource()).getScene().getWindow());
        getWindow().setScene(getCurrentScene());
        getWindow().show();
    }

}
