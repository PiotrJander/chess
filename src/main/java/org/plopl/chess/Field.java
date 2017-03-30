package org.plopl.chess;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializable;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.jsontype.TypeSerializer;

import java.io.IOException;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class Field extends Vector implements JsonSerializable {

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

    @Override
    public void serialize(JsonGenerator gen, SerializerProvider serializers) throws IOException {
        int[] helper = {row, column};
        gen.writeArray(helper, 0, 2);
    }

    @Override
    public void serializeWithType(JsonGenerator gen, SerializerProvider serializers, TypeSerializer typeSer) throws IOException {

    }
}
