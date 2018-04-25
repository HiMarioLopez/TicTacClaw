package edu.baylor.ecs;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.text.Font;
import javafx.event.ActionEvent;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class howToPlayController extends MasterWindow implements Initializable {

    @FXML
    private Label titleBox;

    @FXML
    private TextArea instructions;

    public howToPlayController() { }

    @Override
    public void initialize(URL location, ResourceBundle resources) {

        getWindow().setMinWidth(maxWidth/2);
        getWindow().setMinHeight(maxHeight/2);

        getWindow().setMaxHeight(maxHeight/2);
        getWindow().setMaxWidth(maxWidth/2);

        String instruct = "The game is similar to Tic-Tac-Toe, only there is a slight twist. To start the game, " +
                        "Player 1 places an X on any one of the 81 empty squares, and then players alternate turns. " +
                        "However, after the initial move, players must play the board that mirrors the square from the previous player. " +
                        "If the next move is to a board that has already been won, then that player may choose an open square " +
                        "on any board for that turn. You win boards as usual, but you win the game when you win three boards " +
                        "together (across rows, columns or diagnols). ";

        titleBox.setFont((Font.font("System", maxWidth / 50)));

        instructions.setFont((Font.font("System", maxWidth / 100)));
        instructions.setText(instruct);
        instructions.setWrapText(true);
    }

    public void goBackAction(ActionEvent event) throws IOException {
        backToHome();
    }
}