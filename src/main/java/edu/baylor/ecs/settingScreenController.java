package edu.baylor.ecs;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
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

    }

    public void volumeSliderAction(ActionEvent event){

    }

    public void muteBoxAction(ActionEvent event){

    }

    
    public void backAction(ActionEvent event) throws IOException {
        backToHome();
    }

}
