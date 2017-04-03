package org.plopl.chess.situations;

import org.plopl.chess.Board;
import org.plopl.chess.Color;
import org.plopl.chess.pieces.Bishop;
import org.plopl.chess.pieces.King;

import java.util.stream.IntStream;


public class AllBishops {

    static public void make(Board board) {
        // whites
        IntStream.range(0, 8).forEach(i -> board.set(1, i, new Pawn(Color.WHITE)));
        board.set(0, 0, new Rook(Color.WHITE));
	board.set(0, 7, new Rook(Color.WHITE));
	board.set(0, 1, new Knight(Color.WHITE)));
	board.set(0, 6, new Knight(Color.WHITE)));
	board.set(0, 2, new Bishop(Color.WHITE)));
	board.set(0, 5, new Bishop(Color.WHITE)));
	board.set(0, 3, new Queen(Color.WHITE));
        board.set(0, 4, new King(Color.WHITE));

        // blacks
        IntStream.range(0, 8).forEach(i -> board.set(6, i, new Pawn(Color.BLACK)));
	board.set(7, 0, new Rook(Color.BLACK));
	board.set(7, 7, new Rook(Color.BLACK));
	board.set(7, 1, new Knight(Color.BLACK)));
	board.set(7, 6, new Knight(Color.BLACK)));
	board.set(7, 2, new Bishop(Color.BLACK)));
	board.set(7, 5, new Bishop(Color.BLACK)));
	board.set(7, 3, new Queen(Color.BLACK));
        board.set(7, 4, new King(Color.BLACK));


    }
}