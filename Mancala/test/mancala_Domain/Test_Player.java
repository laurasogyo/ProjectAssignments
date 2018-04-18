package mancala;

import mancala_Domain.Bowl;
import org.junit.Test;
import static org.junit.Assert.*;

public class Test_Player extends TestRules {

    @Test
    public void switchTurn() {

        ((Bowl) bowl.findInField(5)).doTurn(); // player 1 does a move, and the turn is switched
        
        assertEquals(false, bowl.getOwnerOfField().getTurn());
        assertEquals(true, bowl.findInField(8).getOwnerOfField().getTurn());
    }
}
