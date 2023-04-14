package com.chess.engine.pieces;

public class Bishop extends Piece{

    private static final int value = 4;

    public Bishop(int x, int y, int color) {
        super(x, y, color);
    }

    @Override
    public void legalMoves() {

    }

    @Override
    public String toString() {
        return "Bishop";
    }

    @Override
    public int getValue() {
        return value * color;
    }
}
