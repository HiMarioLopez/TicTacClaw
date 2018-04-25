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
    final static int maxWidth = (int) Screen.getPrimary().getVisualBounds().getWidth();
    final static int maxHeight = (int) Screen.getPrimary().getVisualBounds().getHeight();
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
        connect("/singlePlayer.fxml","/singlePlayer.css");
    }

    protected void connectToSetting() throws IOException {
        connect("/settingScreen.fxml","/default.css");
    }

    protected void connectToHome() throws IOException{
        connect("/homeScreen.fxml","/default.css");
    }

    protected void connectToLogin() throws IOException{
        connect("/loginScreen.fxml","/default.css");
    }

    protected void connectHowToPlay() throws IOException{
        connect("/howToPlay.fxml","/default.css");
    }

    protected void defaultInit(){
        double height = 600;
        double width = 500;

        window.setMinWidth(width);
        window.setMaxWidth(width);

        window.setMinHeight(height);
        window.setMaxHeight(height);

        window.setWidth(width);
        window.setHeight(height);
    }


    public static Scene getCurrentScene() {
        return currentScene;
    }

    public static Stage getWindow() {
        return window;
    }


    public void setWindow(Stage other) {
        window = other;
    }

    public void backToHome() throws IOException {
        this.connectToHome();
        window.setScene(getCurrentScene());
        window.show();
    }

    private void connect(String fxml,String css) throws IOException {
        Parent rootParent = FXMLLoader.load(getClass().getResource(fxml));
        currentScene = new Scene(rootParent);
        currentScene.getStylesheets().add(css);
    }

}
