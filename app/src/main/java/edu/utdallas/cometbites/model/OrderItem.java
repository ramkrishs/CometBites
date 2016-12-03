package edu.utdallas.cometbites.model;

import java.io.Serializable;

/**
 * Created by twinklesharma on 12/2/16.
 */

public class OrderItem implements Serializable{

    String id;
    String name;
    String price;
    String quantity;


    public OrderItem(String id, String name, String price, String quantity) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.quantity = quantity;
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getPrice() {
        return price;
    }

    public String getQuantity() {
        return quantity;
    }
}
