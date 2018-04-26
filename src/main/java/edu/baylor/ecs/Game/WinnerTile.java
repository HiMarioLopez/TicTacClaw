package edu.baylor.ecs.Game;

import javafx.geometry.Pos;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.Text;

import static edu.baylor.ecs.Game.SinglePlayer.SIZE_OF_BOARD;

/** Author: Brandon Mork. */
class WinnerTile extends StackPane {

    /** The letter of the WinnerTile.
     */
    private final Text text = new Text();

    /** The font size of the tile.
     */
    private static final int FONT_SIZE = 100;

    /** The quadrant the tile is in.
     */
    private int quadrant;

    /** If the tile has been won yet.
     */
    private boolean hasWon = false;

    /** Author: Brandon Mork. */
    WinnerTile() {
        Rectangle border =
                new Rectangle(SinglePlayer.TILE_SIZE * SIZE_OF_BOARD,
                        SinglePlayer.TILE_SIZE * SIZE_OF_BOARD);
        border.setFill(null);
        border.setStroke(Color.BLACK);

        text.setFont(Font.font(FONT_SIZE));
        text.setText(" ");
        setAlignment(Pos.CENTER);

        getChildren().addAll(border, text);

    }

    /** Author: Brandon Mork.
     * @param quad set the quadrant to this */
    void setQuadrant(final int quad) {
        this.quadrant = quad;
    }

    /** Author: Brandon Mork.
     * @return the current text Object of the tile */
    Text getText() {
        return text;
    }

    /** Author: Brandon Mork.
     * @return the String of the text*/
    String getValue() {
        return text.getText();
    }

    /** Author: Brandon Mork.
     * @return if the tile has been won yet*/
    boolean isHasWon() {
        return hasWon;
    }

    /** Author: Brandon Mork. */
    void setHasWon() {
        this.hasWon = true;
    }

    /** Author: Brandon Mork.
     * @return the quadrant the tile is in */
    int getQuadrant() {
        return quadrant;
    }
}
