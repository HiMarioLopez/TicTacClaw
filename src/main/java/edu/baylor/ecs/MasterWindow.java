package edu.baylor.ecs;

import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.scene.Parent;
import javafx.fxml.FXMLLoader;
import javafx.scene.media.Media;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;

import java.io.IOException;

public class MasterWindow {
    protected Stage window;
    protected Scene currentScene,loginScene,homeScreen,settingScreen;
    protected Parent root,loginSceneParent,homeSceneParent,settingSceneParent;
    protected MediaBox mediaBox = new MediaBox();

    public MasterWindow(){
        System.out.println("Ceated MasterWindow");
    }

    public MasterWindow(Stage otherWindow){
        window = otherWindow;

        try {
            connectResources();
        } catch (IOException e){e.printStackTrace();}

        window.setTitle("Tic-Tac-Claw");
        window.setMinWidth(450);
        window.setMaxWidth(650);

        window.setMinHeight(500);
        window.setMaxHeight(600);


        window.setWidth(500);
        window.setHeight(550);

    }

    public void closeProgram(Stage window) {
        boolean result = ExitBox.display("Alert Window", "Do you really want to leave?");
        if (result) {
            System.out.println("Saving files...");
            //mediaBox.getMediaPlayer().stop();
            window.close();
        }
    }

    protected void connectResources() throws IOException {
        homeSceneParent = FXMLLoader.load(getClass().getResource("/homeScreen.fxml"));
        homeScreen = new Scene(homeSceneParent);
        homeScreen.getStylesheets().add("/homeScreen.css");

        loginSceneParent = FXMLLoader.load(getClass().getResource("/loginScreen.fxml"));
        loginScene = new Scene(loginSceneParent);
        loginScene.getStylesheets().add("/login.css");

        settingSceneParent = FXMLLoader.load(getClass().getResource("/settingScreen.fxml"));
        settingScreen = new Scene(settingSceneParent);
        settingScreen.getStylesheets().add("/settingScreen.css");

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
