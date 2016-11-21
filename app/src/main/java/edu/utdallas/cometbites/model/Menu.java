package edu.utdallas.cometbites.model;

import java.util.List;

public class Menu {
    private List<Item> items;

    public Menu(List<Item> items) {
        this.items = items;
    }

    public List<Item> getItems() {
        return items;
    }

    public void setItems(List<Item> items) {
        this.items = items;
    }
}
