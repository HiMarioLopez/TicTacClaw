package edu.baylor.ecs;

import javafx.scene.text.Font;
import javafx.stage.*;
import javafx.scene.*;
import javafx.scene.layout.*;
import javafx.scene.control.*;
import javafx.geometry.*;

import static edu.baylor.ecs.MasterWindow.maxWidth;

public class WrongMoveBox extends AlertBox{
    public WrongMoveBox(String title, String message){
        super(title,message);

        textbox.setFont((Font.font("System",maxWidth/120)));
        display();
    }
}
