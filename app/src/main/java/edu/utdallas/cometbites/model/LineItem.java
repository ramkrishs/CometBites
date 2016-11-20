package edu.utdallas.cometbites.model;

public class LineItem {
    private Item item;
    private int quantity;

    public double getTotal() {
        //FIXME review getTotal
        return item.getPrice() * quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

}
