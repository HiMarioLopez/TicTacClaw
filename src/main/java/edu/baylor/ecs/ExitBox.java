package edu.baylor.ecs;

import javafx.scene.text.Font;
import javafx.scene.control.*;

import static edu.baylor.ecs.MasterWindow.maxWidth;

public class ExitBox extends AlertBox{

    private final Button yesButton,noButton;
    private static boolean answer;

    public ExitBox(String title, String message) {
        super(title, message);

        textbox.setFont((Font.font("System", maxWidth / 120)));

        yesButton = new Button("Yes");
        noButton = new Button("No");

        hbox.getChildren().clear();
        hbox.getChildren().addAll(yesButton,noButton);
        }

    public boolean exitDisplay(){
        yesButton.setOnAction(e -> {
            answer = true;
            alertWindow.close();
        });
        noButton.setOnAction(e -> {
            answer = false;
            alertWindow.close();
        });

        alertWindow.setScene(scene);
        alertWindow.showAndWait();

        return answer;
    }
}
