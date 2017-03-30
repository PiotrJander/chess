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

    /**
     * @return A letter, "l" for white (like in light) and "d" for black (like in dark).
     */
    public String letter() {
        return this == WHITE ? "l" : "d";
    }

}
