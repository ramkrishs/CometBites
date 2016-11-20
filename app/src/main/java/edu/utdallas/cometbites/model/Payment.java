package edu.utdallas.cometbites.model;

public class Payment {
    private double amount;
    private boolean isAuthorized;
    private Card card;

    public void setAmount(double amount) {
        this.amount = amount;
    }
}
