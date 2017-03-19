package org.plopl.chess;

import static spark.Spark.*;

public class Main {
    public static void main(String[] args) {

        // TODO make a dummy array holding the init state here

        get("/new-game", (req, res) -> {
            return "Hello world";
            // TODO serialize the board to JSON here
        });

        get("/next-move", (req, res) -> {
            // TODO shuffle the game state array here
            return "";
        });
    }
}
