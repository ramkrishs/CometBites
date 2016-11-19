package edu.utdallas.cometbites.services;

import java.util.List;

import edu.utdallas.cometbites.models.FoodJoint;
import edu.utdallas.cometbites.models.Item;
import edu.utdallas.cometbites.models.Order;
import edu.utdallas.cometbites.models.Payment;

public class DBFacade {
    //private Connection conn;

    public DBFacade() {
        //getConnection
    }

    public List<FoodJoint> getFoodJoint() {
       return null;
    }

    public List<Item> getMenu(int foodJointID) {
        return null;
    }

    public Item getItem(int itemID) {
        return null;
    }

    public List<Payment> getPaymentOptions(int customerID) {
        return null;
    }

    public void saveOrder(Order order) {

    }

    public void saveTransaction(Payment payment) {

    }


}
