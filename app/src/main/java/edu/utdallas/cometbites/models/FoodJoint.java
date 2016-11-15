package edu.utdallas.cometbites.models;

/**
 * Created by twinklesharma on 10/26/16.
 */

public class FoodJoint {
    private String logoURL;
    private String estTime;

    public FoodJoint(String logo, String estTime) {
        this.logoURL = logo;
        this.estTime = estTime;
    }

    //getters and setters


    public String getLogo() {
        return logoURL;
    }

    public void setLogo(String logo) {
        this.logoURL = logo;
    }

    public String getEstTime() {
        return estTime;
    }

    public void setEstTime(String estTime) {
        this.estTime = estTime;
    }
}
