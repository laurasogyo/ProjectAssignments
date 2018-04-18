package mancala_Domain;

public class Kalaha extends Field {

    protected Kalaha(int fieldCount, Field firstBowl, Player player) { // constructor

        super(fieldCount, firstBowl, player);
        takeAllPebbles();
    }

    @Override
    public Kalaha getOwnKalaha() {
        return this;
    }

    @Override
    public int countAllPebbles() {

        return 0;
    }

    @Override
    public void recievePebbles(int recievedAmountOfPebbles) {

        if (this.getOwnerOfField().getTurn()) {
            recievedAmountOfPebbles--;
            addOnePebble();
            if (recievedAmountOfPebbles >= 1) {
                passPebbles(recievedAmountOfPebbles);
            }
            return;
        }
        passPebbles(recievedAmountOfPebbles);
    }

    public void recieveManyPebbles(int recievedAmountOfPebbles) {

        if (this.getOwnerOfField().getTurn()) {
            this.addMoreThanOnePebble(recievedAmountOfPebbles);
        } else {
            Field oppositeKalaha = this.getNeighbor().getOwnKalaha();
            oppositeKalaha.addMoreThanOnePebble(recievedAmountOfPebbles);
        }
    }

    @Override
    public Kalaha findOppositeBowl() {

        return this;
    }

    @Override
    public void emptyAllBowls(int steps) {

        if (steps < 14) {
            steps++;
            this.getNeighbor().emptyAllBowls(steps);
        }
    }
}
