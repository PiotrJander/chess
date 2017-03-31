package org.plopl.chess.situations;

import org.plopl.chess.Board;
import org.plopl.chess.Color;
import org.plopl.chess.pieces.Bishop;
import org.plopl.chess.pieces.King;

public class CheckSituation {

    static public void make(Board board) {
        board.set(0, 3, new King(Color.WHITE));
        board.set(7, 3, new King(Color.BLACK));
        board.set(2, 5, new Bishop(Color.BLACK));
    }
}
