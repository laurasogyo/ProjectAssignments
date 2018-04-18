package mancala_Domain;

public class Bowl extends Field {

    public Bowl(Player player) { // constructor first bowl, no pebble amount in arguments 

        super(player);
    }

    protected Bowl(int fieldCount, Field firstBowl, Player player) { // constructor first bowl, no pebble amount in arguments 

        super(fieldCount, firstBowl, player);        
    }

    @Override
    public Kalaha getOwnKalaha() {

        return (Kalaha) getNeighbor().getOwnKalaha();
    }

    @Override
    public Bowl findOppositeBowl() {

        return (Bowl) getNeighbor().findOppositeBowl().getNeighbor();
    }

    public void doTurn() {

        if (this.getOwnerOfField().getTurn() == true && !(this.countPebblesInBowl() == 0)) {
            getNeighbor().recievePebbles(countPebblesInBowl());
            takeAllPebbles();
        }
    }

    public void emptyToKalaha() {

        getOwnKalaha().recieveManyPebbles(countPebblesInBowl());
        takeAllPebbles();
    }

    @Override
    public void recievePebbles(int recievedAmountOfPebbles) {

        addOnePebble();
        if (recievedAmountOfPebbles > 1) {
            recievedAmountOfPebbles--;
            passPebbles(recievedAmountOfPebbles);
            return;
        }
        endTurn();
    }

    public void endTurn() {

        this.stealPebbles();
        if (hasMoves()) {
            emptyAllBowls(1);
            getOwnerOfField().gameWon();
        } else {
            this.getOwnerOfField().switchTurn();
        }
    }

    public void stealPebbles() {

        if (this.countPebblesInBowl() == 1 && this.getOwnerOfField().getTurn() == true) {
            Bowl oppositeBowl = findOppositeBowl();
            this.emptyToKalaha();
            oppositeBowl.emptyToKalaha();
            oppositeBowl.takeAllPebbles();
        }
    }

    public boolean hasMoves() {

        int totalPebbles = getOwnKalaha().getNeighbor().countAllPebbles();
        if (totalPebbles == 0) {
            return true;
        }
        return false;
    }

    @Override
    public void emptyAllBowls(int steps) {

        if (steps <=14) {
            this.emptyToKalaha();
            steps++;
            this.getNeighbor().emptyAllBowls(steps);
        }
    }
}
