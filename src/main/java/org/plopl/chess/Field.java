package org.plopl.chess;

import java.util.Iterator;
import java.util.function.Consumer;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class Field implements Iterable<Field> {

    static Stream<Field> getAllFields() {
        return IntStream.range(0, 8).boxed().flatMap(i -> IntStream.range(0, 8).mapToObj(j -> new Field(i, j)));
    }

    @Override
    public Iterator<Field> iterator() {
        return getAllFields().iterator();
    }

    @Override
    public void forEach(Consumer<? super Field> action) {
        getAllFields().forEach(action);
    }

    public Field(int row, int column) {
        this.row = row;
        this.column = column;
    }

    int row;
    int column;
}
