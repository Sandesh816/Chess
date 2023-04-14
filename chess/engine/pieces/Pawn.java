package com.chess.engine.pieces;

public class Pawn extends Piece {

    boolean wasMoved;
    private static final int value = 1;


    public Pawn(int x, int y, int color){
        super(x, y, color);
        this.wasMoved = false;
    }

    @Override
    public void legalMoves() {

    }

    @Override
    public String toString() {
        return "Pawn";
    }

    @Override
    public int getValue() {
        return value * color;
    }

    public boolean isEnPassantPossible(){
        return true;
    }

    public void promote(Piece newPiece){}

}
