package org.plopl.chess.pieces;

import org.plopl.chess.*;

import java.util.stream.Stream;

public abstract class VectorPiece extends Piece {

    public VectorPiece(Color color) {
        super(color);
    }

    abstract protected Stream<Vector> vectors();

    /**
     * For each direction a VectorPiece can move at, construct a VectorPiece, get the reachable fields
     * for that VectorPiece, gather results together and convert to Moves.
     */
    @Override
    final public Stream<Move> potentialMoves(GameState gs) {
        Field initialPosition = this.getPosition(gs);

        return vectors()
                .map(vec -> new VectorPieceHelper(vec, gs, initialPosition))
                .flatMap(VectorPieceHelper::reachableFields)
                .map(dest -> new Move(this.getId(), initialPosition, dest));
    }
}
