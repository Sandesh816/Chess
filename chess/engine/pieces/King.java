package com.chess.engine.pieces;

public class King extends Piece{

    private static final int value = 100000;

    public King(int x, int y , int color){
        super(x, y,color);
    }

    //return the piece type (king)
    @Override
    public String toString() {
        return "King";
    }

    //get the king's value (positive if the king is white and negative if the king is black)
    @Override
    public int getValue() {
        return value * color;
    }


    //check if the king is in check
    public boolean isInCheck(){
        return false;
    }

    //give all the legal moves for the king
    public void legalMoves(){}

    //check if the king is checkmated
    public boolean isCheckmated(){return false;}

    //check if the king is stalemated
    public boolean isStalemated(){return  false;}

    // check if the king has castling rights
    public boolean canCastle(){return true;}



}
