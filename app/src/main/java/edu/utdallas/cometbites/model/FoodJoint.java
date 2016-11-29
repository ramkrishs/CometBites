package edu.utdallas.cometbites.model;

import javax.annotation.Generated;
        import com.google.gson.annotations.Expose;
        import com.google.gson.annotations.SerializedName;
        import org.apache.commons.lang3.builder.ToStringBuilder;

import java.util.List;

@Generated("org.jsonschema2pojo")
public class FoodJoint {

    @SerializedName("fjID")
    @Expose
    private int fjID;
    @SerializedName("wait_time")
    @Expose
    private String waitTime;
    @SerializedName("name")
    @Expose
    private String name;
    @SerializedName("logo")
    @Expose
    private String logo;
    @SerializedName("open_time")
    @Expose
    private String openTime;
    @SerializedName("closed_time")
    @Expose
    private String closedTime;
    private List<Item> menu;

    public List<Item> getMenu() {
        return menu;
    }

    public void setMenu(List<Item> menu) {
        this.menu = menu;
    }

    /**
     *
     * @return

     * The fjID
     */
    public int getFjID() {
        return fjID;
    }

    /**
     *
     * @param fjID
     * The fjID
     */
    public void setFjID(int fjID) {
        this.fjID = fjID;
    }

    /**
     *
     * @return
     * The waitTime
     */
    public String getWaitTime() {
        return waitTime;
    }

    /**
     *
     * @param waitTime
     * The wait_time
     */
    public void setWaitTime(String waitTime) {
        this.waitTime = waitTime;
    }

    /**
     *
     * @return
     * The name
     */
    public String getName() {
        return name;
    }

    /**
     *
     * @param name
     * The name
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     *
     * @return
     * The logo
     */
    public String getLogo() {
        return logo;
    }

    /**
     *
     * @param logo
     * The logo
     */
    public void setLogo(String logo) {
        this.logo = logo;
    }

    /**
     *
     * @return
     * The openTime
     */
    public String getOpenTime() {
        return openTime;
    }

    /**
     *
     * @param openTime
     * The open_time
     */
    public void setOpenTime(String openTime) {
        this.openTime = openTime;
    }

    /**
     *
     * @return
     * The closedTime
     */
    public String getClosedTime() {
        return closedTime;
    }

    /**
     *
     * @param closedTime
     * The closed_time
     */
    public void setClosedTime(String closedTime) {
        this.closedTime = closedTime;
    }

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this);
    }

}