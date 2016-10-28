package edu.utdallas.cometbites.models;

/**
 * Created by twinklesharma on 10/26/16.
 */

public class FoodJoint {
    private Integer logo;
    private String estTime;

    public FoodJoint(Integer logo, String estTime) {
        this.logo = logo;
        this.estTime = estTime;
    }

    //getters and setters


    public Integer getLogo() {
        return logo;
    }

    public void setLogo(Integer logo) {
        this.logo = logo;
    }

    public String getEstTime() {
        return estTime;
    }

    public void setEstTime(String estTime) {
        this.estTime = estTime;
    }
}
