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
    private Tile[][] board = new Tile[9][9];
    private List<Combo> combos = new ArrayList();
    private boolean valid = true;
    private boolean turnX = true;

    @FXML
    private Pane pane;

//    public singlePlayer(){
//        //this.start();
//    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        pane.setPrefSize(1000,1000);
        getWindow().setMaxWidth(1000);
        getWindow().setMaxHeight(1000);
        getWindow().setHeight(1000);
        getWindow().setWidth(1000);
        pane.setPrefHeight(1000);
        pane.setPrefWidth(1000);
        for(int y=0;y<9;y++){
            for(int x=0;x<9;x++){
                Tile tile = new Tile();
                tile.setTranslateX(x*100);
                tile.setTranslateY(y*100);

                pane.getChildren().add(tile);

                board[x][y] = tile;
            }
        }
        //getWindow().setScene(getCurrentScene());
        //getWindow().setHeight(1000);
        //getWindow().setWidth(1000);
        getWindow().show();
    }
/*
    public void start(){
        //pane.setPrefSize(1000,1000);

        for(int y=0;y< board.length;y++){
            for(int x=0;x<board.length;x++){
                Tile tile = new Tile();
                tile.setTranslateX(x*200);
                tile.setTranslateY(y*200);

                pane.getChildren().add(tile);

                board[x][y] = tile;
            }
        }
        singleplayerScreen = new Scene(pane);
        //this.getWindow().setScene(singleplayerScreen);
        //this.getWindow().setHeight(1000);
        //this.getWindow().setWidth(1000);
        this.getWindow().show();
    }*/

    private class Tile extends StackPane{
        private Text text = new Text();

        public Tile(){
            Rectangle border = new Rectangle(100,100);
            border.setFill(null);
            border.setStroke(Color.BLACK);

            text.setFont(Font.font(72));
            setAlignment(Pos.CENTER);

            getChildren().addAll(border,text);

            setOnMouseClicked(e -> {
                if(!valid)
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
            text.setText("X");
        }

        private void drawO(){
            text.setText("O");
        }

        public String getValue(){
            return text.getText();
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
