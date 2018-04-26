package edu.baylor.ecs.Window.Controllers;

import edu.baylor.ecs.MediaPlayer.MediaBox;
import edu.baylor.ecs.Window.MasterWindow;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.event.ActionEvent;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class SettingScreenController extends MasterWindow implements Initializable{

    @FXML
    private Slider volumeSlider;


    @FXML
    private CheckBox muteBox;

    @FXML
    private Button backButton;


    public SettingScreenController(){
        System.out.println("Created settings");
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        getWindow().setMinWidth(650);
        getWindow().setMinHeight(650);

        getWindow().setMaxHeight(750);
        getWindow().setMaxWidth(750);

        volumeSlider.setValue(MediaBox.getInstance().getVolume()*100);

        volumeSlider.valueProperty().addListener((observable, oldValue, newValue) -> {
            MediaBox.getInstance().changeVolume(newValue.doubleValue()/100);
        });

    }

    public void muteBoxAction(ActionEvent event){
        if(MediaBox.getInstance().isPlaying())
            MediaBox.getInstance().stopMediaBox();
        else
            MediaBox.getInstance().playMediaBox();
    }

    
    public void backAction(ActionEvent event) throws IOException {
        backToHome();
    }

}
