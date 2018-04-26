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

    /** an instance of SinglePlayer.
     */
    private final SinglePlayer singlePlayer;

    /** the text on the Tile.
     */
    private final Text text = new Text();

    /** the x coordinate of the Tile.
     */
    private int x;

    /** the y coordinate of the Tile.
     */
    private int y;

    /** if the Tile has been marked yet.
     */
    private boolean marked = false;

    /** the quadrant the Tile is in.
     */
    private int quadrant;

    /** conversation factor to calculate large quadrant.
     */
    private static final int CONVERSION = 3;

    /** Author: Brandon Mork.
     * @param singleP the SinglePlayer object */
    Tile(final SinglePlayer singleP) {
        this.singlePlayer = singleP;
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
                if (!singlePlayer.isFirstTurn()
                        && (singlePlayer.getPreviousTile().calculateBigQuad()
                        != this.getQuadrant()) && !playAnywhere) {
                    System.out.printf("My previous quad is %d and this quad "
                                    + "is %d%n",
                            singlePlayer.getPreviousTile().calculateBigQuad(),
                            this.getQuadrant());
                    WrongMoveBox wrong = new WrongMoveBox("Wrong Move",
                            "You must play in the correct quadrant "
                                    + "from the last move!");
                    return;
                } else {
                    //take note of the first move
                    if (singlePlayer.isFirstTurn()) {
                        System.out.println("This is my first move!");
                        singlePlayer.setPreviousTile(this);
                        singlePlayer.firstTurnDone();
                    }
                }

                //draw the X
                drawX();

                //make note of the current turn
                singlePlayer.setPreviousTile(this);

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
                        + (singlePlayer.getPreviousTile()
                        .calculateBigQuad() + 1));

                //it is O's turn
            } else if (e.getButton() == MouseButton.PRIMARY
                    && !SinglePlayer.turnX) {

                playAnywhere = calcPlayAnywhere();

                //make sure turn is valid and if previous quad won,go anywhere
                if (!singlePlayer.isFirstTurn()
                        && (singlePlayer.getPreviousTile().calculateBigQuad()
                        != this.getQuadrant())
                        && !playAnywhere) {
                    System.out.println("WRONG MOVE!");
                    System.out.printf("My previous quad is %d and this quad"
                            + " is %d%n",
                            singlePlayer.getPreviousTile().calculateBigQuad(),
                            this.getQuadrant());
                    WrongMoveBox wrong = new WrongMoveBox("Wrong Move",
                            "You must play in the correct quadrant"
                                    + " from the last move!");
                    wrong.display();
                    return;
                }

                //draw the O
                drawO();

                //make note of the current turn
                singlePlayer.setPreviousTile(this);

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
                        + (singlePlayer.getPreviousTile().calculateBigQuad()
                        + 1));
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

    /** Author: Brandon Mork.
     * @param quad the quadrant that the Tile has been placed*/
    void setQuadrant(final int quad) {
        this.quadrant = quad;
    }

    /** Author: Brandon Mork.
     * @return the quadrant the Tile was placed in */
    int getQuadrant() {
        return quadrant;
    }

    /** Author: Brandon Mork.
     * @return the String value of the text */
    String getValue() {
        return text.getText();
    }

    /** Author: Brandon Mork.
     * @return the value of the x coordinate */
    int getX() {
        return x;
    }

    /** Author: Brandon Mork.
     * @param xVal set the x to the new xVal*/
    void setX(final int xVal) {
        this.x = xVal;
    }

    /** Author: Brandon Mork.
     * @return the value of the y coordinate */
    int getY() {
        return y;
    }

    /** Author: Brandon Mork.
     * @param yVal set the y to the new yVal*/
    void setY(final int yVal) {
        this.y = yVal;
    }

    /** Author: Brandon Mork.
     * calculate the quadrant based on the X,Y values
     * @return the large quadrant of the Tile */
    private int calculateBigQuad() {
        return this.x + (this.y * CONVERSION);
    }

    /** Author: Brandon Mork.
     * determine if the quadrant has already been won, return answer
     * @return the answer*/
    private boolean calcPlayAnywhere() {
        boolean answer = false;

        outer:
        if (!singlePlayer.isFirstTurn()) {
            WinBoardIterator iterator
                    = new WinBoardIterator(singlePlayer.getWinBoard());
            while (iterator.hasNext()) {
                WinnerTile temp = iterator.next();
                if (temp.getQuadrant()
                        == singlePlayer.getPreviousTile().calculateBigQuad()) {
                    if (temp.isHasWon()) {
                        System.out.println("He can play anywhere!");
                        answer = true;
                    }
                    break outer;
                }
            }
        }
        return answer;
    }
}
