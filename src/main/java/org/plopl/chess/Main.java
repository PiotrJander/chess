package org.plopl.chess;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.plopl.chess.situations.AllBishops;

import java.util.Random;
import java.util.stream.IntStream;

import static spark.Spark.get;

public class Main {
    public static void main(String[] args) {

        ObjectMapper mapper = new ObjectMapper();

        GameState gs = new GameState(AllBishops::make);

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

    private static String[] shuffle(String[] b) {
        Random random = new Random();
        String[] shuffle = b.clone();
        IntStream.range(0, 32).forEach(i -> {
            int j = random.nextInt(64);
            int k = random.nextInt(64);
            String s = shuffle[j];
            String t = shuffle[k];
            shuffle[j] = t;
            shuffle[k] = s;
        });
        return shuffle;
    }
}
