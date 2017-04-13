package org.plopl.chess.pieces;

import org.plopl.chess.*;

import java.util.stream.Stream;

public class Bishop extends VectorPiece {

    public Bishop(Color color) {
        super(color);
    }

    @Override
    protected Stream<Vector> vectors() {
        return Stream.of(
                new Vector(-1, -1),
                new Vector(-1, 1),
                new Vector(1, -1),
                new Vector(1, 1)
        );
    }

    @Override
    String letter() {
        return "b";
    }


}
