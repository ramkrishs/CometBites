package edu.utdallas.cometbites.model;

/**
 * Created by salilkansal on 10/28/16.
 */

public class LineItem {

    private String quantity;
    private String item_name;
    private String price;

    public LineItem(String quantity, String item_name, String price) {
        this.quantity = quantity;
        this.item_name = item_name;
        this.price = price;
    }

    public String getQuantity() {
        return quantity;
    }

    public String getItem_name() {
        return item_name;
    }

    public String getPrice() {
        return price;
    }
}
