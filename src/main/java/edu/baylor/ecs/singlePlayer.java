package edu.baylor.ecs;

import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.Timeline;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.input.MouseButton;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Line;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.util.Duration;

import java.io.IOException;
import java.net.URL;
import java.util.Objects;
import java.util.ResourceBundle;

import static edu.baylor.ecs.MasterWindow.*;

public class singlePlayer implements Initializable {
    private final tileBlock[][] board = new tileBlock[3][3];
    private final WinnerTile[][] winBoard = new WinnerTile[3][3];
    private Tile previousTile = new Tile();
    private boolean firstTurn = true;
    private boolean valid = true;
    private static boolean turnX = true;
    private final static int spacing = maxWidth/120;
    private final static int tileSize = maxWidth/25;
    private final static int tileFont = maxWidth/30;

    @FXML
    private BorderPane borderpane;

    @FXML
    private Pane pane;

    @FXML
    private Label player1Turn, player2Turn, player1Label, player2Label, titleLabel, quadID;

    @FXML
    private VBox player1VBOX,player2VBOX;

    @Override
    public void initialize(URL location, ResourceBundle resources) {

        //dynamically resize the board to your screen
        borderpane.setPrefHeight(maxHeight);
        borderpane.setPrefWidth(maxWidth);

        int paneSize = calcPaneSize();
        pane.setPrefSize(paneSize, paneSize);

        player1VBOX.setSpacing(maxHeight/4);
        player2VBOX.setSpacing(maxHeight/4);

        player1Turn.setFont(Font.font("System",maxWidth/50));
        player2Turn.setFont(Font.font("System",maxWidth/50));

        quadID.setFont(Font.font("System",maxWidth/50));

        player1Label.setFont(Font.font("System",maxWidth/35));
        player2Label.setFont(Font.font("System",maxWidth/35));

        titleLabel.setFont(Font.font("System",maxWidth/25));


        getWindow().setMaximized(true);
        getWindow().setMaxWidth(maxWidth);
        getWindow().setMaxHeight(maxHeight);
        getWindow().setHeight(maxHeight);
        getWindow().setWidth(maxWidth);

        //create the gameboard
        int quad = 0;
        for (int y = 0; y < 3; y++) {
            for (int x = 0; x < 3; x++) {
                tileBlock block = new tileBlock(quad);
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

    private class Tile extends StackPane {
        private final Text text = new Text();
        private int x;
        private int y;
        private boolean marked = false;
        private int quadrant;

        Tile() {
            //Create the tile appearance and add it to the pane
            Rectangle border = new Rectangle(tileSize, tileSize);
            border.setFill(null);
            border.setStroke(Color.BLACK);

            text.setFont(Font.font(tileFont));
            setAlignment(Pos.CENTER);

            getChildren().addAll(border, text);

            //user has clicked on a tile
            setOnMouseClicked(e -> {

                //cant play on already played tile
                if (marked)
                    return;

                //has the quadrant already been won?
                boolean playAnywhere = false;

                //it is X's turn
                if (e.getButton() == MouseButton.PRIMARY && turnX) {

                    playAnywhere = calcPlayAnywhere();

                    //make sure turn is valid and if the previous quad has been won, go anywhere
                    if(!firstTurn && (previousTile.calculateBigQuad() != this.getQuadrant()) && !playAnywhere){
                        System.out.printf("My previous quad is %d and this quad is %d",previousTile.calculateBigQuad(),this.getQuadrant());
                        WrongMoveBox wrong = new WrongMoveBox("Wrong Move","You must play in the correct quadrant from the last move!");
                        return;
                    }

                    //take note if the first move
                    else
                        if(firstTurn){
                        System.out.println("This is my first move!");
                        previousTile = this;
                        firstTurn = false;
                    }

                    //draw the X
                    drawX();

                    //make not of the current turn
                    previousTile = this;

                    //check if X has won the tileBlock
                    if (checkSmallWin(this)) {
                        System.out.println("I must check big box!");
                        checkBigWin(this);
                    }

                    //change turn to O's
                    turnX = false;
                    player1Turn.setVisible(false);
                    player2Turn.setVisible(true);
                    quadID.setText("Quadrant number to play: " + (previousTile.calculateBigQuad()+1));

                    //it is O's turn
                } else if (e.getButton() == MouseButton.PRIMARY && !turnX) {

                    playAnywhere = calcPlayAnywhere();

                    //make sure turn is valid and if the previous quad has been won, go anywhere
                    if(!firstTurn && (previousTile.calculateBigQuad() != this.getQuadrant()) && !playAnywhere){
                        System.out.println("WRONG MOVE FOOL!");
                        System.out.printf("My previous quad is %d and this quad is %d",previousTile.calculateBigQuad(),this.getQuadrant());
                        WrongMoveBox wrong = new WrongMoveBox("Wrong Move","You must play in the correct quadrant from the last move!");
                        return;
                    }

                    //draw the O
                    drawO();

                    //make not of the current turn
                    previousTile = this;

                    //check if O has won the tileBlock
                    if (checkSmallWin(this)) {
                        System.out.println("I must check big box!");
                        checkBigWin(this);
                    }

                    //change to X's turn
                    turnX = true;
                    player2Turn.setVisible(false);
                    player1Turn.setVisible(true);
                    quadID.setText("Quadrant number to play: " + (previousTile.calculateBigQuad()+1));
                }
            });
        }

        //set the text of the tile to "X"
        private void drawX() {
            System.out.println("You touched x:" + x + " y:" + y);
            text.setText("X");
            marked = true;
        }

        //set the text of the tile to "O"
        private void drawO() {
            System.out.println("You touched x:" + x + " y:" + y);
            text.setText("O");
            marked = true;
        }

        private void setQuadrant(int quadrant) {
            this.quadrant = quadrant;
        }

        private int getQuadrant() {
            return quadrant;
        }

        private String getValue() {
            return text.getText();
        }

        private int getX() {
            return x;
        }

        private void setX(int x) {
            this.x = x;
        }

        private int getY() {
            return y;
        }

        private void setY(int y) {
            this.y = y;
        }

        //calculate the quadrant based on the X,Y values
        private int calculateBigQuad(){
            return this.x+ (this.y * 3);
        }

        //determine if the quadrant has already been won, return answer
        private boolean calcPlayAnywhere() {
            boolean answer = false;

            outer:
            if (!firstTurn) {
                for (WinnerTile row[] : winBoard) {
                    for (WinnerTile col : row) {
                        //find the WinnerTile with the same quadrants
                        if (col.getQuadrant() == previousTile.calculateBigQuad()) {
                            //check if the tile has already been won
                            if (col.isHasWon())
                                answer = true;
                            break outer;
                        }
                    }
                }
            }
            return answer;
        }
    }

    private class tileBlock {
        private Tile[][] block = new Tile[3][3];
        private int quadrant;
        private int filledCount = 0;


        tileBlock(int quad) {
            int transX, transY;
            quadrant = quad;

            for (int y = 0; y < block.length; y++) {
                for (int x = 0; x < block.length; x++) {
                    Tile tile = new Tile();

                    //Calculate x shift
                    transX = x * tileSize + spacing;
                    if (quadrant % 3 == 1)
                        transX += (3 * tileSize + spacing);
                    else if (quadrant % 3 == 2)
                        transX += (6 * tileSize + spacing * 2);

                    //Calculate y shift
                    transY = (y * tileSize) + (quadrant / 3) * (3 * tileSize) + spacing;
                    if (quadrant >= 3 && quadrant <= 5)
                        transY += spacing;
                    else if (quadrant >= 6 && quadrant <= 8)
                        transY += spacing * 2;

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

        public Tile[][] getBlock() {
            return block;
        }

        public void setBlock(Tile[][] block) {
            this.block = block;
        }

        private int getQuadrant() {
            return quadrant;
        }

        public void setQuadrant(int quadrant) {
            this.quadrant = quadrant;
        }

        //return true if they are the winner
        private boolean checkGridWin(Tile checkMe) {
            boolean winner = false;
            String mark = checkMe.getValue();

            //check cols
            for (int i = 0; i < block.length; i++) {
                if (!Objects.equals(block[checkMe.getX()][i].getValue(), mark))
                    break;
                if (i == block.length - 1)
                    return true;
            }

            //check rows
            for (int i = 0; i < block.length; i++) {
                if (!Objects.equals(block[i][checkMe.getY()].getValue(), mark))
                    break;
                if (i == block.length - 1)
                    return true;
            }

            //check diag
            if (checkMe.getX() == checkMe.getY()) {
                for (int i = 0; i < block.length; i++) {
                    if (!Objects.equals(block[i][i].getValue(), mark))
                        break;
                    if (i == block.length - 1)
                        return true;
                }
            }

            //check anti-diag
            if (checkMe.getX() + checkMe.getY() == block.length - 1) {
                for (int i = 0; i < block.length; i++) {
                    if (!Objects.equals(block[i][block.length - 1 - i].getValue(), mark))
                        break;
                    if (i == block.length - 1)
                        return true;
                }
            }

            return winner;
        }

        private int getFilledCount() {
            return filledCount;
        }

        private void incrementFilledCount() {
            this.filledCount++;
        }
    }

    private class WinnerTile extends StackPane {
        private final Text text = new Text();
        private int quadrant;
        private boolean hasWon = false;

        private WinnerTile() {
            Rectangle border = new Rectangle(tileSize * 3, tileSize * 3);
            border.setFill(null);
            border.setStroke(Color.BLACK);

            text.setFont(Font.font(100));
            text.setText(" ");
            setAlignment(Pos.CENTER);

            getChildren().addAll(border, text);

        }

        private void setQuadrant(int quadrant) {
            this.quadrant = quadrant;
        }

        private Text getText() {
            return text;
        }

        private String getValue() {
            return text.getText();
        }

        private boolean isHasWon() {
            return hasWon;
        }

        private void setHasWon(boolean hasWon) {
            this.hasWon = hasWon;
        }

        private int getQuadrant() {
            return quadrant;
        }
    }

    //check if the player has won the large, outer tic tac toe board, play win animation if true
    private void checkBigWin(Tile tile) {
        int x=0, y=0;
        int quad = tile.getQuadrant();

        //get the (x,y) of the last quad won
        x = quad % 3;
        if (quad >= 3 && quad <= 5)
            y = 1;
        else if (quad >= 6 && quad <= 8)
            y = 2;

        double startX=0,startY=0,endX=0,endY=0;

        //check cols
        for (int i = 0; i < winBoard.length; i++) {
            if (!Objects.equals(winBoard[x][i].getValue(), tile.getValue()))
                    break;
            if (i == winBoard.length - 1) {

                startX = (tileSize * 1.5) + spacing*(x+1) + (x*3*tileSize);
                startY = (tileSize * 1.5) + spacing;

                endX = startX;
                endY = startY + (tileSize*6) + (spacing*2);

                System.out.println("WE HAVE AN ACTUAL WINNER!");
                playWinAnimation(startX,startY,endX,endY);

            }
        }

        //check rows
        for (int i = 0; i < winBoard.length; i++) {
            if (!Objects.equals(winBoard[i][y].getValue(), tile.getValue()))
                break;
            if (i == winBoard.length - 1) {

                startX = (tileSize * 1.5) + spacing;
                startY = (tileSize * 1.5) + spacing*(y+1) + (y*3*tileSize);

                endX = startX + (tileSize*6) + (spacing*2);
                endY = startY;

                System.out.println("WE HAVE AN ACTUAL WINNER!");
                playWinAnimation(startX,startY,endX,endY);

            }
        }

        //check diag
        if (x == y) {
            for (int i = 0; i < winBoard.length; i++) {
                if (!Objects.equals(winBoard[i][i].getValue(), tile.getValue()))
                    break;
                if (i == winBoard.length - 1) {

                    startX = (tileSize * 1.5) + spacing;
                    startY = startX;

                    endX = startX + (tileSize*6) + (spacing*2);
                    endY = endX;

                    System.out.println("WE HAVE AN ACTUAL WINNER!");
                    playWinAnimation(startX,startY,endX,endY);

                }
            }
        }

        //check anti-diag
        if (x + y == winBoard.length - 1) {
            for (int i = 0; i < winBoard.length; i++) {
                if (!Objects.equals(winBoard[i][winBoard.length - 1 - i].getValue(), tile.getValue()))
                    break;
                if (i == winBoard.length - 1) {

                    startX = (tileSize * 1.5) + spacing;
                    startY = (tileSize*7.5) + spacing*3;

                    endX = (tileSize*7.5) + spacing*(x+1);
                    endY = (tileSize * 1.5) + spacing;

                    System.out.println("WE HAVE AN ACTUAL WINNER!");
                    playWinAnimation(startX,startY,endX,endY);

                }
            }
        }

        //check for a tie
        for(int i = 0; i < winBoard.length; i++){
            for(int j = 0;j < winBoard.length; j++){
                if(!winBoard[i][j].hasWon){
                    System.out.println("No big tie yet");
                    return;
                }
                if(i == 2 && j == 2){
                    System.out.println("There was a tie!");
                    TieBox alert = new TieBox("Game Over","The game was a tie!");
                    goHome();
                }
            }
        }
    }



    //check if the player was won the inner tic tac toe board, return true if they have
    private boolean checkSmallWin(Tile tile){
        int quad = tile.getQuadrant();
        boolean answer = false;
        int i=0,j=0;

        outerLoop:
        for(i=0;i<board.length;i++){
            for(j=0;j<board.length;j++){
                if(board[i][j].getQuadrant() == quad) {
                    answer = board[i][j].checkGridWin(tile);
                    break outerLoop;
                }
            }
        }

        System.out.println("The incrementing fill is: " + board[i][j].getFilledCount());
        //Add the winner tile to the winBoard
        if(answer || board[i][j].getFilledCount() == 8){
            WinnerTile temp = new WinnerTile();
            int transX=spacing,transY=spacing;
            int x=0;int y=0;                            //the (x,y) of the quads

            System.out.println("There is a small winner or it was a Cat!!");

            //Clear the gridBlock in the location of the win
            board[i][j].removeBlock();

            //Calculate x shift
            if(quad%3==1) {
                transX += (3 * tileSize + spacing);
                x=1;
            }
            else if(quad%3==2) {
                transX += (6 * tileSize + spacing * 2);
                x=2;
            }

            //Calculate y shift
            if(quad >= 3 && quad <= 5) {
                transY += (3 * tileSize + spacing);
                y=1;
            }
            else if(quad >= 6 && quad <= 8) {
                transY += (6 * tileSize + spacing * 2);
                y=2;
            }

            //Translate the tile and set their coordinate
            temp.setTranslateX(transX);
            temp.setTranslateY(transY);
            temp.setQuadrant(quad);

            if(answer){
                temp.getText().setText(tile.getValue());
            }
            else{
                temp.getText().setText("Tie");
                temp.getText().setFont(Font.font("System",maxWidth/75));
            }

            //add the WinnerTile to the board
            winBoard[x][y] = temp;
            temp.setHasWon(true);

            System.out.println("The small winner won on quad:" + quad + "\t which is also x:"+x+" y:"+y);

            //Add the tile to the block and pane
            pane.getChildren().add(temp);
        }
        //There is no winner, increment count of tileBlock
        else{
            board[i][j].incrementFilledCount();
        }
        return answer;
    }

    //play the winning animation
    private void playWinAnimation(double startX, double startY, double endX, double endY){
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
        timeline.setOnFinished(e -> goHome());
    }

    public static boolean getWinner(){
        return turnX;
    }

    private int calcPaneSize(){
        return (9 * tileSize) + (2* spacing);
    }

    private void goHome(){
        Parent rootParent = null;
        try {
            rootParent = FXMLLoader.load(getClass().getResource("/homeScreen.fxml"));
        } catch (IOException e1) {e1.printStackTrace(); }
        Scene temp = new Scene(rootParent);
        temp.getStylesheets().add("/default.css");

        //getMaster().connectToWin();
        getWindow().setScene(temp);
    }
}
