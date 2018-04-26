package edu.baylor.ecs.Window.Controllers;

import edu.baylor.ecs.Window.MasterWindow;
import javafx.fxml.Initializable;
import javafx.scene.text.Font;


import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import static edu.baylor.ecs.Game.SinglePlayer.getWinner;


public class SingleplayerController extends MasterWindow implements Initializable{


    public SingleplayerController(){
        getWindow().setMaximized(true);
        getWindow().setMaxWidth(maxWidth);
        getWindow().setMaxHeight(maxHeight);
        getWindow().setHeight(maxHeight);
        getWindow().setWidth(maxWidth);
    }
    @Override
    public void initialize(URL location, ResourceBundle resources) { }

    public void ToHome() throws IOException{
        backToHome();
    }

    public void closeProgram(){
        super.closeProgram(getWindow());
    }
}