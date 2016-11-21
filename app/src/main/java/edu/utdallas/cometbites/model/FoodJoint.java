package edu.utdallas.cometbites.model;

import java.util.List;

/**
 * Created by twinklesharma on 10/26/16.
 */

public class FoodJoint {
    private int fjID;
    private String logo;
    private String wait_time;
    //TODO Putting this temporary until json is fixed
    private List<Item> menu;


    //getters and setters



    public void setLogo(String logo) {
        this.logo = logo;
    }

    public void setWait_time(String wait_time) {
        this.wait_time = wait_time;
    }

    public List<Item> getMenu() {
        return menu;
    }

    public void setMenu(List<Item> menu) {
        this.menu = menu;
    }

    public int getFjID() {
        return fjID;
    }

    public String getLogo() {
        return logo;
    }

    public String getWait_time() {
        return wait_time;
    }

    @Override
    public String toString() {
        return "FoodJoint{" +
                "fjid=" + fjID +
                ", logo='" + logo + '\'' +
                ", wait_time='" + wait_time + '\'' +
                '}';
    }
}
