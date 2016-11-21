package edu.utdallas.cometbites.model;

import java.util.Date;

public class CreditCard extends Card {
    private int cardNumber;
    private Date expirationDate;
    private int cvv;


    public CreditCard(int cardNumber, Date expirationDate, int cvv) {
        this.cardNumber = cardNumber;
        this.expirationDate = expirationDate;
        this.cvv = cvv;
    }

    public int getNumber() {
        return cardNumber;
    }
}
