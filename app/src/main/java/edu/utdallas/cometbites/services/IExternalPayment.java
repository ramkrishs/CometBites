package edu.utdallas.cometbites.services;

public interface IExternalPayment {
    boolean authorize(int cardNumber);
    boolean charge(int cardNumber, double amount);
}
