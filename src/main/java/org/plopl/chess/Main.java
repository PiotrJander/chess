package org.plopl.chess;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.plopl.chess.situations.TwoKings;

import static spark.Spark.get;

public class Main {
    public static void main(String[] args) {

        ObjectMapper mapper = new ObjectMapper();

        GameState gs = new GameState(TwoKings::make);

        get("/new-game", (req, res) -> {
            res.type("application/json");
            res.header("Access-Control-Allow-Origin", "*");
            return mapper.writer().writeValueAsString(gs.getBoard());
        });

//        get("/next-move", (req, res) -> {
//            String[] shuffled = shuffle(b);
//            res.type("application/json");
//            res.header("Access-Control-Allow-Origin", "*");
//            return mapper.writer().writeValueAsString(shuffled);
//        });
    }
}
