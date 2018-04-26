package edu.baylor.ecs.Window.Controllers;

import edu.baylor.ecs.Window.MasterWindow;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.text.Font;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

/** Author: Brandon Mork. */
public class HowToPlayController extends MasterWindow implements Initializable {

    /** Author: Brandon Mork. */
    @FXML
    private Label titleBox;

    /** Author: Brandon Mork. */
    @FXML
    private TextArea instructions;

    /** Author: Brandon Mork. */
    public HowToPlayController() { }

    @Override
    public final void initialize(final URL location,
                                 final ResourceBundle resources) {

        getWindow().setMinWidth(maxWidth / 2.0);
        getWindow().setMinHeight(maxHeight / 2.0);

        getWindow().setMaxHeight(maxHeight / 2.0);
        getWindow().setMaxWidth(maxWidth / 2.0);

        String instruct = "The game is similar to Tic-Tac-Toe, only there is a"
                + " slight twist. To start the game, Player 1 places an X on "
                + "any one of the 81 empty squares, and then players alternate"
                + " turns. However, after the initial move, players must play"
                + " the board that mirrors the square from the previous"
                + " player. If the next move is to a board that has already"
                + " been won, then that player may choose an open square "
                + "on any board for that turn. You win boards as usual, but"
                + " you win the game when you win three boards "
                + "together (across rows, columns or diagnols). ";

        titleBox.setFont((Font.font("System", maxWidth / 50.0)));

        instructions.setFont((Font.font("System", maxWidth / 100.0)));
        instructions.setText(instruct);
        instructions.setWrapText(true);
    }

    /** Author: Brandon Mork.
     * @throws IOException*/
    public final void goBackAction() throws IOException {
        backToHome();
    }
}