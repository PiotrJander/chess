package org.plopl.chess;

import org.plopl.chess.pieces.Piece;

import java.util.List;
import java.util.Map;

public class ServerMessage {

    public Piece[][] board;

    public Map<Integer, List<Move>> validMoves;
}
