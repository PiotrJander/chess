package org.plopl.chess.pieces;

import org.plopl.chess.*;

import java.util.stream.Stream;

public class King extends Piece {
    public King(Color color) {
        super(color);
    }

    @Override
    String letter() {
        return "k";
    }

    @Override
    public Stream<Move> potentialMoves(GameState gs) {
        Field currentPosition = this.getPosition(gs);
        return Stream.of(
                new Vector(-1, -1),
                new Vector(-1, 0),
                new Vector(-1, 1),
                new Vector(0, -1),
                new Vector(0, 1),
                new Vector(1, -1),
                new Vector(1, 0),
                new Vector(1, 1)
        )
                .map(currentPosition::add)  // stream of adjacent field
                .filter(Field::isWithinBoard)  // only keep fields within the board
                .filter(gs.fieldHasPieceOfCurrentColor().negate())  // don't allow to move onto our piece
                .map(dest -> new Move(this.getId(), currentPosition, dest));
    }
}
