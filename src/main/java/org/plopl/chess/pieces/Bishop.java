package org.plopl.chess.pieces;

import org.plopl.chess.Color;
import org.plopl.chess.Field;
import org.plopl.chess.GameState;

import java.util.stream.Stream;

public class Bishop extends Piece {

    public Bishop(Color color) {
        super(color);
    }

    @Override
    String letter() {
        return "b";
    }

    @Override
    public Stream<Field> potentialMoves(GameState gs) {
        return null;
    }
}
