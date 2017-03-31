package org.plopl.chess;

import org.plopl.chess.pieces.Piece;

import java.util.List;
import java.util.Map;


/**
 * Utility class which is created only to get serialized into a JSON for the client.
 */
class ServerMessage {

    Piece[][] board;

    Map<Integer, List<Move>> validMoves;
}
