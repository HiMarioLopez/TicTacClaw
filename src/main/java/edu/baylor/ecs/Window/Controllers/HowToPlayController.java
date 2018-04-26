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

    /** the Label of the title.
     */
    @FXML
    private Label titleBox;

    /** the TextArea for the instructions.
     */
    @FXML
    private TextArea instructions;

    /** Author: Brandon Mork. */
    public HowToPlayController() { }

    /** determines the size of the Label font.
     */
    private static final int TITLE_FONT_SIZE = 50;

    /** determines the size of the TextArea font.
     */
    private static final int INSTRUCT_FONT_SIZE = 100;

    @Override
    public final void initialize(final URL location,
                                 final ResourceBundle resources) {

        getWindow().setMinWidth(MAX_WIDTH / 2.0);
        getWindow().setMinHeight(MAX_HEIGHT / 2.0);

        getWindow().setMaxHeight(MAX_HEIGHT / 2.0);
        getWindow().setMaxWidth(MAX_WIDTH / 2.0);

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

        titleBox.setFont((Font.font("System", MAX_WIDTH / TITLE_FONT_SIZE)));

        instructions.setFont((Font.font("System",
                MAX_WIDTH / INSTRUCT_FONT_SIZE)));
        instructions.setText(instruct);
        instructions.setWrapText(true);
    }

    /** Author: Brandon Mork.
     * @throws IOException if there is a problem changing windows to home*/
    public final void goBackAction() throws IOException {
        backToHome();
    }
}
