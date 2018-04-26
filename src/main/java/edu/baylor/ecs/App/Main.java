/*
    Authors: Brandon Mork & Mario Lopez
    Game: Tic-Tac-Claw
    Date: 4/26/18
 */

package edu.baylor.ecs.App;

import edu.baylor.ecs.Window.MasterWindow;
import javafx.application.Application;
import javafx.stage.Stage;

/** Author: Brandon Mork. */
public class Main extends Application {


    /** Author: Brandon Mork.
     * @param args the command line arguements */
    public static void main(final String[] args) {
        launch(args);
    }

    @Override
    public final void start(final Stage window) throws Exception {

        //Create MasterWindow and connect to Login Page
        MasterWindow master = new MasterWindow(window);
        master.connectToLogin();
        master.updateScene();
        master.display();
    }
}
