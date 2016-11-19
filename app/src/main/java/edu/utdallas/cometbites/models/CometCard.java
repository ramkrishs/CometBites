package edu.utdallas.cometbites.models;

public class CometCard extends Card {
    private int utdID;

    public CometCard(int utdID) {
        this.utdID = utdID;
    }

    public int getNumber() {
        return utdID;
    }
}
