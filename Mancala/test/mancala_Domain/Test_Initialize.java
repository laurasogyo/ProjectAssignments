package mancala;

import mancala_Domain.Player;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.not;
import static org.junit.Assert.assertEquals;
import org.junit.Test;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNotSame;
import static org.junit.Assert.assertThat;

public class Test_Initialize extends TestRules {

    @Test
    public void nextFieldIsNotEmpty() {

        assertNotNull(bowl.getNeighbor());
    }

    @Test
    public void lastFieldRefersToFirstBowl() {

        assertEquals(bowl.findInField(14).getNeighbor(), bowl.findInField(1));
    }

    @Test
    public void placingKalahas() {
        // assuming that if place 7 is not a bowl, than it is a Kalaha

        assertThat(bowl.findInField(7), is(not(bowl)));
        assertThat(bowl.findInField(14), is(not(bowl)));
    }

    @Test
    public void kalahasPebbleAmount() {

        assertEquals(0, bowl.findInField(7).countPebblesInBowl());
        assertEquals(0, bowl.findInField(14).countPebblesInBowl());
    }

    @Test
    public void bowlsHaveDifferentPlayers() {

        assertNotSame(bowl.findInField(1).getOwnerOfField(), bowl.findInField(8).getOwnerOfField()); // first bowl(playerOne) is not the same player as bowl 8 (playerTwo)       
        assertNotSame(bowl.findInField(6).getOwnerOfField(), bowl.findInField(13).getOwnerOfField()); // 6th bowl(playerOne) is not the same player as bowl 13 (playerTwo)
    }
    
    @Test 
    public void playersKnowEachother(){
        
        Player playerOne = player;
        Player playerTwo = player.getOpponent();
        
        assertEquals(playerTwo, playerOne.getOpponent());
        assertEquals(playerOne, playerTwo.getOpponent());        
    }
}
