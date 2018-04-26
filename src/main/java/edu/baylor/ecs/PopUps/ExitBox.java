/** Author: Brandon Mork.
 * This package will contain all of the popup windows.
 */
package edu.baylor.ecs.PopUps;

import javafx.scene.control.Button;
import javafx.scene.text.Font;

import static edu.baylor.ecs.Window.MasterWindow.MAX_WIDTH;

/** Author: Brandon Mork. */
public class ExitBox extends AlertBox {

    /** the Yes and No Buttons.
     */
    private final Button yesButton, noButton;

    /** the result of the choice.
     */
    private static boolean answer;

    /** the default Font size for the message.
     */
    private static final int FONT_SIZE = 120;

    /** Author: Brandon Mork.
     * @param title the title for the ExitBox
     * @param message the message for the ExitBox*/
    public ExitBox(final String title, final String message) {
        super(title, message);

        getTextbox().setFont((Font.font("System", MAX_WIDTH / FONT_SIZE)));

        yesButton = new Button("Yes");
        noButton = new Button("No");

        getHbox().getChildren().clear();
        getHbox().getChildren().addAll(yesButton, noButton);
        }

    /** Author: Brandon Mork.
     * @return if the window should close or not */
    public final boolean exitDisplay() {
        yesButton.setOnAction(e -> {
            answer = true;
            getAlertWindow().close();
        });
        noButton.setOnAction(e -> {
            answer = false;
            getAlertWindow().close();
        });

        getAlertWindow().setScene(getScene());
        getAlertWindow().showAndWait();

        return answer;
    }
}
