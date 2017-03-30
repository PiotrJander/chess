package org.plopl.chess;

import org.jetbrains.annotations.NotNull;
import org.plopl.chess.pieces.King;
import org.plopl.chess.pieces.Piece;

import java.util.Objects;
import java.util.function.Consumer;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class GameState {

    private Board board = new Board();

    private Color whosTurn;

    private GameState parent;

    private Move lastMove;

    /**
     * This constructor takes the `make` method of a Situation subclass as its argument.
     * <p>
     * For passing methods as arguments in Java 8, see:
     * - http://stackoverflow.com/questions/25186216/java-8-pass-method-as-parameter
     * - http://stackoverflow.com/questions/28417262/java-8-supplier-consumer-explanation-for-the-layperson
     */
    public GameState(Consumer<Board> makeSituation) {

        makeSituation.accept(board);
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

    public Color getWhosTurn() {
        return whosTurn;
    }

    Stream<Piece> allPiecesOfColor(Color c) {
        return Field.allFields().map(field -> board.get(field)).filter(Objects::nonNull).filter(c::pieceHasColor);
    }

    @NotNull
    King kingOfColor(Color color) {
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
        return allPiecesOfColor(yourColor).anyMatch(piece -> piece.threatens(this, kingOfColor(myColor)));
    }

    boolean isMate() {
        return isCheck(whosTurn) && successors().collect(Collectors.toList()).isEmpty();
    }

}
