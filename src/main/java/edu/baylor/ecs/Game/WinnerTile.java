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
    private final Text text = new Text();
    private int quadrant;
    boolean hasWon = false;

    /** Author: Brandon Mork. */
    WinnerTile() {
        Rectangle border =
                new Rectangle(SinglePlayer.TILE_SIZE * SIZE_OF_BOARD,
                        SinglePlayer.TILE_SIZE * SIZE_OF_BOARD);
        border.setFill(null);
        border.setStroke(Color.BLACK);

        text.setFont(Font.font(100));
        text.setText(" ");
        setAlignment(Pos.CENTER);

        getChildren().addAll(border, text);

    }

    /** Author: Brandon Mork. */
    void setQuadrant(int quadrant) {
        this.quadrant = quadrant;
    }

    /** Author: Brandon Mork. */
    Text getText() {
        return text;
    }

    /** Author: Brandon Mork. */
    String getValue() {
        return text.getText();
    }

    /** Author: Brandon Mork. */
    boolean isHasWon() {
        return hasWon;
    }

    /** Author: Brandon Mork. */
    void setHasWon() {
        this.hasWon = true;
    }

    /** Author: Brandon Mork. */
    int getQuadrant() {
        return quadrant;
    }
}
