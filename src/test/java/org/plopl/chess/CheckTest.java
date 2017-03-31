package org.plopl.chess;

import org.junit.Test;
import org.plopl.chess.situations.CheckSituation;

import static org.junit.Assert.assertTrue;

public class CheckTest {

    @Test
    public void isCheck() {

        GameState checkSituation = new GameState(CheckSituation::make);
        checkSituation.setWhosTurn(Color.BLACK);
        assertTrue(checkSituation.isCheck(Color.WHITE));
    }
}
