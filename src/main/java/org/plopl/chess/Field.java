package org.plopl.chess;

import java.util.stream.IntStream;
import java.util.stream.Stream;

public class Field {

    public static Stream<Field> allFields() {
        return IntStream.range(0, 8).boxed().flatMap(i -> IntStream.range(0, 8).mapToObj(j -> new Field(i, j)));
    }

    public Field(int row, int column) {
        this.row = row;
        this.column = column;
    }

    int row;
    int column;
}
