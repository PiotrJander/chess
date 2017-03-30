package org.plopl.chess.pieces;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializable;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.jsontype.TypeSerializer;
import org.plopl.chess.Color;
import org.plopl.chess.Field;
import org.plopl.chess.GameState;
import org.plopl.chess.Move;

import java.io.IOException;
import java.util.Objects;
import java.util.stream.Stream;

abstract public class Piece implements JsonSerializable {

    /**
     * Auto-incrementing counter for unique ids.
     */
    private static int counter;

    private int id;
    private Color color;

    /**
     * `counter` must be incremented every time a new Piece is created.
     */
    public Piece(Color color) {
        this.id = counter++;
        this.color = color;
    }

    /**
     * Each type of a piece is denoted by a letter. E.g. king is "k"
     */
    abstract String letter();

    public int getId() {
        return id;
    }

    /**
     * E.g. a white king with id=2 will be serialized to
     * <p>
     * {
     *  type: "King",
     *  color: "WHITE"
     *  code: "kl",
     *  id: 2
     * }
     */
    @Override
    public void serialize(JsonGenerator gen, SerializerProvider serializers) throws IOException {
        gen.writeStartObject();
        gen.writeStringField("type", getClass().getSimpleName());
        gen.writeStringField("color", color.name());
        gen.writeStringField("code", letter() + color.letter());
        gen.writeNumberField("id", id);
        gen.writeEndObject();
    }

    /**
     * Part of the JsonSerializable interface; we don't bother with it for now.
     */
    @Override
    public void serializeWithType(JsonGenerator gen, SerializerProvider serializers, TypeSerializer typeSer) throws IOException {

    }

    /**
     * Potential moves for a Piece in GameState follow from the movement rules for the piece.
     * The only situation when a potential move by player P is not a valid move is when
     * the move would lead to P being checked.
     */
    abstract public Stream<Move> potentialMoves(GameState gs);

    /**
     * Valid moves are potential moves which do not result in the moving player being checked.
     */
    public Stream<Move> validMoves(GameState gs) {
        return potentialMoves(gs)
                .filter(move -> {
                    GameState potentialGameState = new GameState(gs, move);
                    return potentialGameState.isCheck(gs.getWhosTurn());
                });
    }

    /**
     * Returns the Field where the Piece stands.
     */
    public Field getPosition(GameState gs) {
        //noinspection OptionalGetWithoutIsPresent
        return Field.allFields()
                .filter(field -> Objects.equals(this, gs.getBoard().get(field)))
                .findFirst().get();
    }

    /**
     * True iff the Piece `this` threatens the Piece `piece`, i.e. the Field of `piece`
     * is among the potential moves for `this`.
     */
    public boolean threatens(GameState gs, Piece piece) {
        return potentialMoves(gs).filter(field -> Objects.equals(field, piece.getPosition(gs))).findFirst().isPresent();
    }

    public Color getColor() {
        return color;
    }
}
