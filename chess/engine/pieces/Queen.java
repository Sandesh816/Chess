package com.chess.engine.pieces;

public class Queen extends Piece{

    private static final int value = 9;



    public Queen(int x, int y, int color){
        super(x, y, color);
    }


    @Override
    public void legalMoves() {

    }

    @Override
    public String toString() {
        return "Queen";
    }

    @Override
    public int getValue() {
        return value * color;
    }


}
