package com.chess.engine.pieces;

import com.chess.engine.board.Board;

import javax.swing.text.Position;
import java.awt.*;

public abstract class Piece {

    int color;
    boolean killed = false;
    int x;
    int y;

    public Piece(int x, int y, int color){
        this.x = x;
        this.y = y;
        this.color =color;
    }

    //get the position of the piece
    public int[] getPosition(){
        return new int[]{x,y};
    }


    //move the piece
    public  void move(int newX, int newY){
        x = newX;
        y = newY;
    }

    public abstract void legalMoves();

    //get the piece type
    public abstract String toString();

    //get the piece value
    public abstract int getValue();

    //get the piece color (white 1, black -1)
    public  int getColor() {
        return color;
    }

    //set the piece color (white 1, black -1)
    public void setColor(int color) {
        this.color = color;
    }


    //check if a piece is killed
    public boolean isKilled() {
        return killed;
    }

    //set if a piece is killed
    public void setKilled(boolean killed) {
        this.killed = killed;
    }



    //get the x pos of the piece
    public int getX() {
        return x;
    }
    //get the y pos of the piece
    public int getY() {
        return y;
    }



    //set the x pos of the piece
    public void setX(int x) {
        this.x = x;
    }
    //set the y pos of the piece
    public void setY(int y) {
        this.y = y;
    }
}
