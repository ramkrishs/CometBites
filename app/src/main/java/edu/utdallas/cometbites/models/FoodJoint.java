package edu.utdallas.cometbites.models;

/**
 * Created by twinklesharma on 10/26/16.
 */

public class FoodJoint {
    private int fjid;
    private String logo;
    private String wait_time;


    //getters and setters


    public void setFjid(int fjid) {
        this.fjid = fjid;
    }

    public void setLogo(String logo) {
        this.logo = logo;
    }

    public void setWait_time(String wait_time) {
        this.wait_time = wait_time;
    }

    public int getFjid() {
        return fjid;
    }

    public String getLogo() {
        return logo;
    }

    public String getWait_time() {
        return wait_time;
    }
}
