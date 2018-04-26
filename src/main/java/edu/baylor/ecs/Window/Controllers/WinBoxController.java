package edu.baylor.ecs.Window.Controllers;

import edu.baylor.ecs.Window.MasterWindow;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.text.Font;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import static edu.baylor.ecs.Game.SinglePlayer.getWinner;

/** Author: Brandon Mork. */
public class WinBoxController extends MasterWindow implements Initializable {

    /** the Label that will tell who won.
     */
    @FXML
    private Label winnerLabel;

    /** the picture of confetti.
     */
    @FXML
    private ImageView image;

    /** the two buttons on the Window to exit and go back.
     */
    @FXML
    private Button backHomeButton, exitButton;

    /** the confetti picture.
     */
    private final Image confetti = new Image("/pictures/confetti.png");

    /** determines the size of the Label font.
     */
    private static final int FONT_SIZE = 50;


    @Override
    public final void initialize(final URL location,
                                 final ResourceBundle resources) {
        winnerLabel.setFont(Font.font("System", MAX_WIDTH / FONT_SIZE));

        if (getWinner()) {
            winnerLabel.setText("Looks like Player 2 has won!");
        } else {
            winnerLabel.setText("Looks like Player 1 has won!");
        }
        image.setImage(confetti);
    }

    /** Author: Brandon Mork.
     * @throws IOException if there is a problem going to home */
    public final void toHome() throws IOException {
        backToHome();
    }

    /** Author: Brandon Mork. */
    public final void closeProgram() {
        super.closeProgram(getWindow());
    }
}
