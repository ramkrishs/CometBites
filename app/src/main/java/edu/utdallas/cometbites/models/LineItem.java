package edu.utdallas.cometbites.models;

public class LineItem {
    private Item item;
    private int quantity;

    public double getTotal() {
        //FIXME review getTotal
        return Double.parseDouble(item.getPrice()) * quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

}
