package edu.utdallas.cometbites.model;

import java.util.Date;
import java.util.List;

public class Order {
    private int orderNumber;
    private double total;
    private Date date;
    private Payment payment;
    private Ticket ticket;
    private Status status;
    private List<LineItem> orderItems;

    public void addItem(int itemID) {

    }

    public List<LineItem> getItems() {
        return orderItems;
    }

    public void updateQuantity(int itemID, int qty) {

    }

    public Ticket concludeOrder(Payment payment) {
        return null;
    }

    public void calculateWaitTime() {

    }

    public void updateTotal() {

    }

    public void updateStatus(Status newStatus) {

    }

    public Status getStatus() {
        return status;
    }

}
