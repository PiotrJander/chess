package org.plopl.chess;

import org.plopl.chess.pieces.Piece;

public enum Color {
    WHITE,
    BLACK;

    Color other() {
        return this == WHITE ? BLACK : WHITE;
    }

    boolean pieceHasColor(Piece piece) {
        return piece.getColor() == this;
    }

}
