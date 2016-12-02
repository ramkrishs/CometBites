
        package edu.utdallas.cometbites.model;
        import java.util.ArrayList;
        import java.util.List;
        import javax.annotation.Generated;
        import com.google.gson.annotations.Expose;
        import com.google.gson.annotations.SerializedName;
        import org.apache.commons.lang3.builder.ToStringBuilder;

@Generated("org.jsonschema2pojo")
public class Order {

    @SerializedName("fjID")
    @Expose
    private String fjID;
    @SerializedName("date")
    @Expose
    private String date;
    @SerializedName("total")
    @Expose
    private String total;
    @SerializedName("netid")
    @Expose
    private String netid;
    @SerializedName("invoice")
    @Expose
    private String invoice;
    @SerializedName("items")
    @Expose
    private List<Item> items = new ArrayList<Item>();

    /**
     * No args constructor for use in serialization
     *
     */
    public Order() {
    }

    /**
     *
     * @param total
     * @param items
     * @param invoice
     * @param netid
     * @param date
     * @param fjID
     */
    public Order(String fjID, String date, String total, String netid, String invoice, List<Item> items) {
        this.fjID = fjID;
        this.date = date;
        this.total = total;
        this.netid = netid;
        this.invoice = invoice;
        this.items = items;
    }

    /**
     *
     * @return
     * The fjID
     */
    public String getFjID() {
        return fjID;
    }

    /**
     *
     * @param fjID
     * The fjID
     */
    public void setFjID(String fjID) {
        this.fjID = fjID;
    }

    /**
     *
     * @return
     * The date
     */
    public String getDate() {
        return date;
    }

    /**
     *
     * @param date
     * The date
     */
    public void setDate(String date) {
        this.date = date;
    }

    /**
     *
     * @return
     * The total
     */
    public String getTotal() {
        return total;
    }

    /**
     *
     * @param total
     * The total
     */
    public void setTotal(String total) {
        this.total = total;
    }

    /**
     *
     * @return
     * The netid
     */
    public String getNetid() {
        return netid;
    }

    /**
     *
     * @param netid
     * The netid
     */
    public void setNetid(String netid) {
        this.netid = netid;
    }

    /**
     *
     * @return
     * The invoice
     */
    public String getInvoice() {
        return invoice;
    }

    /**
     *
     * @param invoice
     * The invoice
     */
    public void setInvoice(String invoice) {
        this.invoice = invoice;
    }

    /**
     *
     * @return
     * The items
     */
    public List<Item> getItems() {
        return items;
    }

    /**
     *
     * @param items
     * The items
     */
    public void setItems(List<Item> items) {
        this.items = items;
    }

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this);
    }

}