package org.plopl.chess;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.plopl.chess.situations.TwoKings;

import static spark.Spark.get;

public class Main {

    static ObjectMapper mapper = new ObjectMapper();

    static GameState gs;

    public static void main(String[] args) {

        get("/new-game", (req, res) -> {
            gs = new GameState(TwoKings::make);
            res.type("application/json");
            res.header("Access-Control-Allow-Origin", "*");
            return mapper.writer().writeValueAsString(gs.makeServerMessage());
        });

        get("/make-move", (req, res) -> {
            res.type("application/json");
            res.header("Access-Control-Allow-Origin", "*");
            return "";
        });
    }
}
