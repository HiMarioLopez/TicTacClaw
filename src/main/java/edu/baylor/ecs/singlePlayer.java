package edu.baylor.ecs;

import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.Timeline;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.input.MouseButton;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Line;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.util.Duration;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

public class singlePlayer extends MasterWindow implements Initializable {
    private tileBlock[][] board = new tileBlock[3][3];
    private WinnerTile[][] winBoard = new WinnerTile[3][3];
    private boolean valid = true;
    private boolean turnX = true;
    private int width = 800;
    private int height = 800;

    @FXML
    private Pane pane;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        pane.setPrefSize(width, height);
        getWindow().setMaxWidth(width);
        getWindow().setMaxHeight(height);
        getWindow().setHeight(height);
        getWindow().setWidth(width);

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
        getWindow().show();
    }

    private class Tile extends StackPane {
        private Text text = new Text();
        private int size = 75;
        private int spacing = 15;
        private int x;
        private int y;
        private boolean marked = false;
        private int quadrant;

        public Tile() {
            Rectangle border = new Rectangle(size, size);
            border.setFill(null);
            border.setStroke(Color.BLACK);

            text.setFont(Font.font(65));
            setAlignment(Pos.CENTER);

            getChildren().addAll(border, text);

            setOnMouseClicked(e -> {
                if (marked)
                    return;
                if (e.getButton() == MouseButton.PRIMARY && turnX) {
                    drawX();
                    turnX = false;
                    if (checkSmallWin(this)) {
                        System.out.println("I must check big box!");
                        checkBigWin(this);
                    }
                } else if (e.getButton() == MouseButton.PRIMARY && !turnX) {
                    drawO();
                    turnX = true;
                    if (checkSmallWin(this)) {
                        System.out.println("I must check big box!");
                        checkBigWin(this);
                    }
                }
            });
        }

        private void drawX() {
            System.out.println("You touched x:" + x + " y:" + y);
            text.setText("X");
            marked = true;
        }

        private void drawO() {
            System.out.println("You touched x:" + x + " y:" + y);
            text.setText("O");
            marked = true;
        }

        public int getSpacing() {
            return spacing;
        }

        public void setQuadrant(int quadrant) {
            this.quadrant = quadrant;
        }

        public int getQuadrant() {
            return quadrant;
        }

        public String getValue() {
            return text.getText();
        }

        public int getX() {
            return x;
        }

        public void setX(int x) {
            this.x = x;
        }

        public int getY() {
            return y;
        }

        public void setY(int y) {
            this.y = y;
        }

        public int getSize() {
            return size;
        }
    }

    private class tileBlock {
        private Tile[][] block = new Tile[3][3];
        private int quadrant;
        private int spacing = 15;


        public tileBlock(int quad) {
            int transX, transY;
            quadrant = quad;

            for (int y = 0; y < block.length; y++) {
                for (int x = 0; x < block.length; x++) {
                    Tile tile = new Tile();

                    //Calculate x shift
                    transX = x * tile.getSize() + spacing;
                    if (quadrant % 3 == 1)
                        transX += (3 * tile.getSize() + spacing);
                    else if (quadrant % 3 == 2)
                        transX += (6 * tile.getSize() + spacing * 2);

                    //Calculate y shift
                    transY = (y * tile.getSize()) + (quadrant / 3) * (3 * tile.getSize()) + spacing;
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

        public void removeBlock() {
            for (int i = 0; i < block.length; i++) {
                for (int j = 0; j < block.length; j++) {
                    block[i][j].setVisible(false);
                }
            }
        }

        public Tile[][] getBlock() {
            return block;
        }

        public void setBlock(Tile[][] block) {
            this.block = block;
        }

        public int getQuadrant() {
            return quadrant;
        }

        public void setQuadrant(int quadrant) {
            this.quadrant = quadrant;
        }

        //return true if they are the winner
        public boolean checkGridWin(Tile checkMe) {
            boolean winner = false;
            String mark = checkMe.getValue();

            //check cols
            for (int i = 0; i < block.length; i++) {
                if (block[checkMe.getX()][i].getValue() != mark)
                    break;
                if (i == block.length - 1)
                    return true;
            }

            //check rows
            for (int i = 0; i < block.length; i++) {
                if (block[i][checkMe.getY()].getValue() != mark)
                    break;
                if (i == block.length - 1)
                    return true;
            }

            //check diag
            if (checkMe.getX() == checkMe.getY()) {
                for (int i = 0; i < block.length; i++) {
                    if (block[i][i].getValue() != mark)
                        break;
                    if (i == block.length - 1)
                        return true;
                }
            }

            //check anti-diag
            if (checkMe.getX() + checkMe.getY() == block.length - 1) {
                for (int i = 0; i < block.length; i++) {
                    if (block[i][block.length - 1 - i].getValue() != mark)
                        break;
                    if (i == block.length - 1)
                        return true;
                }
            }

            return winner;
        }
    }

    private class WinnerTile extends StackPane {
        private Text text = new Text();
        private int smallTileSize = 75;
        private int quadrant;
        private int spacing = 15;

        public WinnerTile() {
            Rectangle border = new Rectangle(smallTileSize * 3, smallTileSize * 3);
            border.setFill(null);
            border.setStroke(Color.BLACK);

            text.setFont(Font.font(100));
            text.setText(" ");
            setAlignment(Pos.CENTER);

            getChildren().addAll(border, text);

        }

        public void setQuadrant(int quadrant) {
            this.quadrant = quadrant;
        }

        public Text getText() {
            return text;
        }

        public String getValue() {
            return text.getText();
        }

        public int getSmallTileSize() {
            return smallTileSize;
        }

        public int getSpacing() {
            return spacing;
        }
    }

    private void checkBigWin(Tile tile) {
        int x=0, y=0;
        int quad = tile.getQuadrant();

        //get the (x,y) of the last quad won
        x = quad % 3;
        if (quad >= 3 && quad <= 5)
            y = 1;
        else if (quad >= 6 && quad <= 8)
            y = 2;


        //check cols
        for (int i = 0; i < winBoard.length; i++) {
            if (winBoard[x][i].getValue() != tile.getValue())
                    break;
            if (i == winBoard.length - 1) {
                double start=0,end=0;
                int spacing = winBoard[x][0].getSpacing();
                start = winBoard[x][0].getSmallTileSize() * 1.5 + spacing;
                end = winBoard[x][winBoard.length-1].getSmallTileSize() * 7.5 + spacing;


                System.out.println("WE HAVE AN ACTUAL WINNER!\tPlaying animation");
                playWinAnimation(start,start,end);
            }
        }

        //check rows
        for (int i = 0; i < winBoard.length; i++) {
            if (winBoard[i][y].getValue() != tile.getValue())
                break;
            if (i == winBoard.length - 1)
                System.out.println("WE HAVE AN ACTUAL WINNER!");
        }

        //check diag
        if (x == y) {
            for (int i = 0; i < winBoard.length; i++) {
                if (winBoard[i][i].getValue() != tile.getValue())
                    break;
                if (i == winBoard.length - 1)
                    System.out.println("WE HAVE AN ACTUAL WINNER!");
            }
        }

        //check anti-diag
        if (x + y == winBoard.length - 1) {
            for (int i = 0; i < winBoard.length; i++) {
                if (winBoard[i][winBoard.length - 1 - i].getValue() != tile.getValue())
                    break;
                if (i == winBoard.length - 1)
                    System.out.println("WE HAVE AN ACTUAL WINNER!");
            }
        }
    }



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

        //Add the winner tile to the winBoard
        if(answer){
            WinnerTile temp = new WinnerTile();
            int spacing = temp.getSpacing();
            int transX=spacing,transY=spacing+5;
            int x=0;int y=0;                            //the (x,y) of the quads

            System.out.println("There is a small winner!!");

            //Clear the gridBlock in the location of the win
            board[i][j].removeBlock();

            //Calculate x shift
            if(quad%3==1) {
                transX += (3 * tile.getSize() + spacing);
                x=1;
            }
            else if(quad%3==2) {
                transX += (6 * tile.getSize() + spacing * 2);
                x=2;
            }

            //Calculate y shift
            if(quad >= 3 && quad <= 5) {
                transY += (3 * tile.getSize() + spacing);
                y=1;
            }
            else if(quad >= 6 && quad <= 8) {
                transY += (6 * tile.getSize() + spacing * 2);
                y=2;
            }

            //Translate the tile and set their coordinate
            temp.setTranslateX(transX);
            temp.setTranslateY(transY);
            temp.setQuadrant(quad);
            temp.getText().setText(tile.getValue());

            winBoard[x][y] = temp;
            System.out.println("The small winner won on quad:" + quad + "\t which is also x:"+x+" y:"+y);

            //Add the tile to the block and pane
            pane.getChildren().add(temp);
        }
        return answer;
    }

    private void playWinAnimation(double startX,double startY,double endX,double endY){

        Line line = new Line();
        line.setStartX(startX);
        line.setStartY(startY);
        line.setEndX(startX);
        line.setEndY(startY);

        pane.getChildren().add(line);

        Timeline timeline = new Timeline();
        timeline.getKeyFrames().add(new KeyFrame(Duration.seconds(1.5),
                new KeyValue(line.endXProperty(), endX),
                new KeyValue(line.endYProperty(), endY)));
        timeline.play();
    }
}
