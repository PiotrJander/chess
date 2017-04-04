package org.plopl.chess.situations;

import org.plopl.chess.Board;
import org.plopl.chess.Color;
import org.plopl.chess.pieces.Bishop;
import org.plopl.chess.pieces.King;
import org.plopl.chess.pieces.Pawn;
import org.plopl.chess.pieces.Rook;

import java.util.stream.IntStream;


public class StandardSet {

    static public void make(Board board) {
        // whites
        IntStream.range(0, 8).forEach(i -> board.set(1, i, new Pawn()));
        board.set(0, 0, new Rook());
        board.set(0, 7, new Rook());
        board.set(0, 1, new Knight()));
        board.set(0, 6, new Knight()));
        board.set(0, 2, new Bishop(Color.WHITE)));
        board.set(0, 5, new Bishop(Color.WHITE)));
        board.set(0, 3, new Queen());
        board.set(0, 4, new King(Color.WHITE));

        // blacks
        IntStream.range(0, 8).forEach(i -> board.set(6, i, new Pawn()));
        board.set(7, 0, new Rook());
        board.set(7, 7, new Rook());
        board.set(7, 1, new Knight()));
        board.set(7, 6, new Knight()));
        board.set(7, 2, new Bishop(Color.BLACK)));
        board.set(7, 5, new Bishop(Color.BLACK)));
        board.set(7, 3, new Queen());
        board.set(7, 4, new King(Color.BLACK));


    }
}