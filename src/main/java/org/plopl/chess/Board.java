package org.plopl.chess;

import org.jetbrains.annotations.Nullable;

public class Board {

    private Piece[][] board = new Piece[8][8];

    @Nullable
    Piece get(int i, int j) {
        return board[i][j];
    }

    @Nullable
    Piece get(Field field) {
        return get(field.row, field.column);
    }

    void set(int i, int j, Piece piece) {
        board[i][j] = piece;
    }

    void set(Field field, Piece piece) {
        set(field.row, field.column, piece);
    }

    Board copy() {
        Board board = new Board();
        // TODO copy pieces from old board
        return board;
    }
}