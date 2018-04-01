package edu.baylor.ecs;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.input.MouseButton;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.Text;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

public class singlePlayer extends MasterWindow implements Initializable{
    private tileBlock[][] board = new tileBlock[3][3];
    private List<Combo> combos = new ArrayList();
    private boolean valid = true;
    private boolean turnX = true;

    @FXML
    private Pane pane;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        pane.setPrefSize(1000,1000);
        getWindow().setMaxWidth(1000);
        getWindow().setMaxHeight(1000);
        getWindow().setHeight(1000);
        getWindow().setWidth(1000);
        pane.setPrefHeight(1000);
        pane.setPrefWidth(1000);

        int quad = 0;
        for(int y=0;y<3;y++){
            for(int x=0;x<3;x++){
                tileBlock block = new tileBlock(quad);
                board[x][y] = block;
                quad++;
            }
        }
        //getWindow().setScene(getCurrentScene());
        //getWindow().setHeight(1000);
        //getWindow().setWidth(1000);
        getWindow().show();
    }

    private class Tile extends StackPane{
        private Text text = new Text();
        private int x;
        private int y;
        private boolean marked = false;

        public Tile(){
            Rectangle border = new Rectangle(100,100);
            border.setFill(null);
            border.setStroke(Color.BLACK);

            text.setFont(Font.font(72));
            setAlignment(Pos.CENTER);

            getChildren().addAll(border,text);

            setOnMouseClicked(e -> {
                if(marked)
                    return;
                if(e.getButton() == MouseButton.PRIMARY && turnX){
                    drawX();
                    turnX = false;
                    checkSmallWin();
                }
                else if(e.getButton() == MouseButton.PRIMARY && !turnX) {
                    drawO();
                    turnX = true;
                    checkSmallWin();
                }
            });
        }

        private void drawX(){
            System.out.println("You touched x:"+x+" y:"+y);
            text.setText("X");
            marked = true;
        }

        private void drawO(){
            System.out.println("You touched x:"+x+" y:"+y);
            text.setText("O");
            marked = true;
        }

        public String getValue(){
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
    }

    private class Combo {
        private Tile[] tiles;

        /* take in unspecified amount of tiles */
        public Combo(Tile... tiles){
            this.tiles = tiles;
        }

        public boolean isCombo() {
            if (tiles[0].getValue().isEmpty())
                return false;

            return (tiles[0].getValue().equals(tiles[1].getValue())
                 && tiles[0].getValue().equals(tiles[2].getValue()));
        }
    }

    private class tileBlock{
        private Tile[][] block = new Tile[3][3];
        private int quadrant;


        public tileBlock(int quad){
            quadrant = quad;
            for(int y=0;y<block.length;y++){
                for(int x=0;x<block.length;x++){
                    Tile tile = new Tile();

                    int transX=0;
                    if(quadrant%3==0) {
                        transX = x*100;
                    }
                    else if(quadrant%3==1){
                        transX = (x*100)+ 300;
                    }
                    else{
                        transX = (x*100) + 600;
                    }

                    tile.setTranslateX(transX);
                    tile.setTranslateY((y*100)+ ((quadrant/3)*300));

                    tile.setX(x);
                    tile.setY(y);
                    System.out.println("I created a tile! x:"+x+" y:"+y+"\tMy Quad is "+quadrant);
                    pane.getChildren().add(tile);
                    block[x][y] = tile;
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
    }

    /* a user has won a small section */
    private void checkSmallWin(){
        for (Combo combo:combos){
            if(combo.isCombo()){
                System.out.println("User has won a section!!!");
                //winAnimation(combo);
                break;
            }
        }
    }
}
