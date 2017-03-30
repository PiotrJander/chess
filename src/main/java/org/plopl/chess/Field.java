package org.plopl.chess;

import java.util.stream.IntStream;
import java.util.stream.Stream;

public class Field extends Vector {

    public Field(int row, int column) {
        super(row, column);
    }

    public static Stream<Field> allFields() {
        return IntStream.range(0, 8).boxed().flatMap(i -> IntStream.range(0, 8).mapToObj(j -> new Field(i, j)));
    }

    public Field add(Vector other) {
        return new Field(this.row + other.row, this.column + other.column);
    }

    public boolean isWithinBoard() {
        return row >= 0 && row <= 7 && column >= 0 && column <= 7;
    }
}
