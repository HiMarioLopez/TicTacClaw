package edu.baylor.ecs.Window.Controllers;

import edu.baylor.ecs.MediaPlayer.MediaBox;
import edu.baylor.ecs.Window.MasterWindow;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Slider;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

/** Author: Brandon Mork. */
public class SettingScreenController extends MasterWindow
        implements Initializable {

    /** the volume slider.
     */
    @FXML
    private Slider volumeSlider;

    /** the checkbox that will play and pause music.
     */
    @FXML
    private CheckBox muteBox;

    /** the Button to go back to Home.
     */
    @FXML
    private Button backButton;

    /** the default height of the setting screen.
     */
    private static final int DEFAULT_HEIGHT = 700;

    /** the default width of the setting screen.
     */
    private static final int DEFAULT_WIDTH = 700;

    /** the multiplier to affect the mediaplayer.
     */
    private static final int MEDIA_MULTIPLIER = 100;


    /** Author: Brandon Mork. */
    public SettingScreenController() {
        System.out.println("Created settings");
    }

    @Override
    public final void initialize(final URL location,
                                 final ResourceBundle resources) {
        getWindow().setMinHeight(DEFAULT_HEIGHT);
        getWindow().setMaxHeight(DEFAULT_HEIGHT);

        getWindow().setMinWidth(DEFAULT_WIDTH);
        getWindow().setMaxWidth(DEFAULT_WIDTH);

        volumeSlider.setValue(MediaBox.getInstance().getVolume()
                * MEDIA_MULTIPLIER);

        volumeSlider.valueProperty()
                .addListener((observable, oldValue, newValue)
                        -> MediaBox.getInstance()
                        .changeVolume(newValue.doubleValue()
                                / MEDIA_MULTIPLIER));
    }

    /** Author: Brandon Mork. */
    public final void muteBoxAction() {
        if (MediaBox.getInstance().isPlaying()) {
            MediaBox.getInstance().stopMediaBox();
        } else {
            MediaBox.getInstance().playMediaBox();
        }
    }

    /** Author: Brandon Mork.
     * @throws IOException if there is an error going back to home */
    public final void backAction() throws IOException {
        backToHome();
    }

}
