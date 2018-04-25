/*
    Name: Brandon Mork
 */
package edu.baylor.ecs.App;

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

        Media sound = new Media(this.getClass().getResource("/Wolves.m4a").toString());
        MediaPlayer mediaPlayers = new MediaPlayer(sound);
        //mediaPlayers.play();
        //AudioClip note = new AudioClip(this.getClass().getResource("/Wolves.mp3").toString());
        //note.play(100);
        //System.out.println(note.isPlaying());
        //MasterWindow master = new MasterWindow(root, window,  loginScene);


        master = new MasterWindow(window);
        master.connectToLogin();
        master.getWindow().setScene(master.getCurrentScene());
        master.getWindow().show();

        //exit request
        master.getWindow().setOnCloseRequest(e -> {
            //override the close request
            e.consume();
            master.closeProgram(master.getWindow());
            //mediaPlayer.stop();
            //closeProgram();
        });
    }
}
