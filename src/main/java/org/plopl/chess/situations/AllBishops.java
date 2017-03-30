package org.plopl.chess.situations;

import org.plopl.chess.Board;
import org.plopl.chess.Color;
import org.plopl.chess.pieces.Bishop;
import org.plopl.chess.pieces.King;

import java.util.stream.IntStream;


public class AllBishops {

    static public void make(Board board) {
        // whites
        IntStream.range(0, 8).forEach(i -> board.set(0, i, new Bishop(Color.WHITE)));
        IntStream.range(0, 8).forEach(i -> board.set(1, i, new Bishop(Color.WHITE)));
        board.set(0, 4, new King(Color.WHITE));

        // blacks
        IntStream.range(0, 8).forEach(i -> board.set(6, i, new Bishop(Color.BLACK)));
        IntStream.range(0, 8).forEach(i -> board.set(7, i, new Bishop(Color.BLACK)));
        board.set(7, 4, new King(Color.BLACK));
    }
}
