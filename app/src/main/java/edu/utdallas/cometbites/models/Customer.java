package edu.utdallas.cometbites.models;

import java.util.List;

public class Customer {
    private int id;
    private String firstName;
    private String lastName;
    private int phoneNumber;
    private List<Payment> payments;

    public String getFullName() {
        return firstName + lastName;
    }

    public int getPhoneNumber() {
        return phoneNumber;
    }
}
