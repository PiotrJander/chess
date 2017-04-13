package org.plopl.chess.pieces;

import org.plopl.chess.Field;
import org.plopl.chess.GameState;
import org.plopl.chess.Vector;

import java.util.stream.Stream;

class VectorPieceHelper {

    private Vector vector;

    private GameState gs;

    private Field initialPosition;

    VectorPieceHelper(Vector vector, GameState gs, Field initialPosition) {
        this.vector = vector;
        this.gs = gs;
        this.initialPosition = initialPosition;
    }

    /**
     * Compute the reachable fields from `initialPosition`.
     */
    Stream<Field> reachableFields() {
        return reachableFromField(initialPosition);
    }

    /**
     * Recursively compute the reachable fields from `position`.
     */
    private Stream<Field> reachableFromField(Field position) {

        Field nextField = position.add(vector);

        if (!nextField.isWithinBoard()) {
            // next field is out of board
            return Stream.empty();
        }

        Piece pieceOnNextField = gs.getBoard().get(nextField);
        if (pieceOnNextField != null && pieceOnNextField.getColor() == gs.getWhosTurn()) {
            // there is our piece on the next position
            return Stream.empty();
        }

        if (pieceOnNextField == null) {
            // the next position is free, we can recursively explore further fields
            return Stream.concat(Stream.of(nextField), reachableFromField(nextField));
        } else {
            // there is an enemy piece on the next position, so thisi is the last reachable
            // field in this direction
            return Stream.of(nextField);
        }
    }
}
