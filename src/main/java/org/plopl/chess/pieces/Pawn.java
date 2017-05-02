package org.plopl.chess.pieces;


import org.plopl.chess.*;

import java.util.ArrayList;
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
        return "p";
    }

    @Override
    public Stream<Move> potentialMoves(GameState gs) {
        Field currentPosition = this.getPosition(gs);
        Board board = gs.getBoard();
        Field moveForward1;
        Field moveForward2;
        Field attack1;
        Field attack2;
        ArrayList<Field> fields = new ArrayList<Field>();
        int row = 1;
        int startingRow = 1;

        if(this.getColor() == Color.BLACK) {
            row = -1;
            startingRow = 6;
        }

        moveForward1 = currentPosition.add(new Vector(row, 0));
        if( moveForward1.isWithinBoard() && board.get(moveForward1) == null ){
            // moving one field
            fields.add(moveForward1);

            moveForward2 = currentPosition.add(new Vector(row * 2, 0));
            if( currentPosition.getRow() == startingRow && board.get(moveForward2) == null ){
                // moving two fields
                fields.add(moveForward2);
            }
        }

        // Could be more DRY here
        attack1 = currentPosition.add(new Vector(row, 1));
        attack2 = currentPosition.add(new Vector(row, -1));
        if(attack1.isWithinBoard()){
            // attacking a piece
            if(board.get(attack1) != null && !board.get(attack1).getColor().equals(this.getColor())){
                fields.add(attack1);
            }
            // TODO: bicie w przelocie
        }
        if(attack2.isWithinBoard()){
            // attacking a piece
            if(board.get(attack2) != null && !board.get(attack2).getColor().equals(this.getColor())){
                fields.add(attack2);
            }
            // TODO: bicie w przelocie
        }


        return fields.stream()
                .map(dest -> new Move(this.getId(), currentPosition, dest));
    }
}
