package edu.baylor.ecs.PopUps;

import javafx.scene.text.Font;

import static edu.baylor.ecs.Window.MasterWindow.MAX_WIDTH;

/** Author: Brandon Mork. */
public class WrongMoveBox extends AlertBox {
    public WrongMoveBox(final String title, final String message) {
        super(title, message);

        textbox.setFont((Font.font("System", MAX_WIDTH / 120.0)));
    }
}
