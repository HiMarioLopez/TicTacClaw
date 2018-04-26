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


public class WinBoxController extends MasterWindow implements Initializable{

    @FXML
    private Label winnerLabel;

    @FXML
    private ImageView image;

    @FXML
    private Button backHomeButton,exitButton;

    private final Image confetti = new Image("/pictures/confetti.png");


    @Override
    public void initialize(URL location, ResourceBundle resources) {
        winnerLabel.setFont(Font.font("System",maxWidth/50));

        if(getWinner())
            winnerLabel.setText("Looks like Player 2 has won!");
        else
            winnerLabel.setText("Looks like Player 1 has won!");

        image.setImage(confetti);
    }

    public void toHome() throws IOException{
        backToHome();
    }

    public void closeProgram(){
        super.closeProgram(getWindow());
    }
}
