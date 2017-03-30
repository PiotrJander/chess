package org.plopl.chess;

import org.jetbrains.annotations.NotNull;
import org.plopl.chess.pieces.King;
import org.plopl.chess.pieces.Piece;

import java.util.Objects;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class GameState {

    private Board board;

    private Color whosTurn;

    private GameState parent;

    public GameState() {

        board = new Board();
        whosTurn = Color.WHITE;
        parent = null;

        // TODO constructor for the init game state; populate with init layout
    }

    public GameState(@NotNull GameState parent, @NotNull Move m) {

        this.parent = parent;
        this.whosTurn = parent.whosTurn.other();

        this.board = parent.board;  // TODO copy board instead

        // TODO update the board from the parent's move
        // we assume the move is valid, so no need to check it
    }

    public Board getBoard() {
        return board;
    }

    Stream<Piece> allPiecesOfColor(Color c) {
        return Field.allFields().map(field -> board.get(field)).filter(Objects::nonNull).filter(c::pieceHasColor);
    }

    @NotNull
    King getKingOfColor(Color color) {
        //noinspection OptionalGetWithoutIsPresent
        return (King) allPiecesOfColor(color).filter(piece -> piece instanceof King).findFirst().get();
    }

    Stream<GameState> successors() {
        throw new UnsupportedOperationException();
    }

    Stream<GameState> successorsFromPiece() {
        throw new UnsupportedOperationException();
    }

    boolean isCheck(Color myColor) {
        Color yourColor = myColor.other();
        King myKing = getKingOfColor(myColor);
        Stream<Piece> yourPieces = allPiecesOfColor(yourColor);
        return yourPieces.anyMatch(piece -> piece.threatens(this, myKing));
    }

    boolean isMate() {
        return isCheck(whosTurn) && successors().collect(Collectors.toList()).isEmpty();
    }

}
