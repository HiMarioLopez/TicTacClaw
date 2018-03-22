package edu.baylor.ecs;

import javafx.stage.*;
import javafx.scene.*;
import javafx.scene.layout.*;
import javafx.scene.control.*;
import javafx.geometry.*;


public class ExitBox {

    static boolean answer;

    public static boolean display (String title, String message){
        Stage alertWindow = new Stage();

        //block user interaction until this is taken care of
        alertWindow.initModality(Modality.APPLICATION_MODAL);
        alertWindow.setTitle(title);
        alertWindow.setMinWidth(250);

        Label label = new Label(message);

        //Create buttons
        Button yesButton = new Button("Yes");
        Button noButton = new Button("No");

        yesButton.setOnAction(e -> {
            answer = true;
            alertWindow.close();
        });
        noButton.setOnAction(e -> {
            answer = false;
            alertWindow.close();
        });

        VBox layout = new VBox(10);
        layout.getChildren().addAll(label,yesButton,noButton);
        layout.setAlignment(Pos.CENTER);

        Scene scene = new Scene(layout);
        alertWindow.setScene(scene);
        alertWindow.showAndWait();

        return answer;
    }
}
