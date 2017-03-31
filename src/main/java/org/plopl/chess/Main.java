package org.plopl.chess;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.plopl.chess.situations.AllBishops;

import static spark.Spark.*;

public class Main {

    static ObjectMapper mapper = new ObjectMapper();

    static GameState gs;

    private static void enableCORS(final String origin, final String methods, final String headers) {

        options("/*", (request, response) -> {

            String accessControlRequestHeaders = request.headers("Access-Control-Request-Headers");
            if (accessControlRequestHeaders != null) {
                response.header("Access-Control-Allow-Headers", accessControlRequestHeaders);
            }

            String accessControlRequestMethod = request.headers("Access-Control-Request-Method");
            if (accessControlRequestMethod != null) {
                response.header("Access-Control-Allow-Methods", accessControlRequestMethod);
            }

            return "OK";
        });

        before((request, response) -> {
            response.header("Access-Control-Allow-Origin", origin);
            response.header("Access-Control-Request-Method", methods);
            response.header("Access-Control-Allow-Headers", headers);
            // Note: this may or may not be necessary in your particular application
            response.type("application/json");
        });
    }

    public static void main(String[] args) {

        port(9090);

        enableCORS("*", "*", "*");

        post("/new-game", (req, res) -> {
            gs = new GameState(AllBishops::make);
            res.type("application/json");
            return mapper.writer().writeValueAsString(gs.makeServerMessage());
        });

        post("/next-move", (req, res) -> {

//            JsonNode node = mapper.readTree(req.body());
//            int pieceId = Integer.parseInt(node.get("pieceId").asText());
//            Field to = mapper.readValue(node.get("to").asText(), Field.class);
//            mapper.readValue(req.body(), Move.class);
            String[] split = req.body().split(" ");
            int pieceId = Integer.parseInt(split[0]);
            int row = Integer.parseInt(split[1]);
            int column = Integer.parseInt(split[2]);

            Field from = Field.allFields()
                    .filter(field ->
                            gs.getBoard().get(field) != null
                                    && gs.getBoard().get(field).getId() == pieceId)
                    .findFirst().get();
            Move move = new Move(pieceId, from, new Field(row, column));

            gs = new GameState(gs, move);
            gs = gs.makeMove();
            res.type("application/json");
            return mapper.writer().writeValueAsString(gs.makeServerMessage());
        });
    }


}
