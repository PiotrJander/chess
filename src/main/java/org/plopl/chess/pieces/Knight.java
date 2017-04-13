package org.plopl.chess.pieces;

import org.plopl.chess.*;

import java.util.stream.Stream;

public class Knight extends Piece {
    /**
     * `counter` must be incremented every time a new Piece is created.
     *
     * @param color
     */
    public Knight(Color color) {
        super(color);
    }

    @Override
    String letter() {
        return "n";
    }

    @Override
    public Stream<Move> potentialMoves(GameState gs) {
        Field currentPosition = this.getPosition(gs);
        return Stream.of(
                new Vector(2,1),
                new Vector(2,-1),
                new Vector(-2,1),
                new Vector(-2,-1),
                new Vector(1,2),
                new Vector(1,-2),
                new Vector(-1,2),
                new Vector(-1,-2)
        )
                .map(currentPosition::add)
                .filter(Field::isWithinBoard)
                .map(dest -> new Move(this.getId(), currentPosition, dest));
    }
}
