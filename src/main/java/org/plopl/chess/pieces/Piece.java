package org.plopl.chess.pieces;

import org.plopl.chess.Color;
import org.plopl.chess.Field;
import org.plopl.chess.GameState;

import java.util.Objects;
import java.util.stream.Stream;

abstract public class Piece {

    private int id;
    private Color color;

    abstract public Stream<Field> potentialMoves(GameState gs);

    public Field getPosition(GameState gs) {
        //noinspection OptionalGetWithoutIsPresent
        return Field.allFields()
                .filter(field -> Objects.equals(this, gs.getBoard().get(field)))
                .findFirst().get();
    }

    public boolean threatens(GameState gs, Piece piece) {
        return potentialMoves(gs).filter(field -> Objects.equals(field, piece.getPosition(gs))).findFirst().isPresent();
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Color getColor() {
        return color;
    }

    public void setColor(Color color) {
        this.color = color;
    }
}
