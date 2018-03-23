package edu.baylor.ecs;

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

public class MasterWindow {
    private Stage window;
    private Scene currentScene,loginScene,homeScreen,settingScreen;
    private Parent root;

    public MasterWindow(){
    }

    public MasterWindow(Parent otherRoot,Stage otherWindow, Scene otherScene){
        root = otherRoot;
        window = otherWindow;
        currentScene = otherScene;
    }

    public void closeProgram(Stage window) {
        boolean result = ExitBox.display("Alert Window", "Do you really want to leave?");
        if (result) {
            System.out.println("Saving files...");
            window.close();
        }
    }


    public Scene getLoginScene() {
        return loginScene;
    }

    public void setLoginScene(Scene loginScene) {
        this.loginScene = loginScene;
    }

    public Scene getHomeScreen() {
        return homeScreen;
    }

    public void setHomeScreen(Scene homeScreen) {
        this.homeScreen = homeScreen;
    }

    public Scene getSettingScreen() {
        return settingScreen;
    }

    public void setSettingScreen(Scene settingScreen) {
        this.settingScreen = settingScreen;
    }

    public void setCurrentScene(Scene currentScene) {
        this.currentScene = currentScene;
    }

    public Scene getCurrentScene() {
        return currentScene;
    }

    public Parent getRoot() {
        return root;
    }

    public void setRoot(Parent root) {
        this.root = root;
    }

    public Stage getWindow() {
        return window;
    }

    public void setWindow(Stage window) {
        this.window = window;
    }
}
