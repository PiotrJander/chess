package org.plopl.chess;

public enum Color {
    WHITE,
    BLACK;

    Color other() {
        return this == WHITE ? BLACK : WHITE;
    }

    boolean pieceHasColor(Piece piece) {
        return piece.color == this;
    }

}
