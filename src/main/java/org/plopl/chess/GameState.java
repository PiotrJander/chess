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
        lastMove = null;
    }

    public GameState(@NotNull GameState parent, @NotNull Move move) {

        this.parent = parent;
        whosTurn = parent.whosTurn.other();
        board = Board.copyFrom(parent.board);
        lastMove = move;

        board.set(move.to, board.get(move.from));
        board.set(move.from, null);
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

    public boolean isCheck(Color myColor) {
        Color yourColor = myColor.other();
        return allPiecesOfColor(yourColor).anyMatch(piece -> piece.threatens(this, kingOfColor(myColor)));
    }

    public boolean isMate() {
        return isCheck(whosTurn) && successors().collect(Collectors.toList()).isEmpty();
    }

}
