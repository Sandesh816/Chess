package com.chess.engine.pieces;

public class Rook extends Piece{

    private static final int value = 5;
    public Rook(int x, int y, int color) {
        super(x, y, color);
    }

    @Override
    public void legalMoves() {

    }

    @Override
    public String toString() {
        return "Rook";
    }

    @Override
    public int getValue() {
        return value * color;
    }
}
