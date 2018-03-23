package edu.baylor.ecs;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.media.AudioClip;
import javafx.stage.Stage;
import javafx.scene.Parent;
import javafx.fxml.FXMLLoader;
import javafx.scene.media.Media;
import java.io.File;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import java.io.File;

public abstract class UI{

    protected Scene homeScreen,settingScreen;
    protected Stage window;

    public void closeProgram(Stage window) {
        boolean result = ExitBox.display("Alert Window", "Do you really want to leave?");
        if (result) {
            System.out.println("Saving files...");
            window.close();
        }
    }


}
