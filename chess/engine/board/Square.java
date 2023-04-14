package com.chess.engine.board;

import com.chess.engine.pieces.Piece;

public interface Square {

    public  boolean isOccupied();

    public  Piece getPiece();

}
