package mancala_Domain;

public class Player {

    private boolean turn;
    final private Player opponent;

    public Player() { // constructor player 1

        this.turn = true;
        Player playerTwo = new Player(this);
        this.opponent = playerTwo;
    }

    private Player(Player playerOne) { // constructor player 2

        this.opponent = playerOne;
        this.turn = false;
    }

    public boolean getTurn() {

        return this.turn;
    }

    private void switchTurnOpposite() {

       turn = !turn;
    }

    public Player getOpponent() {

        return opponent;
    }

    public void switchTurn() {

        turn = !turn;
        opponent.switchTurnOpposite();

    }

    public void gameWon() {

        System.out.println("Game ended"); // hoort buiten het domein!!
        // nog niet af, innumeration (ofzo) tip Robbert
    }
}
