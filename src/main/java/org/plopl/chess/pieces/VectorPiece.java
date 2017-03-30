package org.plopl.chess.pieces;

import org.plopl.chess.Field;
import org.plopl.chess.GameState;
import org.plopl.chess.Vector;

import java.util.stream.Stream;

public class VectorPiece {

    Vector vector;

    GameState gs;

    Field initialPosition;

    public VectorPiece(Vector vector, GameState gs, Field initialPosition) {
        this.vector = vector;
        this.gs = gs;
        this.initialPosition = initialPosition;
    }

    public Stream<Field> potentialMoves() {
        return movesFromField(initialPosition);
    }

    private Stream<Field> movesFromField(Field position) {

        Field nextPosition = position.add(vector);

        if (!nextPosition.isWithinBoard()) {
            return Stream.empty();
        }

        Piece pieceOnNextPos = gs.getBoard().get(nextPosition);
        if (pieceOnNextPos != null && pieceOnNextPos.getColor() == gs.getWhosTurn()) {
            // there is our piece on the next position
            return Stream.empty();
        }

        if (pieceOnNextPos == null) {
            // the next position is free
            return Stream.concat(Stream.of(nextPosition), movesFromField(nextPosition));
        } else {
            // there is an enemy piece on the next position
            return Stream.of(nextPosition);
        }
    }
}
