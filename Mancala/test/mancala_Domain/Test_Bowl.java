package mancala;

import mancala_Domain.Bowl;
import org.junit.Test;
import static org.junit.Assert.*;

public class Test_Bowl extends TestRules {

    @Test
    public void bowlContainsPebbles() {

        // ALL bowl contain 4, this is tested by:
        assertEquals(4, bowl.findInField(1).countPebblesInBowl()); //check placing 1 (start)
        assertEquals(4, bowl.findInField(6).countPebblesInBowl()); //check placing 6 (bowl before kalaha one)
        assertEquals(4, bowl.findInField(8).countPebblesInBowl()); //check placing 8 (bowl after kalaha one)
        assertEquals(4, bowl.findInField(13).countPebblesInBowl()); //check placing 13 (bowl before kalaha two)
    }

    @Test
    public void getOwnKalaha() {

        assertEquals(bowl.findInField(7), bowl.getOwnKalaha());
        assertEquals(bowl.findInField(14), ((Bowl) bowl.findInField(9)).getOwnKalaha());
    }

    @Test
    public void findOppositeBowl() {

        assertEquals(bowl.findInField(10), bowl.findInField(4).findOppositeBowl());
    }

    @Test
    public void doTurn() {

        ((Bowl) bowl.findInField(1)).doTurn();

        assertEquals(0, bowl.findInField(1).countPebblesInBowl());
    }

    @Test
    public void recievePebblesPlayer1() {

        ((Bowl) bowl.findInField(1)).doTurn();

        assertEquals(5, bowl.findInField(5).countPebblesInBowl());
    }

    @Test
    public void recievePebblesPlayer2() {

        ((Bowl) bowl.findInField(1)).doTurn(); // player 1 set
        ((Bowl) bowl.findInField(8)).doTurn();

        assertEquals(5, bowl.findInField(12).countPebblesInBowl());
    }

    @Test
    public void getOwnerOfBowlPlayer1() {

        assertEquals(player, bowl.findInField(1).getOwnerOfField());
    }

    @Test
    public void getOwnerOfBowlPlayer2() {

        assertEquals(player.getOpponent(), bowl.findInField(8).getOwnerOfField());
    }

    @Test
    public void sen_NoPebblesInBowl() {
        // if there are no pebbles in the bowl, the player can't initiate a move, and it is still his turn

        ((Bowl) bowl.findInField(1)).doTurn(); // player 1 first move
        ((Bowl) bowl.findInField(8)).doTurn(); // player 2 first move
        ((Bowl) bowl.findInField(1)).doTurn(); // player 1 second move, but no stones in bowl

        assertEquals(true, bowl.findInField(1).getOwnerOfField().getTurn()); // after clicking on an empty bowl the player has still his turn
    }

    @Test
    public void sen_CantPickBowlIfNotOwnTurn() {
        // if it not the player's turn the bowls should not be innitiated

        ((Bowl) bowl.findInField(9)).doTurn(); // player 1, want's to access a bowl of player 2

        assertEquals(4, bowl.findInField(9).countPebblesInBowl());
    }

    @Test
    public void sen_IsItStillPlayersTurnAfterPickingBowlWhichIsNotHisOwn() {

        ((Bowl) bowl.findInField(9)).doTurn(); // player 1, want's to access a bowl of player 2

        assertEquals(true, bowl.findInField(2).getOwnerOfField().getTurn());
    }

    @Test
    public void sen_EndingAtEmptyBowlANDopIsNotEmpty() {
        // we weten dat het vinden van de oppesite bowl goed gaat, maar komt deze pebble wel in het juiste bakje?

        ((Bowl) bowl.findInField(5)).doTurn();
        ((Bowl) bowl.findInField(8)).doTurn();
        ((Bowl) bowl.findInField(1)).doTurn();

        assertEquals(8, bowl.findInField(7).countPebblesInBowl()); // kalaha of player contains pebbles after his move
        assertEquals(0, bowl.findInField(9).countPebblesInBowl()); // and the bowl of which the last pebble went contains 0
    }

    @Test
    public void sen_EndGame_NoMorePebblesOnTheBoard() {

        emptyFieldOnOneSide();

        assertEquals(true, ((Bowl) bowl.findInField(1)).hasMoves());
        assertEquals(true, ((Bowl) bowl.findInField(8)).hasMoves());
    }
}
