package com.chess.engine.board;

public class Board {

    //use of build in data structure (array)
    Square [][] board = new Square[8][8];

    public Board(){

    }
    //get the board
    public Square[][] getBoard() {
        return board;
    }
    //set the board
    public void setBoard(Square[][] board) {
        this.board = board;
    }

}
