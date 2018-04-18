package mancala;

import mancala_Domain.Bowl;
import org.junit.Test;
import static org.junit.Assert.*;

public class Test_Kalaha extends TestRules {

    @Test
    public void kalahaContainsPebbles() {

        assertEquals(0, bowl.findInField(7).countPebblesInBowl()); //check placing 1 (start)
        assertEquals(0, bowl.findInField(14).countPebblesInBowl()); //check placing 6 (bowl before kalaha one
    }

    @Test
    public void getOwnKalahaPlayer1() {

        assertEquals(bowl.findInField(7), bowl.findInField(1).getOwnKalaha());
    }

    @Test
    public void getOwnKalahaPlayer2() {

        assertEquals(bowl.findInField(14), bowl.findInField(8).getOwnKalaha());
    }

    @Test
    public void recievePebblesANDpassPlayer1() {

        ((Bowl) bowl.findInField(6)).doTurn();

        assertEquals(1, bowl.findInField(7).countPebblesInBowl());
    }

    @Test
    public void recievePebblesANDpassPlayer2() {

        ((Bowl) bowl.findInField(6)).doTurn(); // player 1 turn
        ((Bowl) bowl.findInField(13)).doTurn();

        assertEquals(1, bowl.findInField(14).countPebblesInBowl());
    }

    @Test
    public void recievePebblesNOTpass() {

        ((Bowl) bowl.findInField(1)).emptyAllBowls(0);

        // alle stenen op het bord gaan naar de kalaha wiens beurt het is.. in dit geval kalaha 7
        assertEquals(48, bowl.findInField(7).countPebblesInBowl());
        assertEquals(0, bowl.findInField(14).countPebblesInBowl()); // extra check to see if it does not go in the OP kalaha
    }

    @Test
    public void sen_SkipOppositeKalaha() {

        // execute
        ((Bowl) bowl.findInField(1)).doTurn();
        ((Bowl) bowl.findInField(8)).doTurn();
        ((Bowl) bowl.findInField(2)).doTurn();
        ((Bowl) bowl.findInField(3)).doTurn();
        ((Bowl) bowl.findInField(9)).doTurn();
        ((Bowl) bowl.findInField(4)).doTurn();
        ((Bowl) bowl.findInField(10)).doTurn();
        ((Bowl) bowl.findInField(5)).doTurn();
        ((Bowl) bowl.findInField(11)).doTurn();
        ((Bowl) bowl.findInField(6)).doTurn();

        // test
        assertEquals(5, bowl.findInField(7).countPebblesInBowl()); // after the above execution, the first kalaha should contain 5 pebbles and the second by default 3
        assertEquals(3, bowl.findInField(14).countPebblesInBowl());
    }

    @Test
    public void sen_LastPebbleInKalaha() {

        ((Bowl) bowl.findInField(3)).doTurn();

        assertEquals(true, bowl.findInField(4).getOwnerOfField().getTurn()); // because the last pebble was in the bowl, the player keeps his turn!
    }

    @Test
    public void sen_LastPebbleInKalahaAndMoveAgain() {

        ((Bowl) bowl.findInField(3)).doTurn();
        ((Bowl) bowl.findInField(5)).doTurn();

        assertEquals(true, bowl.findInField(9).getOwnerOfField().getTurn());
    }

    @Test
    public void sen_EndGame_PutsPebblesInBowl() {

        emptyFieldOnOneSide();
        
        assertEquals(6, bowl.findInField(7).countPebblesInBowl());
        assertEquals(46, bowl.findInField(14).countPebblesInBowl());
    }
}
