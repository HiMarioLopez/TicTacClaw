package edu.baylor.ecs.PopUps;

import javafx.scene.control.Button;
import javafx.scene.text.Font;

import static edu.baylor.ecs.Window.MasterWindow.maxWidth;

/** Author: Brandon Mork. */
public class ExitBox extends AlertBox {

    private final Button yesButton, noButton;
    private static boolean answer;

    /** Author: Brandon Mork. */
    public ExitBox(final String title, final String message) {
        super(title, message);

        textbox.setFont((Font.font("System", maxWidth / 120)));

        yesButton = new Button("Yes");
        noButton = new Button("No");

        hbox.getChildren().clear();
        hbox.getChildren().addAll(yesButton, noButton);
        }

    /** Author: Brandon Mork. */
    public boolean exitDisplay() {
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
