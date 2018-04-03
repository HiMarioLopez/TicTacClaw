package edu.baylor.ecs;

import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.scene.Parent;
import javafx.fxml.FXMLLoader;
import java.io.IOException;

public class MasterWindow {
    private static Stage window;
    private static Scene currentScene;
    private static Parent rootParent;
    //protected MediaBox mediaBox = new MediaBox();

    public MasterWindow(){
        System.out.println("Ceated MasterWindow");
    }

    public MasterWindow(Stage otherWindow){
        window = otherWindow;

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

    protected void connectToSingle() throws IOException {
        rootParent = FXMLLoader.load(getClass().getResource("/singlePlayer.fxml"));
        currentScene = new Scene(rootParent);
        currentScene.getStylesheets().add("/singlePlayer.css");
    }

    protected void connectToSetting() throws IOException {
        rootParent = FXMLLoader.load(getClass().getResource("/settingScreen.fxml"));
        currentScene = new Scene(rootParent);
        currentScene.getStylesheets().add("/settingScreen.css");
    }

    protected void connectToHome() throws IOException{
        rootParent = FXMLLoader.load(getClass().getResource("/homeScreen.fxml"));
        currentScene = new Scene(rootParent);
        currentScene.getStylesheets().add("/homeScreen.css");
    }

    protected void connectToLogin() throws IOException{
        rootParent = FXMLLoader.load(getClass().getResource("/loginScreen.fxml"));
        currentScene = new Scene(rootParent);
        currentScene.getStylesheets().add("/login.css");
    }





    public static Scene getCurrentScene() {
        return currentScene;
    }

    public void setCurrentScene(Scene currentScene) {
        this.currentScene = currentScene;
    }



    public static Stage getWindow() {
        return window;
    }

    public void setWindow(Stage window) {
        this.window = window;
    }

}
