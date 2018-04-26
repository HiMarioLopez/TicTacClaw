package edu.baylor.ecs.Window.Controllers;

import edu.baylor.ecs.Window.MasterWindow;
import javafx.fxml.Initializable;

import java.net.URL;
import java.util.ResourceBundle;


public class SingleplayerController extends MasterWindow implements Initializable{

    protected SingleplayerController(){
        getWindow().setMaximized(true);
        getWindow().setMaxWidth(maxWidth);
        getWindow().setMaxHeight(maxHeight);
        getWindow().setHeight(maxHeight);
        getWindow().setWidth(maxWidth);
    }
    @Override
    public void initialize(URL location, ResourceBundle resources) { }

}