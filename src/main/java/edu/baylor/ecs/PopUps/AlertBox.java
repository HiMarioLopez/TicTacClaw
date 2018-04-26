/* Author: Brandon Mork.
 * This package will contain all of the popup windows.
 */
package edu.baylor.ecs.PopUps;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;

/** Author: Brandon Mork. */
public class AlertBox {

    /** the message of the alert.
     */
    private final Label textbox;

    /** the HBox with the buttons.
     */
    private final HBox hbox;

    /** the window of the alert.
     */
    private final Stage alertWindow;

    /** the scene of the alert.
     */
    private final Scene scene;

    /** the default height of the alert screen.
     */
    private static final int DEFAULT_HEIGHT = 150;

    /** the default width of the alert screen.
     */
    private static final int DEFAULT_WIDTH = 350;

    /** the default padding for the VBox.
     */
    private static final int PADDING = 25;

    /** the default spacing for VBox.
     */
    private static final int VBOX_SPACING = 40;

    /** the default spacing for HBox.
     */
    private static final int HBOX_SPACING = 40;

    /** Author: Brandon Mork.
     * @param title the title of the alert popup
     * @param message the message of the alert popup */
    AlertBox(final String title, final String message) {
        alertWindow = new Stage();

        //block user interaction until this is taken care of
        alertWindow.initModality(Modality.APPLICATION_MODAL);
        alertWindow.setMinWidth(DEFAULT_WIDTH);
        alertWindow.setMinHeight(DEFAULT_HEIGHT);


        alertWindow.setTitle(title);
        textbox = new Label(message);

        Button defaultButton = new Button("Ok");

        defaultButton.setOnAction(e -> alertWindow.close());


        VBox vbox = new VBox(VBOX_SPACING);
        vbox.setAlignment(Pos.CENTER);
        vbox.setPadding(new Insets(PADDING, PADDING, PADDING, PADDING));

        hbox = new HBox(HBOX_SPACING);
        hbox.getChildren().addAll(defaultButton);
        hbox.setAlignment(Pos.CENTER);

        vbox.getChildren().addAll(textbox, hbox);

        scene = new Scene(vbox);
        scene.getStylesheets().add("/css/default.css");
    }

    /** Author: Brandon Mork.
     * display the window */
    public final void display() {
        alertWindow.setScene(scene);
        alertWindow.showAndWait();
    }

    /** Author: Brandon Mork.
     * @return the textbox*/
    public final Label getTextbox() {
        return textbox;
    }

    /** Author: Brandon Mork.
     * @return the HBox*/
    public final HBox getHbox() {
        return hbox;
    }

    /** Author: Brandon Mork.
     * @return the Stage*/
    public final Stage getAlertWindow() {
        return alertWindow;
    }

    /** Author: Brandon Mork.
     * @return the Scene*/
    public final Scene getScene() {
        return scene;
    }
}
