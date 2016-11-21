package edu.utdallas.cometbites.model;

import java.util.List;

public class Customer {
    private int id;
    private String firstName;
    private String lastName;
    private int phoneNumber;
    private List<Payment> payments;
    //preferred method of payment

    public String getFullName() {
        return firstName + lastName;
    }

    public int getPhoneNumber() {
        return phoneNumber;
    }
}
