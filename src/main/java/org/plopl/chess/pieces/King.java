package org.plopl.chess.pieces;

import org.plopl.chess.Color;
import org.plopl.chess.Field;
import org.plopl.chess.GameState;

import java.util.stream.Stream;

public class King extends Piece {
    public King(Color color) {
        super(color);
    }

    @Override
    String letter() {
        return null;
    }

    @Override
    public Stream<Field> potentialMoves(GameState gs) {
        return null;
    }
}
