package edu.baylor.ecs;

import javafx.stage.*;
import javafx.scene.*;
import javafx.scene.layout.*;
import javafx.scene.control.*;
import javafx.geometry.*;


public class WrongMoveBox {

    public static void display (String title, String message){
        Stage alertWindow = new Stage();

        //block user interaction until this is taken care of
        alertWindow.initModality(Modality.APPLICATION_MODAL);
        alertWindow.setTitle(title);
        alertWindow.setMinWidth(350);
        alertWindow.setMinHeight(150);

        Label label = new Label(message);

        //Create buttons
        Button okButton = new Button("Ok");

        okButton.setOnAction(e -> {
            alertWindow.close();
        });


        VBox layout = new VBox(10);
        layout.getChildren().addAll(label,okButton);
        layout.setAlignment(Pos.CENTER);

        Scene scene = new Scene(layout);
        alertWindow.setScene(scene);
        alertWindow.showAndWait();

    }
}