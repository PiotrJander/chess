package org.plopl.chess;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.plopl.chess.pieces.King;
import org.plopl.chess.pieces.Piece;

import java.util.List;
import java.util.Objects;
import java.util.Random;
import java.util.function.Consumer;
import java.util.function.Predicate;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class GameState {

    private Board board = new Board();

    private Color whosTurn;

    @Nullable
    private GameState parent;

    @Nullable
    private Move lastMove;

    /**
     * This constructor takes the `make` method of a Situation subclass as its argument.
     * <p>
     * For passing methods as arguments in Java 8, see:
     * - http://stackoverflow.com/questions/25186216/java-8-pass-method-as-parameter
     * - http://stackoverflow.com/questions/28417262/java-8-supplier-consumer-explanation-for-the-layperson
     */
    GameState(Consumer<Board> makeSituation) {

        makeSituation.accept(board);
        whosTurn = Color.WHITE;
        parent = null;
        lastMove = null;
    }

    /**
     * Creates a new GameState from the parent GameState and a Move.
     * <p>
     * The move is assumed to be valid.
     */
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

    public void setWhosTurn(Color whosTurn) {
        this.whosTurn = whosTurn;
    }

    private Stream<Piece> allPiecesOfColor(Color c) {
        return Field.allFields().map(field -> board.get(field)).filter(Objects::nonNull).filter(c::pieceHasColor);
    }

    @NotNull
    private King kingOfColor(Color color) {
        //noinspection OptionalGetWithoutIsPresent
        return (King) allPiecesOfColor(color).filter(piece -> piece instanceof King).findFirst().get();
    }

    /**
     * All possible successor GameStates from this GameState in the game tree.
     */
    private Stream<GameState> successors() {
        return allPiecesOfColor(whosTurn).flatMap(this::successorsFromPiece);
    }

    /**
     * All possible successor GameStates from this GameState in the game tree,
     * resulting from moving a given piece.
     */
    private Stream<GameState> successorsFromPiece(Piece piece) {
        return piece.validMoves(this).map(move -> new GameState(this, move));
    }

    public Predicate<Field> fieldHasPieceOfCurrentColor() {
        return field -> getBoard().get(field) != null && getBoard().get(field).getColor() == getWhosTurn();
    }

    /**
     * True iff myColor is checked in this GameState.
     */
    public boolean isCheck(Color myColor) {
        Color yourColor = myColor.other();
        return allPiecesOfColor(yourColor).anyMatch(piece -> piece.threatens(this, kingOfColor(myColor)));
    }

    /**
     * True iff there is a mate in this GameState.
     */
    public boolean isMate() {
        return isCheck(whosTurn) && successors().collect(Collectors.toList()).isEmpty();
    }

    /**
     * Encapsulates current board situation and valid moves for different pieces
     * in a ServerMessage object which will be serialized and sent to the client.
     */
    ServerMessage makeServerMessage() {
        ServerMessage message = new ServerMessage();
        message.board = board.getBoard();
        message.validMoves = allPiecesOfColor(whosTurn)
                .flatMap(piece -> piece.validMoves(this))
                .collect(Collectors.groupingBy(Move::getPieceId));
        return message;
    }

    /**
     * Chooses a random move by randomly selecting from all successor GameStates.
     */
    GameState makeRandomMove() {
        List<GameState> succ = successors().collect(Collectors.toList());
        return succ.get((new Random()).nextInt(succ.size()));
    }

}






