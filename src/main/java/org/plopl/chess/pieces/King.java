package org.plopl.chess.pieces;

import org.plopl.chess.Field;
import org.plopl.chess.GameState;

import java.util.Objects;
import java.util.stream.Stream;

public class King extends Piece {
    @Override
    public Stream<Field> potentialMoves(GameState gs) {
        return null;
    }
}
