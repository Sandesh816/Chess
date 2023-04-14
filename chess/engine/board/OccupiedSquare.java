package com.chess.engine.board;

import com.chess.engine.pieces.Piece;

public class OccupiedSquare implements Square {
    int x;
    int y;
    Piece occupyingPiece;

    public OccupiedSquare(int x, int y, Piece occupyingPiece){
        this.x = x;
        this.y = y;
        this.occupyingPiece = occupyingPiece;

    }

    @Override
    public boolean isOccupied() {
        return true;
    }

    @Override
    public Piece getPiece() {
        return occupyingPiece;
    }
}
