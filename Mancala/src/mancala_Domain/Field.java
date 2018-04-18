package mancala_Domain;

public abstract class Field {

    private int pebbleCount;
    private Field fieldNextField;
    private Player ownerOfBowl;

    protected Field(Player player) {

        int fieldCount = 1;
        this.pebbleCount = 4;
        this.ownerOfBowl = player; // player 1        
        Field firstBowl = this;
        this.fieldNextField = new Bowl(fieldCount, firstBowl, player);
    }

    protected Field(int fieldCount, Field firstBowl, Player player) { // constructor

        fieldCount++;
        this.ownerOfBowl = player;
        this.pebbleCount = 4;
        if (fieldCount > 7) {
            this.ownerOfBowl = player.getOpponent(); // player 2
        }
        if (fieldCount < 6 || fieldCount > 6 && fieldCount < 13) {
            this.fieldNextField = new Bowl(fieldCount, firstBowl, player);
        }
        if (fieldCount == 6 || fieldCount == 13) {
            this.fieldNextField = new Kalaha(fieldCount, firstBowl, player);
        }
        if (fieldCount == 14) {
            this.fieldNextField = firstBowl;
        }
    }

    void addOnePebble() {

        this.pebbleCount++;
    }

    void addMoreThanOnePebble(int pebbles) {

        this.pebbleCount = this.pebbleCount + pebbles;
    }

    void takeAllPebbles() {

        this.pebbleCount = 0;
    }

    public abstract Kalaha getOwnKalaha();

    public abstract Field findOppositeBowl();

    public abstract void emptyAllBowls(int steps);

    public abstract void recievePebbles(int recievedAmountOfPebbles);

    public Field getNeighbor() {

        return this.fieldNextField;
    }

    public Player getOwnerOfField() {

        return ownerOfBowl;
    }

    public int countPebblesInBowl() {

        return this.pebbleCount;
    }

    public int countAllPebbles() {

        int totalPebbles = countPebblesInBowl() + getNeighbor().countAllPebbles();
        return totalPebbles;
    }

    public void passPebbles(int passedAmountOfPebbles) {

        getNeighbor().recievePebbles(passedAmountOfPebbles);
    }

    // alleen gebruiken voor de test (Junit)
    public Field findInField(int count) {

        Field next = this;
        if (!(count == 1)) {
            count--;
            next = next.getNeighbor().findInField(count);
        }
        return next;
    }
}
