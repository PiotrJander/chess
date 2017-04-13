package org.plopl.chess.pieces;

import org.plopl.chess.Color;
import org.plopl.chess.Vector;

import java.util.stream.Stream;

public class Queen extends VectorPiece {
    /**
     * `counter` must be incremented every time a new Piece is created.
     *
     * @param color
     */
    public Queen(Color color) {
        super(color);
    }

    @Override
    protected Stream<Vector> vectors() {
        return Stream.of(
                new Vector(-1, -1),
                new Vector(-1, 1),
                new Vector(1, -1),
                new Vector(1, 1),
                new Vector(-1, 0),
                new Vector(1, 0),
                new Vector(0, -1),
                new Vector(0, 1)
        );
    }

    @Override
    String letter() {
        return "q";
    }
}
