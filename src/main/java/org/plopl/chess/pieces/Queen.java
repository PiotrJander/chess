package org.plopl.chess.pieces;

import org.plopl.chess.Color;
import org.plopl.chess.GameState;
import org.plopl.chess.Move;

import java.util.stream.Stream;

public class Queen extends Piece{
    /**
     * `counter` must be incremented every time a new Piece is created.
     *
     * @param color
     */
    public Queen(Color color) {
        super(color);
    }

    @Override
    String letter() {
        return null;
    }

    @Override
    public Stream<Move> potentialMoves(GameState gs) {
        return null;
    }
}
