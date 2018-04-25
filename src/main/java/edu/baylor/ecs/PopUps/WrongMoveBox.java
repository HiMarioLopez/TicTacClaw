package edu.baylor.ecs.PopUps;

import javafx.scene.text.Font;

import static edu.baylor.ecs.Window.MasterWindow.maxWidth;

public class WrongMoveBox extends AlertBox {
    public WrongMoveBox(String title, String message){
        super(title,message);

        textbox.setFont((Font.font("System",maxWidth/120)));
        display();
    }
}
