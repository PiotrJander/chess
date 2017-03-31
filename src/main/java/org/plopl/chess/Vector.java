package org.plopl.chess;


/**
 * Base class for Field (which is simply coordinates). Can be added to a Field (coordinates).
 */
public class Vector {

    int row;
    int column;

    public Vector(int row, int column) {
        this.row = row;
        this.column = column;
    }
}
