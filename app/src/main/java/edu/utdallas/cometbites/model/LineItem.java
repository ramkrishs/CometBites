package edu.utdallas.cometbites.model;

/**
 * Created by twinkle sharma on 10/28/16.
 */

public class LineItem {
    private int quantity;
    private Item item;

    public LineItem() {
    }

    public LineItem(Item item) {
        this.item = item;
    }

    public LineItem(int quantity, Item item) {
        this.quantity = quantity;
        this.item = item;
    }

    public String getId() {
        return item.getId();
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public Item getItem() {
        return item;
    }

    public void setItem(Item item) {
        this.item = item;
    }

    public double getTotal() {
        return quantity * Double.parseDouble(item.getPrice());
    }

}

