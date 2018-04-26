package edu.baylor.ecs.Window;

import edu.baylor.ecs.MediaPlayer.MediaBox;
import edu.baylor.ecs.PopUps.ExitBox;
import javafx.scene.Scene;
import javafx.stage.Screen;
import javafx.stage.Stage;
import javafx.scene.Parent;
import javafx.fxml.FXMLLoader;
import java.io.IOException;

public class MasterWindow {
    private static Stage window;
    private static Scene currentScene;
    public final static int maxWidth = (int) Screen.getPrimary().getVisualBounds().getWidth();
    public final static int maxHeight = (int) Screen.getPrimary().getVisualBounds().getHeight();

    public MasterWindow(){
        System.out.println("Ceated MasterWindow");
        window.setOnCloseRequest(e -> {
            e.consume();
            closeProgram(window);
        });
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
            MediaBox.getInstance().stopMediaBox();
            window.close();
        }

    }

    protected void connectToSingle() throws IOException {
        connect("/fxml/singlePlayer.fxml", "/css/singlePlayer.css");
    }

    protected void connectToSetting() throws IOException {
        connect("/fxml/settingScreen.fxml", "/css/default.css");
    }

    protected void connectToHome() throws IOException{
        connect("/fxml/homeScreen.fxml", "/css/default.css");
    }

    public void connectToLogin() throws IOException{
        connect("/fxml/loginScreen.fxml", "/css/default.css");
    }

    protected void connectHowToPlay() throws IOException{
        connect("/fxml/howToPlay.fxml", "/css/default.css");
    }

    protected void connectToWinBox()throws IOException{
        connect("/fxml/winBox.fxml", "/css/default.css");
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


    protected static Scene getCurrentScene() {
        return currentScene;
    }

    protected static Stage getWindow() {
        return window;
    }

    public void updateScene() {
        window.setScene(currentScene);
    }

    public void display(){
        window.show();
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
        Parent rootParent = FXMLLoader.load(MasterWindow.class.getResource(fxml));
        currentScene = new Scene(rootParent);
        currentScene.getStylesheets().add(css);
    }

}
