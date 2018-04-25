package edu.baylor.ecs;

import javafx.scene.Scene;
import javafx.stage.Screen;
import javafx.stage.Stage;
import javafx.scene.Parent;
import javafx.fxml.FXMLLoader;
import java.io.IOException;

public class MasterWindow {
    private static Stage window;
    private static Scene currentScene;
    private static Parent rootParent;
    protected final static int maxWidth = (int) Screen.getPrimary().getVisualBounds().getWidth();
    protected final static int maxHeight = (int) Screen.getPrimary().getVisualBounds().getHeight();
    //protected MediaBox mediaBox = new MediaBox();

    public MasterWindow(){
        System.out.println("Ceated MasterWindow");
    }

    public MasterWindow(Stage otherWindow){
        window = otherWindow;
        window.setTitle("Tic-Tac-Claw");
    }

    public void closeProgram(Stage window) {
        //boolean result = ExitBox.display("Alert Window", "Do you really want to leave?");
        ExitBox exitBox= new ExitBox("Alert Window", "Do you really want to leave?");
        boolean result = exitBox.exitDisplay();

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

    protected void connectToWin() throws IOException{
        rootParent = FXMLLoader.load(getClass().getResource("/winBox.fxml"));
        currentScene = new Scene(rootParent);
        currentScene.getStylesheets().add("/default.css");
    }

    protected void defaultInit(){
        double height = 600;
        double width = 500;

        getWindow().setMinWidth(width);
        getWindow().setMaxWidth(width);

        getWindow().setMinHeight(height);
        getWindow().setMaxHeight(height);

        getWindow().setWidth(width);
        getWindow().setHeight(height);
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

    public MasterWindow getMaster(){
        return  this;
    }

    public void backToHome() throws IOException {
        this.connectToHome();
        getWindow().setScene(getCurrentScene());
        getWindow().show();
    }

}
