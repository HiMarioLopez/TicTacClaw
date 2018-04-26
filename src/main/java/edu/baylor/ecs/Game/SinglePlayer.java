package edu.baylor.ecs.Game;

import edu.baylor.ecs.PopUps.TieBox;
import edu.baylor.ecs.Window.Controllers.SingleplayerController;
import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.Timeline;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.shape.Line;
import javafx.scene.text.Font;
import javafx.util.Duration;

import java.io.IOException;
import java.net.URL;
import java.util.Objects;
import java.util.ResourceBundle;

/** Author: Brandon Mork. */
public class SinglePlayer extends SingleplayerController
        implements Initializable {

    /** the size of the Tic-Tac-Claw board.
     */
    public static final int SIZE_OF_BOARD = 3;

    /** A 3x3 Block of TileBlocks, which are individual Tic-Tac-Toe boards.
     */
    private final TileBlock[][] board
            = new TileBlock[SIZE_OF_BOARD][SIZE_OF_BOARD];

    /** A 3x3 Block of WinnerTiles.
     */
    private final WinnerTile[][] winBoard
            = new WinnerTile[SIZE_OF_BOARD][SIZE_OF_BOARD];

    /** A copy of the last tile played.
     */
    private Tile previousTile = new Tile(this);

    /** If the first turn has been passed yet.
     */
    private boolean firstTurn = true;

    /** If it is Player 1's turn.
     */
    private static boolean turnX = true;

    /** The default spacing between Tiles.
     */
    private static final double TILE_SPACING = MAX_WIDTH / 120.0;

    /** The default size of the Tiles.
     */
    static final double TILE_SIZE = MAX_WIDTH / 25.0;

    /** The defualt size of the Tile fonts.
     */
    static final double TILE_FONT = MAX_WIDTH / 30.0;

    /** The largest Container, which contains the game and side bars.
     */
    @FXML
    private BorderPane borderpane;

    /** The Pane, which contains the Game.
     */
    @FXML
    private Pane pane;

    /** Tell the user if it is Player 1's turn.
     */
    @FXML
    private Label player1Turn;

    /** Tell the user if it is Player 2's turn.
     */
    @FXML
    private Label player2Turn;

    /** Title of Player 1's side.
     */
    @FXML
    private Label player1Label;

    /** Title of Player 2's side.
     */
    @FXML
    private Label player2Label;

    /** Title of the game.
     */
    @FXML
    private Label titleLabel;

    /** Tell the user where to play.
     */
    @FXML
    private Label quadID;

    /** The sidebar to inform players who's turn.
     */
    @FXML
    private VBox player1VBOX, player2VBOX;

    /** The defualt size of Font of who's turn it is.
     */
    private static final double TURN_FONT = 50.0;

    /** The defualt size of Font of which player it is.
     */
    private static final double PLAYER_FONT = 35.0;

    /** The defualt size of Font of the Title.
     */
    private static final double TITLE_FONT = 25.0;

    /** The defualt size of Font of the Title.
     */
    private static final double VBOX_SPACING = MAX_HEIGHT / 4.0;

    /** The duration of the win animation.
     */
    private static final double SECONDS_TO_PLAY = 1.5;

    /** The thickness of the line of the win animation.
     */
    private static final int STROKE_WIDTH = 5;

    /** The last tile of the 2nd row quadrant number.
     */
    private static final int QUAD_2_HIGH = 5;

    /** The last tile of the 3nd row quadrant number.
     */
    private static final int QUAD_3_HIGH = 8;


    /** Author: Brandon Mork. */
    public SinglePlayer() {
        super();
    }

    @Override
    public final void initialize(final URL location,
                                 final ResourceBundle resources) {

        //dynamically resize the board to your screen
        borderpane.setPrefHeight(MAX_HEIGHT);
        borderpane.setPrefWidth(MAX_WIDTH);

        int paneSize = calcPaneSize();
        pane.setPrefSize(paneSize, paneSize);

        player1VBOX.setSpacing(VBOX_SPACING);
        player2VBOX.setSpacing(VBOX_SPACING);

        player1Turn.setFont(Font.font("System", MAX_WIDTH / TURN_FONT));
        player2Turn.setFont(Font.font("System", MAX_WIDTH / TURN_FONT));

        quadID.setFont(Font.font("System", MAX_WIDTH / TURN_FONT));

        player1Label.setFont(Font.font("System", MAX_WIDTH / PLAYER_FONT));
        player2Label.setFont(Font.font("System", MAX_WIDTH / PLAYER_FONT));

        titleLabel.setFont(Font.font("System", MAX_WIDTH / TITLE_FONT));

        //create the gameboard
        int quad = 0;
        for (int y = 0; y < SIZE_OF_BOARD; y++) {
            for (int x = 0; x < SIZE_OF_BOARD; x++) {
                TileBlock block = new TileBlock(quad);
                board[x][y] = block;

                WinnerTile winner = new WinnerTile();
                winBoard[x][y] = winner;
                quad++;
            }
        }

        //player1 will go first
        player2Turn.setVisible(false);

        getWindow().show();
    }

    /** Author: Brandon Mork. */
    private class TileBlock {

        /** The 3x3 block of Tiles that will make a TileBlock.
         */
        private final Tile[][] block = new Tile[SIZE_OF_BOARD][SIZE_OF_BOARD];

        /** The quadrant that these TileBlock are in.
         */
        private final int quadrant;

        /** The number of Tiles that have been marked in the TileBlock.
         */
        private int filledCount = 0;

        /** Author: Brandon Mork.
         * @param quad the quadrant this TileBlock has been assigned to */
        TileBlock(final int quad) {
            double transX, transY;
            quadrant = quad;

            for (int y = 0; y < block.length; y++) {
                for (int x = 0; x < block.length; x++) {
                    Tile tile = new Tile(SinglePlayer.this);

                    //Calculate x shift
                    transX = (x * TILE_SIZE + TILE_SPACING);
                    if (quadrant % SIZE_OF_BOARD == 1) {
                        transX += (SIZE_OF_BOARD * TILE_SIZE + TILE_SPACING);
                    } else if (quadrant % SIZE_OF_BOARD == 2) {
                        transX += (2 * SIZE_OF_BOARD * TILE_SIZE
                                + TILE_SPACING * 2);
                    }

                    //Calculate y shift
                    transY = ((y * TILE_SIZE) + (quadrant / SIZE_OF_BOARD)
                            * (SIZE_OF_BOARD * TILE_SIZE) + TILE_SPACING);
                    if (quadrant >= SIZE_OF_BOARD && quadrant <= QUAD_2_HIGH) {
                        transY += TILE_SPACING;
                    } else if (quadrant >= (2 * SIZE_OF_BOARD)
                            && quadrant <= QUAD_3_HIGH) {
                        transY += TILE_SPACING * 2;
                    }

                    //Translate the tile and set their coordinate
                    tile.setTranslateX(transX);
                    tile.setTranslateY(transY);
                    tile.setX(x);
                    tile.setY(y);
                    tile.setQuadrant(quadrant);

                    //Add the tile to the block and pane
                    pane.getChildren().add(tile);
                    block[x][y] = tile;
                }
            }

        }

        /** remove the tiles underneath the WinnerTile.
         */
        void removeBlock() {
            for (Tile[] aBlock : block) {
                for (int j = 0; j < block.length; j++) {
                    aBlock[j].setVisible(false);
                }
            }
        }

        /** @return the quadrant of the TileBlock.
         */
        private int getQuadrant() {
            return quadrant;
        }


        /** check for the larger tic tac toe board win.
         * @return if they are the winner, true is winner
         * @param checkMe check this Tile's TileBlock
         */
        private boolean checkGridWin(final Tile checkMe) {
            String mark = checkMe.getValue();

            //check cols
            for (int i = 0; i < block.length; i++) {
                if (!Objects.equals(block[checkMe.getX()][i].getValue(),
                        mark)) {
                    break;
                }
                if (i == block.length - 1) {
                    return true;
                }
            }

            //check rows
            for (int i = 0; i < block.length; i++) {
                if (!Objects.equals(block[i][checkMe.getY()].getValue(),
                        mark)) {
                    break;
                }
                if (i == block.length - 1) {
                    return true;
                }
            }

            //check diag
            if (checkMe.getX() == checkMe.getY()) {
                for (int i = 0; i < block.length; i++) {
                    if (!Objects.equals(block[i][i].getValue(), mark)) {
                        break;
                    }
                    if (i == block.length - 1) {
                        return true;
                    }
                }
            }

            //check anti-diag
            if (checkMe.getX() + checkMe.getY() == block.length - 1) {
                for (int i = 0; i < block.length; i++) {
                    if (!Objects.equals(block[i][block.length - 1 - i]
                            .getValue(), mark)) {
                        break;
                    }
                    if (i == block.length - 1) {
                        return true;
                    }
                }
            }

            return false;
        }

        /** @return the amount of tiles used in the TileBlock.
         */
        private int getFilledCount() {
            return filledCount;
        }

        /** increment the filledCount.
         */
        private void incrementFilledCount() {
            this.filledCount++;
        }
    }

    /** check if the player has won the large, outer tic tac toe board.
     *  play win animation if true.
     *  @param tile the Tile's large quadrant to check
     */
    final void checkBigWin(final Tile tile) {
        int x, y = 0;
        int quad = tile.getQuadrant();
        final double middleRatio = 1.5;
        final double middleRatio2 = 6.0;

        //get the (x,y) of the last quad won
        x = quad % SIZE_OF_BOARD;
        if (quad >= SIZE_OF_BOARD && quad <= QUAD_2_HIGH) {
            y = 1;
        } else if (quad >= (SIZE_OF_BOARD * 2) && quad <= QUAD_3_HIGH) {
            y = 2;
        }

        double startX, startY, endX, endY;

        //check cols
        for (int i = 0; i < winBoard.length; i++) {
            if (!Objects.equals(winBoard[x][i].getValue(), tile.getValue())) {
                break;
            }
            if (i == winBoard.length - 1) {

                startX = (TILE_SIZE * middleRatio)
                        + TILE_SPACING * (x + 1)
                        + (x * SIZE_OF_BOARD * TILE_SIZE);
                startY = (TILE_SIZE * middleRatio) + TILE_SPACING;

                endX = startX;
                endY = startY + (TILE_SIZE * middleRatio2) + (TILE_SPACING * 2);

                System.out.println("WE HAVE AN ACTUAL WINNER!");
                playWinAnimation(startX, startY, endX, endY);

            }
        }

        //check rows
        for (int i = 0; i < winBoard.length; i++) {
            if (!Objects.equals(winBoard[i][y].getValue(), tile.getValue())) {
                break;
            }
            if (i == winBoard.length - 1) {

                startX = (TILE_SIZE * middleRatio) + TILE_SPACING;
                startY = (TILE_SIZE * middleRatio)
                        + TILE_SPACING * (y + 1)
                        + (y * SIZE_OF_BOARD * TILE_SIZE);

                endX = startX + (TILE_SIZE * middleRatio2) + (TILE_SPACING * 2);
                endY = startY;

                System.out.println("WE HAVE AN ACTUAL WINNER!");
                playWinAnimation(startX, startY, endX, endY);

            }
        }

        //check diag
        if (x == y) {
            for (int i = 0; i < winBoard.length; i++) {
                if (!Objects.equals(winBoard[i][i].getValue(),
                        tile.getValue())) {
                    break;
                }
                if (i == winBoard.length - 1) {
                    startX = (TILE_SIZE * middleRatio) + TILE_SPACING;
                    //noinspection SuspiciousNameCombination
                    startY = startX;

                    endX = startX + (TILE_SIZE * middleRatio2)
                            + (TILE_SPACING * 2);
                    //noinspection SuspiciousNameCombination
                    endY = endX;

                    System.out.println("WE HAVE AN ACTUAL WINNER!");
                    playWinAnimation(startX, startY, endX, endY);

                }
            }
        }

        //check anti-diag
        if (x + y == winBoard.length - 1) {
            for (int i = 0; i < winBoard.length; i++) {
                if (!Objects.equals(winBoard[i][winBoard.length - 1 - i]
                        .getValue(), tile.getValue())) {
                    break;
                }
                if (i == winBoard.length - 1) {
                    final double middleRatio3 = 7.5;

                    startX = (TILE_SIZE * middleRatio) + TILE_SPACING;
                    startY = (TILE_SIZE * middleRatio3)
                            + TILE_SPACING * SIZE_OF_BOARD;

                    endX = (TILE_SIZE * middleRatio3) + TILE_SPACING * (x + 1);
                    endY = (TILE_SIZE * middleRatio) + TILE_SPACING;

                    System.out.println("WE HAVE AN ACTUAL WINNER!");
                    playWinAnimation(startX, startY, endX, endY);
                }
            }
        }

        //check for a tie
        for (int i = 0; i < winBoard.length; i++) {
            for (int j = 0; j < winBoard.length; j++) {
                if (!winBoard[i][j].isHasWon()) {
                    System.out.println("No big tie yet");
                    return;
                }
                if (i == 2 && j == 2) {
                    System.out.println("There was a tie!");
                    TieBox alert = new TieBox("Game Over",
                            "The game was a tie!");
                    alert.display();
                    try {
                        this.connectToHome();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    getWindow().setScene(getCurrentScene());
                    getWindow().show();
                }
            }
        }
    }

    /** check if the player was won the inner tic tac toe board.
     * @return return true if they have
     * @param tile check this Tile's TileBlock
     */
    final boolean checkSmallWin(final Tile tile) {
        int quad = tile.getQuadrant();
        boolean answer = false;
        int i, j = 0;
        final int maximumTiles = 8;

        outerLoop:
        for (i = 0; i < board.length; i++) {
            for (j = 0; j < board.length; j++) {
                if (board[i][j].getQuadrant() == quad) {
                    answer = board[i][j].checkGridWin(tile);
                    break outerLoop;
                }
            }
        }

        System.out.println("The incrementing fill is: "
                + board[i][j].getFilledCount());
        //Add the winner tile to the winBoard
        if (answer || board[i][j].getFilledCount() == maximumTiles) {
            WinnerTile temp = new WinnerTile();
            int transX = (int) TILE_SPACING, transY = (int) TILE_SPACING;
            int x = 0, y = 0;       //the (x,y) of the quads

            System.out.println("There is a small winner or it was a Cat!!");

            //Clear the gridBlock in the location of the win
            board[i][j].removeBlock();

            //Calculate x shift
            if (quad % SIZE_OF_BOARD == 1) {
                transX += (SIZE_OF_BOARD * TILE_SIZE + TILE_SPACING);
                x = 1;
            } else if (quad % SIZE_OF_BOARD == 2) {
                transX += (2 * SIZE_OF_BOARD * TILE_SIZE + TILE_SPACING * 2);
                x = 2;
            }

            //Calculate y shift
            if (quad >= SIZE_OF_BOARD && quad <= QUAD_2_HIGH) {
                transY += (SIZE_OF_BOARD * TILE_SIZE + TILE_SPACING);
                y = 1;
            } else if (quad >= (SIZE_OF_BOARD * 2) && quad <= QUAD_3_HIGH) {
                transY += (2 * SIZE_OF_BOARD * TILE_SIZE + TILE_SPACING * 2);
                y = 2;
            }

            //Translate the tile and set their coordinate
            temp.setTranslateX(transX);
            temp.setTranslateY(transY);
            temp.setQuadrant(quad);

            if (answer) {
                temp.getText().setText(tile.getValue());
            } else {
                temp.getText().setText("Tie");
            }

            //add the WinnerTile to the board
            winBoard[x][y] = temp;
            temp.setHasWon();

            System.out.println("The small winner won on quad:"
                    + quad
                    + "\t which is also x:"
                    + x
                    + " y:"
                    + y);

            //Add the tile to the block and pane
            pane.getChildren().add(temp);

            //There is no winner, increment count of TileBlock
        } else {
            board[i][j].incrementFilledCount();
        }
        return answer;
    }

    /** play the winning animation.
     * @param startX the start x coordinate
     * @param startY the start y coordinate
     * @param endX the end x coordinate
     * @param endY the end of y coordinate*/
    private void playWinAnimation(final double startX, final double startY,
                                  final double endX, final double endY) {
        System.out.println("Playing animation");

        //Create the line
        Line line = new Line();
        line.setStartX(startX);
        line.setStartY(startY);
        line.setEndX(startX);
        line.setEndY(startY);
        line.setStrokeWidth(STROKE_WIDTH);
        pane.getChildren().add(line);

        //Animate the line being drawn over the winning tiles
        Timeline timeline = new Timeline();
        timeline.getKeyFrames()
                .add(new KeyFrame(Duration.seconds(SECONDS_TO_PLAY),
                new KeyValue(line.endXProperty(), endX),
                new KeyValue(line.endYProperty(), endY)));
        timeline.play();

        //Open up the WinBox to congratulate the winner
        timeline.setOnFinished(e -> {
            try {
                connectToWinBox();
            } catch (IOException e1) {
                e1.printStackTrace();
            }
            getWindow().setScene(getCurrentScene());
            getWindow().show();
        });
    }

    /** @return who's turn it was when they've won. */
    public static boolean getWinner() {
        return turnX;
    }

    /** @return calculate the size of the Pane. */
    private int calcPaneSize() {
        return (int) ((SIZE_OF_BOARD * SIZE_OF_BOARD * TILE_SIZE)
                + (TILE_SPACING + TILE_SPACING));
    }

    /** exit the game. */
    @FXML
    private void exitGame() {
        closeProgram(getWindow());
    }

    /** go back to home screen.
     * @throws IOException if we cant change screen to Home */
    @FXML
    private void goHome() throws IOException {
        backToHome();
    }

    /** @return the WinBoard object. */
    public final WinnerTile[][] getWinBoard() {
        return winBoard;
    }

    /** @return the previousTile object. */
    public final Tile getPreviousTile() {
        return previousTile;
    }

    /** @param previous set the new previousTile. */
    public final void setPreviousTile(final Tile previous) {
        this.previousTile = previous;
    }

    /** @return determine if the first turn has been taken. */
    public final boolean isFirstTurn() {
        return firstTurn;
    }

    /** the first turn has been passed. */
    public final void firstTurnDone() {
        firstTurn = false;
    }

    /** @return the Label for Player1's turn box. */
    public final Label getPlayer1Turn() {
        return player1Turn;
    }

    /** @return the Label for Player1's turn box. */
    public final Label getPlayer2Turn() {
        return player2Turn;
    }

    /** @return the Label for which quadrant to play. */
    public final Label getQuadID() {
        return quadID;
    }

    /** @return return true if it is X's turn, false otherwise. */
    public static boolean isTurnX() {
        return turnX;
    }

    /** @param turn the update who's turn it is*/
    public static void setTurnX(final boolean turn) {
        SinglePlayer.turnX = turn;
    }
}
