package org.plopl.chess;

import org.plopl.chess.pieces.Piece;

public class Move {

    Piece piece;
    Field from;
    Field to;

    public Move(Piece piece, Field from, Field to) {
        this.piece = piece;
        this.from = from;
        this.to = to;
    }
}
