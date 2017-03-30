package org.plopl.chess;

import org.jetbrains.annotations.NotNull;

import java.util.Objects;
import java.util.stream.Stream;

public class GameState {



    Board board;

    Color whosTurn;

    GameState parent;

    public GameState() {

        board = new Board();
        whosTurn = Color.WHITE;

        // TODO constructor for the init game state; populate with init layout
    }

    public GameState(@NotNull GameState parent, @NotNull Move m) {

        this.parent = parent;
        this.whosTurn = parent.whosTurn.other();
        this.board = parent.board;

        // TODO update the board from the parent's move
        // we assume the move is valid, so no need to check it
    }

    Stream<Piece> getAllPiecesOfColor(Color c) {
        return Field.getAllFields().map(field -> board.get(field)).filter(Objects::nonNull).filter(c::pieceHasColor);
    }

}
