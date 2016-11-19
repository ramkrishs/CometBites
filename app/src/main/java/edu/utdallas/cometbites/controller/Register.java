package edu.utdallas.cometbites.controller;

import java.util.List;

import edu.utdallas.cometbites.models.FoodJoint;
import edu.utdallas.cometbites.models.LineItem;
import edu.utdallas.cometbites.models.Order;
import edu.utdallas.cometbites.models.Payment;

public class Register {
    private List<FoodJoint> foodJoints;
    private Order order;
    private static Register instance;

    public static synchronized Register getInstance() {
        if(instance == null) {
            instance = new Register();
        }

        return instance;
    }

    public List<FoodJoint> startOrder(){
        return null;
    }

    public void selectFoodJoint(int foodJointID) {

    }

    public void informQuantity(int itemID) {

    }

    public List<LineItem> viewOrder() {
        return order.getItems();
    }

    public List<Payment> checkOut() {
        return null;
    }

    public void selectItem(int itemID) {

    }

    public void makePayment(Payment payment) {

    }
}