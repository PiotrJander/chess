package org.plopl.chess;

import java.util.List;
import java.util.Map;

public class ServerMessage {

    public Board board;

    public Map<Integer, List<Field>> validMoves;
}
