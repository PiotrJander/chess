package org.plopl.chess.pieces;

import org.plopl.chess.*;

import java.util.stream.Stream;

public class Bishop extends Piece {

    public Bishop(Color color) {
        super(color);
    }

    @Override
    String letter() {
        return "b";
    }

    /**
     * olaboga co to za kod
     * For each direction a bishop can move at, construct a VectorPiece, get the reachable fields
     * for that VectorPiece, gather results together and convert to Moves.
     */
    @Override
    public Stream<Move> potentialMoves(GameState gs) {
        Field initialPosition = this.getPosition(gs);

        return Stream.of(new Vector(-1, -1), new Vector(1, -1), new Vector(-1, 1), new Vector(1, 1))
                .map(vec -> new VectorPiece(vec, gs, initialPosition))
                .flatMap(VectorPiece::reachableFields)
                .map(dest -> new Move(this.getId(), initialPosition, dest));
    }
}
