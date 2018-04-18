package mancala;

import mancala_Domain.Bowl;
import mancala_Domain.Player;
import org.junit.Before;

public class TestRules {

    protected Player player;
    protected Bowl bowl;
    protected static int countBeforeAnnotates;

    @Before
    public void beforeEveryTest() {

        countBeforeAnnotates++;
        player = new Player();
        bowl = new Bowl(player);
        System.out.println("test: " + countBeforeAnnotates);
    }

    public void emptyFieldOnOneSide() {

        ((Bowl) bowl.findInField(6)).doTurn();
        ((Bowl) bowl.findInField(8)).doTurn();
        ((Bowl) bowl.findInField(1)).doTurn();
        player.switchTurn();
        ((Bowl) bowl.findInField(2)).doTurn();
        ((Bowl) bowl.findInField(3)).doTurn();
        player.switchTurn();
        ((Bowl) bowl.findInField(4)).doTurn();
        player.switchTurn();
        ((Bowl) bowl.findInField(5)).doTurn();
        player.switchTurn();
        ((Bowl) bowl.findInField(6)).doTurn();
        ((Bowl) bowl.findInField(8)).doTurn();
    }

}
