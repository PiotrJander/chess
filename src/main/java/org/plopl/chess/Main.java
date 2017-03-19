package org.plopl.chess;

import com.fasterxml.jackson.databind.ObjectMapper;

import java.util.stream.IntStream;

import static spark.Spark.*;

public class Main {
    public static void main(String[] args) {

        ObjectMapper mapper = new ObjectMapper();

        String[] b = new String[64];
        b[0] = b[7] = "rd"; b[1] = b[6] = "nd"; b[2] = b[5] = "bd"; b[3] = "kd"; b[4] = "qd";
        IntStream.range(8, 16).forEach(i -> b[i] = "pd");
        IntStream.range(16, 48).forEach(i -> b[i] = null);
        IntStream.range(48, 56).forEach(i -> b[i] = "pl");
        b[56] = b[63] = "rl"; b[57] = b[62] = "nl"; b[58] = b[61] = "bl"; b[59] = "kl"; b[60] = "ql";

        get("/new-game", (req, res) -> {
            res.type("application/json");
            res.header("Access-Control-Allow-Origin", "*");
            return mapper.writer().writeValueAsString(b);
        });

        get("/next-move", (req, res) -> {
            // TODO shuffle the game state array here
            return "";
        });
    }
}
