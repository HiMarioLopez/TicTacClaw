/*
    Name: Brandon Mork
 */
package edu.baylor.ecs.App;

import edu.baylor.ecs.MediaPlayer.MediaBox;
import edu.baylor.ecs.Window.MasterWindow;
import javafx.application.Application;
import javafx.stage.Stage;

/** Author: Brandon Mork. */
public class Main extends Application {


    /** Author: Brandon Mork.
     * @param  */
    public static void main(final String[] args) {
        launch(args);
    }

    @Override
    public final void start(final Stage window) throws Exception {
        //Create MediaBox
        MediaBox mediaBox = MediaBox.getInstance();
        mediaBox.playMediaBox();

        //Create MasterWindow and connect to Login Page
        MasterWindow master = new MasterWindow(window);
        master.connectToLogin();
        master.updateScene();
        master.display();
    }
}
