/*
    Name: Brandon Mork
 */
package edu.baylor.ecs.App;

import edu.baylor.ecs.MediaPlayer.MediaBox;
import edu.baylor.ecs.Window.MasterWindow;
import javafx.application.Application;
import javafx.stage.Stage;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;

public class Main extends Application {


    private MasterWindow master;

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage window) throws Exception {
        //Create MediaBox
        MediaBox mediaBox = MediaBox.getInstance();
        mediaBox.playMediaBox();

        //Create MasterWindow and connect to Login Page
        master = new MasterWindow(window);
        master.connectToLogin();
        master.updateScene();
        master.display();
    }
}
