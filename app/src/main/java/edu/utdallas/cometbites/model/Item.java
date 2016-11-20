package edu.utdallas.cometbites.model;

/**
 * Created by twinklesharma on 10/26/16.
 */

public class Item {
    private int id;
    private String image;
    private String name;
    private Double price;
    private String description;

    public Item(int id, String image, String name, Double price, String description) {
        this.id = id;
        this.image = image;
        this.name = name;
        this.price = price;
        this.description = description;
    }

    public int getId() {
        return id;
    }

    public String getImage() {
        return image;
    }

    public String getName() {
        return name;
    }

    public Double getPrice() {
        return price;
    }

    public String getDescription() {
        return description;
    }
}
