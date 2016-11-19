package edu.utdallas.cometbites.services;

public class UTDPaymentAdapter implements IExternalPayment {
    public boolean authorize(int cardNumber) {
        return false;
    }
    public boolean charge(int cardNumber, double amount) {
        return false;
    }
}
