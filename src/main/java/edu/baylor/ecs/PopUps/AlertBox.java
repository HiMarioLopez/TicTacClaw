package edu.baylor.ecs.PopUps;

import javafx.stage.*;
import javafx.scene.*;
import javafx.scene.layout.*;
import javafx.scene.control.*;
import javafx.geometry.*;

public class AlertBox{


    protected Label textbox;
    protected Button defaultButton;
    protected HBox hbox;

    protected Stage alertWindow;
    protected Scene scene;

    public AlertBox(){}

    public AlertBox(String title, String message){
        alertWindow = new Stage();

        //block user interaction until this is taken care of
        alertWindow.initModality(Modality.APPLICATION_MODAL);
        alertWindow.setMinWidth(350);
        alertWindow.setMinHeight(150);


        alertWindow.setTitle(title);
        textbox = new Label(message);

        defaultButton = new Button("Ok");

        defaultButton.setOnAction(e -> alertWindow.close());


        VBox vbox = new VBox(40);
        vbox.setAlignment(Pos.CENTER);
        vbox.setPadding(new Insets(25,25,25,25));

        hbox = new HBox(25);
        hbox.getChildren().addAll(defaultButton);
        hbox.setAlignment(Pos.CENTER);

        vbox.getChildren().addAll(textbox,hbox);

        scene = new Scene(vbox);
        scene.getStylesheets().add("/css/default.css");
    }

    public void display(){
        alertWindow.setScene(scene);
        alertWindow.showAndWait();
    }
}