package edu.baylor.ecs.Game;

import edu.baylor.ecs.PopUps.WrongMoveBox;
import javafx.geometry.Pos;
import javafx.scene.input.MouseButton;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.Text;

/** Author: Brandon Mork. */
class Tile extends StackPane {
    private final SinglePlayer singlePlayer;
    private final Text text = new Text();
    private int x;
    private int y;
    private boolean marked = false;
    private int quadrant;

    /** Author: Brandon Mork. */
    Tile(final SinglePlayer singlePlayer) {
        this.singlePlayer = singlePlayer;
        //Create the tile appearance and add it to the pane
        Rectangle border = new Rectangle(SinglePlayer.TILE_SIZE,
                                         SinglePlayer.TILE_SIZE);
        border.setFill(null);
        border.setStroke(Color.BLACK);

        text.setFont(Font.font(SinglePlayer.TILE_FONT));
        setAlignment(Pos.CENTER);

        getChildren().addAll(border, text);

        //user has clicked on a tile
        setOnMouseClicked(e -> {

            //cant play on already played tile
            if (marked) {
                return;
            }

            //has the quadrant already been won?
            boolean playAnywhere;

            //it is X's turn
            if (e.getButton() == MouseButton.PRIMARY && SinglePlayer.turnX) {

                playAnywhere = calcPlayAnywhere();

                //make sure turn is valid and if previous quad won,go anywhere
                if (!singlePlayer.firstTurn
                        && (singlePlayer.previousTile.calculateBigQuad()
                        != this.getQuadrant()) && !playAnywhere) {
                    System.out.printf("My previous quad is %d and this quad "
                                    + "is %d%n",
                            singlePlayer.previousTile.calculateBigQuad(),
                            this.getQuadrant());
                    WrongMoveBox wrong = new WrongMoveBox("Wrong Move",
                            "You must play in the correct quadrant "
                                    + "from the last move!");
                    return;
                } else {
                    //take note of the first move
                    if (singlePlayer.firstTurn) {
                        System.out.println("This is my first move!");
                        singlePlayer.previousTile = this;
                        singlePlayer.firstTurn = false;
                    }
                }

                //draw the X
                drawX();

                //make not of the current turn
                singlePlayer.previousTile = this;

                //check if X has won the TileBlock
                if (singlePlayer.checkSmallWin(this)) {
                    System.out.println("I must check big box!");
                    singlePlayer.checkBigWin(this);
                }

                //change turn to O's
                SinglePlayer.turnX = false;
                singlePlayer.player1Turn.setVisible(false);
                singlePlayer.player2Turn.setVisible(true);
                singlePlayer.quadID.setText("Quadrant number to play: "
                        + (singlePlayer.previousTile.calculateBigQuad() + 1));

                //it is O's turn
            } else if (e.getButton() == MouseButton.PRIMARY
                    && !SinglePlayer.turnX) {

                playAnywhere = calcPlayAnywhere();

                //make sure turn is valid and if previous quad won,go anywhere
                if (!singlePlayer.firstTurn
                        && (singlePlayer.previousTile.calculateBigQuad()
                        != this.getQuadrant())
                        && !playAnywhere) {
                    System.out.println("WRONG MOVE!");
                    System.out.printf("My previous quad is %d and this quad"
                            + " is %d%n",
                            singlePlayer.previousTile.calculateBigQuad(),
                            this.getQuadrant());
                    WrongMoveBox wrong = new WrongMoveBox("Wrong Move",
                            "You must play in the correct quadrant"
                                    + " from the last move!");
                    wrong.display();
                    return;
                }

                //draw the O
                drawO();

                //make not of the current turn
                singlePlayer.previousTile = this;

                //check if O has won the TileBlock
                if (singlePlayer.checkSmallWin(this)) {
                    System.out.println("I must check big box!");
                    singlePlayer.checkBigWin(this);
                }

                //change to X's turn
                SinglePlayer.turnX = true;
                singlePlayer.player2Turn.setVisible(false);
                singlePlayer.player1Turn.setVisible(true);
                singlePlayer.quadID.setText("Quadrant number to play: "
                        + (singlePlayer.previousTile.calculateBigQuad() + 1));
            }
        });
    }

    /** Author: Brandon Mork.
     *  set the text of the tile to "X" */
    private void drawX() {
        System.out.println("You touched x:" + x + " y:" + y);
        text.setText("X");
        marked = true;
    }

    /** Author: Brandon Mork.
     *  set the text of the tile to "O" */
    private void drawO() {
        System.out.println("You touched x:" + x + " y:" + y);
        text.setText("O");
        marked = true;
    }

    /** Author: Brandon Mork. */
    void setQuadrant(int quadrant) {
        this.quadrant = quadrant;
    }

    /** Author: Brandon Mork. */
    int getQuadrant() {
        return quadrant;
    }

    /** Author: Brandon Mork. */
    String getValue() {
        return text.getText();
    }

    /** Author: Brandon Mork. */
    int getX() {
        return x;
    }

    /** Author: Brandon Mork. */
    void setX(int x) {
        this.x = x;
    }

    /** Author: Brandon Mork. */
    int getY() {
        return y;
    }

    /** Author: Brandon Mork. */
    void setY(int y) {
        this.y = y;
    }

    /** Author: Brandon Mork.
     * calculate the quadrant based on the X,Y values */
    private int calculateBigQuad() {
        return this.x + (this.y * 3);
    }

    /** Author: Brandon Mork.
     * determine if the quadrant has already been won, return answer */
    private boolean calcPlayAnywhere() {
        boolean answer = false;

        outer:
        if (!singlePlayer.firstTurn) {
            for (WinnerTile row [] : singlePlayer.winBoard) {
                for (WinnerTile col : row) {
                    //find the WinnerTile with the same quadrants
                    if (col.getQuadrant()
                            == singlePlayer.previousTile.calculateBigQuad()) {
                        //check if the tile has already been won
                        if (col.isHasWon()) {
                            answer = true;
                        }
                        break outer;
                    }
                }
            }
        }
        return answer;
    }
}
