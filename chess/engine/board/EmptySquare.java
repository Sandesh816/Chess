package com.chess.engine.board;

import com.chess.engine.pieces.Piece;

public class EmptySquare implements Square{
    int x;
    int y;

    public EmptySquare(int x, int y){
       this.x = x;
       this.y = y;
    }

    @Override
    public  boolean isOccupied(){
        return false;
    }
    @Override
    public  Piece getPiece(){
        return null;
    }
}
