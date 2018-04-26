/** Author: Brandon Mork.
 * This package will contain all of the popup windows.
 */
package edu.baylor.ecs.PopUps;

import javafx.scene.text.Font;

import static edu.baylor.ecs.Window.MasterWindow.MAX_WIDTH;

/** Author: Brandon Mork. */
public class WrongMoveBox extends AlertBox {

    /** the default Font size for the message.
     */
    private static final int FONT_SIZE = 120;

    /** Author: Brandon Mork.
     * @param title the title for the ExitBox
     * @param message the message for the ExitBox*/
    public WrongMoveBox(final String title, final String message) {
        super(title, message);

        getTextbox().setFont((Font.font("System", MAX_WIDTH / FONT_SIZE)));
    }
}
