package org.plopl.chess.pieces;

import org.plopl.chess.Color;
import org.plopl.chess.Field;
import org.plopl.chess.GameState;
import org.plopl.chess.Vector;

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
    public Stream<Field> potentialMoves(GameState gs) {
        Field initialPosition = this.getPosition(gs);
        VectorPiece vectorPieceMinusMinus = new VectorPiece(new Vector(-1, -1), gs, initialPosition);
        VectorPiece vectorPiecePlusMinus = new VectorPiece(new Vector(1, -1), gs, initialPosition);
        VectorPiece vectorPieceMinusPlus = new VectorPiece(new Vector(-1, 1), gs, initialPosition);
        VectorPiece vectorPiecePlusPlus = new VectorPiece(new Vector(1, 1), gs, initialPosition);
        return Stream.of(
                vectorPieceMinusMinus.potentialMoves(),
                vectorPieceMinusPlus.potentialMoves(),
                vectorPiecePlusMinus.potentialMoves(),
                vectorPiecePlusPlus.potentialMoves()
        ).flatMap(x -> x);
    }
}
