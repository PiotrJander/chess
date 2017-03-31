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

    @Override
    public Stream<Move> potentialMoves(GameState gs) {
        Field initialPosition = this.getPosition(gs);

        return Stream.of(new Vector(-1, -1), new Vector(1, -1), new Vector(-1, 1), new Vector(1, 1))
                .map(vec -> new VectorPiece(vec, gs, initialPosition))
                .flatMap(VectorPiece::potentialMoves)
                .map(dest -> new Move(this.getId(), initialPosition, dest));
    }
}
