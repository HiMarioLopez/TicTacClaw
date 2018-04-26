package edu.baylor.ecs.Game;

import javafx.geometry.Pos;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.Text;

class WinnerTile extends StackPane {
    private final Text text = new Text();
    private int quadrant;
    boolean hasWon = false;

    WinnerTile() {
        Rectangle border = new Rectangle(SinglePlayer.tileSize * 3
                , SinglePlayer.tileSize * 3);
        border.setFill(null);
        border.setStroke(Color.BLACK);

        text.setFont(Font.font(100));
        text.setText(" ");
        setAlignment(Pos.CENTER);

        getChildren().addAll(border, text);

    }

    void setQuadrant(int quadrant) {
        this.quadrant = quadrant;
    }

    Text getText() {
        return text;
    }

    String getValue() {
        return text.getText();
    }

    boolean isHasWon() {
        return hasWon;
    }

    void setHasWon() {
        this.hasWon = true;
    }

    int getQuadrant() {
        return quadrant;
    }
}
