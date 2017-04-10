package org.plopl.chess.pieces;


import org.plopl.chess.*;

import java.util.stream.Stream;

public class Pawn extends Piece {
    /**
     * `counter` must be incremented every time a new Piece is created.
     *
     * @param color
     */
    public Pawn(Color color) {
        super(color);
    }

    @Override
    String letter() {
        return null;
    }

    @Override
    public Stream<Move> potentialMoves(GameState gs) {
        Field currentPosition = this.getPosition(gs);

        int row = 1;
        if(this.getColor() == Color.BLACK) row = -1;

        return Stream.of(new Vector(row,0))
                .map(currentPosition::add)
                .filter(Field::isWithinBoard)
                .map(dest -> new Move(this.getId(), currentPosition, dest));
    }
}
