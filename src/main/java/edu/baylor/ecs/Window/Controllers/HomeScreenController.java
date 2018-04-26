package edu.baylor.ecs.Window.Controllers;

import edu.baylor.ecs.Window.MasterWindow;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.stage.Stage;
import javafx.scene.Node;
import javafx.event.ActionEvent;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

/** Author: Brandon Mork. */
public class HomeScreenController extends MasterWindow
        implements Initializable {

    /** the Buttons for the HomeScreen.
     */
    @FXML
    private Button coopButton, howToPlayButton, settingButton, exitButton;


    /** Author: Brandon Mork. */
    public HomeScreenController() { }

    @Override
    public final void initialize(final URL location,
                                 final ResourceBundle resources) {
        defaultInit();
    }

    /** Author: Brandon Mork.
     * Change window to the Game
     * @param event the user clicks the button
     * @throws IOException if there is a problem changing to next scene */
    public final void coopAction(final ActionEvent event) throws IOException {
        System.out.println("User press Co-op");

        this.connectToSingle();
        setWindow((Stage) ((Node) event.getSource()).getScene().getWindow());
        getWindow().setScene(getCurrentScene());
        getWindow().show();
    }

    /** Author: Brandon Mork.
     * Change window to the instructions
     * @param event the user clicks the button
     * @throws IOException if there is a problem changing to next scene */
    public final void howToPlayAction(final ActionEvent event)
            throws IOException {
        System.out.println("User press Multiplayer");

        this.connectHowToPlay();
        setWindow((Stage) ((Node) event.getSource()).getScene().getWindow());
        getWindow().setScene(getCurrentScene());
        getWindow().show();
    }

    /** Author: Brandon Mork.
     * Change window to the settings screen
     * @param event the user clicks the button
     * @throws IOException if there is a problem changing to next scene */
    public final void settingAction(final ActionEvent event)
            throws IOException {
        System.out.println("User press Settings");

        this.connectToSetting();
        setWindow((Stage) ((Node) event.getSource()).getScene().getWindow());
        getWindow().setScene(getCurrentScene());
        getWindow().show();
    }

    /** Author: Brandon Mork.
     * close the program */
    public final void closeProgram() {
        super.closeProgram(getWindow());
    }

}
