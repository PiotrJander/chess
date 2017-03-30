package org.plopl.chess.situations;

import org.plopl.chess.Board;
import org.plopl.chess.Color;
import org.plopl.chess.pieces.King;

public class TwoKings {

    static public void make(Board board) {
        board.set(0, 3, new King(Color.WHITE));
        board.set(7, 3, new King(Color.BLACK));
    }
}
