package edu.baylor.ecs;

import javafx.stage.*;
import javafx.scene.*;
import javafx.scene.layout.*;
import javafx.scene.control.*;
import javafx.geometry.*;

import java.awt.event.ActionEvent;
import java.io.IOException;

public class WinBox {
    private static boolean toHome = false;

    public static void display(MasterWindow master,boolean turnX){
        Stage winWindow = new Stage();
        String message;

        winWindow.initModality(Modality.APPLICATION_MODAL);
        winWindow.setTitle("We have a Winner!");
        winWindow.setMinWidth(250);

        if(turnX)
            message = "The Host has won!";
        else
            message = "The Guest has won!";

        Label label = new Label(message);

        Button homeButton = new Button("Go Back To Home");

        homeButton.setOnAction(e -> {
            try {
                master.connectToHome();
            } catch (IOException ex) { ex.printStackTrace();}
            master.setWindow((Stage)((Node)e.getSource()).getScene().getWindow());
            master.getWindow().setScene(master.getCurrentScene());
            winWindow.close();
        });

        VBox layout = new VBox(10);
        layout.getChildren().addAll(label,homeButton);
        layout.setAlignment(Pos.CENTER);

        Scene scene = new Scene(layout);
        winWindow.setScene(scene);
        winWindow.showAndWait();
    }

    //make a new winBox class with fxml and make it look nice?
    //add an exit button
    //possibly add balloon win animation?
}
