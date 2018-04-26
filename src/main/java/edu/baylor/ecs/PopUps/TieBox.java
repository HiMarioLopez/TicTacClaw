package edu.baylor.ecs.PopUps;

import javafx.scene.text.Font;

import static edu.baylor.ecs.Window.MasterWindow.MAX_WIDTH;

/** Author: Brandon Mork. */
public class TieBox extends AlertBox {
    public TieBox(final String title, final String message) {
        super(title, message);

        textbox.setFont((Font.font("System", MAX_WIDTH / 50)));
    }
}
