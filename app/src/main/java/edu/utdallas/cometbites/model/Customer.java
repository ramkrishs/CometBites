package edu.utdallas.cometbites.model;

import java.util.List;

public class Customer {
    private String netid;
    private String firstname;
    private String lastname;
    private int phonenumber;
    private String emailid;
    private List<Payment> payments;
    //preferred method of payment


    public String getNetid() {
        return netid;
    }

    public String getFirstname() {
        return firstname;
    }

    public String getLastname() {
        return lastname;
    }

    public int getPhonenumber() {
        return phonenumber;
    }

    public String getEmailid() {
        return emailid;
    }

    public List<Payment> getPayments() {
        return payments;
    }
}
