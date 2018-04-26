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
    final Label textbox;
    final HBox hbox;

    final Stage alertWindow;
    final Scene scene;

    /** Author: Brandon Mork. */
    AlertBox(final String title, final String message) {
        alertWindow = new Stage();

        //block user interaction until this is taken care of
        alertWindow.initModality(Modality.APPLICATION_MODAL);
        alertWindow.setMinWidth(350);
        alertWindow.setMinHeight(150);


        alertWindow.setTitle(title);
        textbox = new Label(message);

        Button defaultButton = new Button("Ok");

        defaultButton.setOnAction(e -> alertWindow.close());


        VBox vbox = new VBox(40);
        vbox.setAlignment(Pos.CENTER);
        vbox.setPadding(new Insets(25, 25, 25, 25));

        hbox = new HBox(25);
        hbox.getChildren().addAll(defaultButton);
        hbox.setAlignment(Pos.CENTER);

        vbox.getChildren().addAll(textbox, hbox);

        scene = new Scene(vbox);
        scene.getStylesheets().add("/css/default.css");
    }

    /** Author: Brandon Mork. */
    public final void display() {
        alertWindow.setScene(scene);
        alertWindow.showAndWait();
    }
}