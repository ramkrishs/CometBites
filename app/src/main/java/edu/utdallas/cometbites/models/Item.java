package edu.utdallas.cometbites.models;

/**
 * Created by twinklesharma on 10/26/16.
 */

public class Item {
    private Integer image;
    private String name;
    private String price;

    public Item(Integer image, String name, String price) {
        this.image = image;
        this.name = name;
        this.price = price;
    }

    public Integer getImage() {
        return image;
    }

    public void setImage(Integer image) {
        this.image = image;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }
}
