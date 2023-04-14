package com.chess.engine.pieces;

public class Knight extends Piece{

    private static final int value = 4;

    public Knight(int x, int y, int color) {
        super(x, y, color);
    }

    @Override
    public void legalMoves() {

    }

    @Override
    public String toString() {
        return "Knight";
    }

    @Override
    public int getValue() {
        return value * color;
    }
}
