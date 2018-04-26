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
public class SinglePlayer extends SingleplayerController implements Initializable {
    public final static int SIZE_OF_BOARD = 3;
    private final TileBlock[][] board = new TileBlock[SIZE_OF_BOARD][SIZE_OF_BOARD];
    final WinnerTile[][] winBoard = new WinnerTile[SIZE_OF_BOARD][SIZE_OF_BOARD];
    Tile previousTile = new Tile(this);
    boolean firstTurn = true;
    static boolean turnX = true;
    private final static double SPACING = MAX_WIDTH / 120.0;
    static final double TILE_SIZE = MAX_WIDTH / 25.0;
    static final double TILE_FONT = MAX_WIDTH / 30.0;

    @FXML
    private BorderPane borderpane;

    @FXML
    private Pane pane;

    @FXML
    Label player1Turn;
    @FXML
    Label player2Turn;
    @FXML
    private Label player1Label;
    @FXML
    private Label player2Label;
    @FXML
    private Label titleLabel;
    @FXML
    Label quadID;

    @FXML
    private VBox player1VBOX, player2VBOX;

    /** Author: Brandon Mork. */
    public SinglePlayer() {
        super();
    }

    @Override
    public final void initialize(URL location, ResourceBundle resources) {

        //dynamically resize the board to your screen
        borderpane.setPrefHeight(MAX_HEIGHT);
        borderpane.setPrefWidth(MAX_WIDTH);

        int paneSize = calcPaneSize();
        pane.setPrefSize(paneSize, paneSize);

        player1VBOX.setSpacing(MAX_HEIGHT / 4.0);
        player2VBOX.setSpacing(MAX_HEIGHT / 4.0);

        player1Turn.setFont(Font.font("System", MAX_WIDTH / 50.0));
        player2Turn.setFont(Font.font("System", MAX_WIDTH / 50.0));

        quadID.setFont(Font.font("System", MAX_WIDTH / 50.0));

        player1Label.setFont(Font.font("System", MAX_WIDTH / 35.0));
        player2Label.setFont(Font.font("System", MAX_WIDTH / 35.0));

        titleLabel.setFont(Font.font("System", MAX_WIDTH / 25.0));

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
        private final Tile[][] block = new Tile[SIZE_OF_BOARD][SIZE_OF_BOARD];
        private final int quadrant;
        private int filledCount = 0;

        /** Author: Brandon Mork. */
        TileBlock(final int quad) {
            double transX, transY;
            quadrant = quad;

            for (int y = 0; y < block.length; y++) {
                for (int x = 0; x < block.length; x++) {
                    Tile tile = new Tile(SinglePlayer.this);

                    //Calculate x shift
                    transX = (x * TILE_SIZE + SPACING);
                    if (quadrant % 3 == 1) {
                        transX += (3 * TILE_SIZE + SPACING);
                    } else if (quadrant % 3 == 2) {
                        transX += (6 * TILE_SIZE + SPACING * 2);
                    }

                    //Calculate y shift
                    transY = ((y * TILE_SIZE) + (quadrant / 3) * (3.0 * TILE_SIZE) + SPACING);
                    if (quadrant >= 3 && quadrant <= 5) {
                        transY += SPACING;
                    } else if (quadrant >= 6 && quadrant <= 8) {
                        transY += SPACING * 2;
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

        //remove the tiles underneath the WinnerTile
        void removeBlock() {
            for (Tile[] aBlock : block) {
                for (int j = 0; j < block.length; j++) {
                    aBlock[j].setVisible(false);
                }
            }
        }

        private int getQuadrant() {
            return quadrant;
        }

        //return true if they are the winner
        private boolean checkGridWin(Tile checkMe) {
            String mark = checkMe.getValue();

            //check cols
            for (int i = 0; i < block.length; i++) {
                if (!Objects.equals(block[checkMe.getX()][i].getValue(), mark)) {
                    break;
                }
                if (i == block.length - 1) {
                    return true;
                }
            }

            //check rows
            for (int i = 0; i < block.length; i++) {
                if (!Objects.equals(block[i][checkMe.getY()].getValue(), mark)) {
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
                    if (!Objects.equals(block[i][block.length - 1 - i].getValue(), mark)) {
                        break;
                    }
                    if (i == block.length - 1) {
                        return true;
                    }
                }
            }

            return false;
        }

        private int getFilledCount() {
            return filledCount;
        }

        private void incrementFilledCount() {
            this.filledCount++;
        }
    }

    //check if the player has won the large, outer tic tac toe board, play win animation if true
    void checkBigWin(Tile tile) {
        int x, y = 0;
        int quad = tile.getQuadrant();

        //get the (x,y) of the last quad won
        x = quad % 3;
        if (quad >= 3 && quad <= 5) {
            y = 1;
        } else if (quad >= 6 && quad <= 8) {
            y = 2;
        }

        double startX, startY, endX, endY;

        //check cols
        for (int i = 0; i < winBoard.length; i++) {
            if (!Objects.equals(winBoard[x][i].getValue(), tile.getValue())) {
                break;
            }
            if (i == winBoard.length - 1) {

                startX = (TILE_SIZE * 1.5) + SPACING * (x + 1) + (x * 3 * TILE_SIZE);
                startY = (TILE_SIZE * 1.5) + SPACING;

                endX = startX;
                endY = startY + (TILE_SIZE * 6) + (SPACING * 2);

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

                startX = (TILE_SIZE * 1.5) + SPACING;
                startY = (TILE_SIZE * 1.5) + SPACING * (y + 1) + (y * 3 * TILE_SIZE);

                endX = startX + (TILE_SIZE * 6) + (SPACING * 2);
                endY = startY;

                System.out.println("WE HAVE AN ACTUAL WINNER!");
                playWinAnimation(startX, startY, endX, endY);

            }
        }

        //check diag
        if (x == y) {
            for (int i = 0; i < winBoard.length; i++) {
                if (!Objects.equals(winBoard[i][i].getValue(), tile.getValue())) {
                    break;
                }
                if (i == winBoard.length - 1) {
                    startX = (TILE_SIZE * 1.5) + SPACING;
                    //noinspection SuspiciousNameCombination
                    startY = startX;

                    endX = startX + (TILE_SIZE * 6) + (SPACING * 2);
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
                if (!Objects.equals(winBoard[i][winBoard.length - 1 - i].getValue(), tile.getValue())) {
                    break;
                }
                if (i == winBoard.length - 1) {

                    startX = (TILE_SIZE * 1.5) + SPACING;
                    startY = (TILE_SIZE * 7.5) + SPACING * 3;

                    endX = (TILE_SIZE * 7.5) + SPACING * (x + 1);
                    endY = (TILE_SIZE * 1.5) + SPACING;

                    System.out.println("WE HAVE AN ACTUAL WINNER!");
                    playWinAnimation(startX, startY, endX, endY);
                }
            }
        }

        //check for a tie
        for (int i = 0; i < winBoard.length; i++) {
            for (int j = 0; j < winBoard.length; j++) {
                if (!winBoard[i][j].hasWon) {
                    System.out.println("No big tie yet");
                    return;
                }
                if (i == 2 && j == 2) {
                    System.out.println("There was a tie!");
                    TieBox alert = new TieBox("Game Over", "The game was a tie!");
                    alert.display();
                    try {
                        this.connectToHome();
                    } catch (IOException e)
                    {
                        e.printStackTrace();
                    }
                    getWindow().setScene(getCurrentScene());
                    getWindow().show();
                }
            }
        }
    }



    //check if the player was won the inner tic tac toe board, return true if they have
    final boolean checkSmallWin(Tile tile) {
        int quad = tile.getQuadrant();
        boolean answer = false;
        int i, j = 0;

        outerLoop:
        for (i = 0; i < board.length; i++) {
            for (j = 0; j < board.length; j++) {
                if (board[i][j].getQuadrant() == quad) {
                    answer = board[i][j].checkGridWin(tile);
                    break outerLoop;
                }
            }
        }

        System.out.println("The incrementing fill is: " + board[i][j].getFilledCount());
        //Add the winner tile to the winBoard
        if (answer || board[i][j].getFilledCount() == 8) {
            WinnerTile temp = new WinnerTile();
            int transX = (int) SPACING, transY = (int) SPACING;
            int x = 0, y = 0;                            //the (x,y) of the quads

            System.out.println("There is a small winner or it was a Cat!!");

            //Clear the gridBlock in the location of the win
            board[i][j].removeBlock();

            //Calculate x shift
            if (quad % 3 == 1) {
                transX += (3 * TILE_SIZE + SPACING);
                x = 1;
            } else if (quad % 3 == 2) {
                transX += (6 * TILE_SIZE + SPACING * 2);
                x = 2;
            }

            //Calculate y shift
            if (quad >= 3 && quad <= 5) {
                transY += (3 * TILE_SIZE + SPACING);
                y = 1;
            } else if (quad >= 6 && quad <= 8) {
                transY += (6 * TILE_SIZE + SPACING * 2);
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
                temp.getText().setFont(Font.font("System", (MAX_WIDTH / 75.0)));
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

    //play the winning animation
    private void playWinAnimation(final double startX, final double startY, final double endX, final double endY) {
        System.out.println("Playing animation");

        //Create the line
        Line line = new Line();
        line.setStartX(startX);
        line.setStartY(startY);
        line.setEndX(startX);
        line.setEndY(startY);
        line.setStrokeWidth(5);
        pane.getChildren().add(line);

        //Animate the line being drawn over the winning tiles
        Timeline timeline = new Timeline();
        timeline.getKeyFrames().add(new KeyFrame(Duration.seconds(1.5),
                new KeyValue(line.endXProperty(), endX),
                new KeyValue(line.endYProperty(), endY)));
        timeline.play();

        //Open up the WinBox to congratulate the winner
        timeline.setOnFinished(e -> {
            try {
                connectToWinBox();
            } catch (IOException e1) { e1.printStackTrace(); }
            getWindow().setScene(getCurrentScene());
            getWindow().show();
        });
    }

    public static boolean getWinner() {
        return turnX;
    }

    private int calcPaneSize() {
        return (int) ((9 * TILE_SIZE) + (2 * SPACING));
    }

    @FXML
    private void exitGame() {
        closeProgram(getWindow());
    }

    @FXML
    private void goHome() throws IOException {
        backToHome();
    }
}
