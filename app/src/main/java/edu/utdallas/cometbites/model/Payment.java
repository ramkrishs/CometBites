package edu.utdallas.cometbites.model;

public class Payment {
    private double amount;
    private boolean isAuthorized;
    private Card card;

    public Payment(double amount, Card card) {
        this.amount = amount;
        this.isAuthorized = false;
        this.card = card;
    }


    public double getAmount() {
        return amount;
    }

    public boolean isAuthorized() {
        return isAuthorized;
    }

    public Card getCard() {
        return card;
    }

    public void setCard(Card card) {
        this.card = card;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }
}
