package org.plopl.chess.situations;

import org.plopl.chess.Board;

abstract public class Situation {

    /**
     * Makes the situation on the board. Assumes the board is empty.
     */
    abstract public void make(Board board);
}
