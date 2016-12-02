package edu.utdallas.cometbites.model;

        import javax.annotation.Generated;
        import com.google.gson.annotations.Expose;
        import com.google.gson.annotations.SerializedName;
        import org.apache.commons.lang3.builder.ToStringBuilder;

@Generated("org.jsonschema2pojo")
public class PaymentOptions {

    @SerializedName("cardname")
    @Expose
    private String cardname;
    @SerializedName("cardno")
    @Expose
    private String cardno;
    @SerializedName("cvv")
    @Expose
    private String cvv;
    @SerializedName("expdate")
    @Expose
    private String expdate;

    /**
     * No args constructor for use in serialization
     *
     */
    public PaymentOptions() {
    }

    /**
     *
     * @param cardname
     * @param cvv
     * @param expdate
     * @param cardno
     */
    public PaymentOptions(String cardname, String cardno, String cvv, String expdate) {
        this.cardname = cardname;
        this.cardno = cardno;
        this.cvv = cvv;
        this.expdate = expdate;
    }

    /**
     *
     * @return
     * The cardname
     */
    public String getCardname() {
        return cardname;
    }

    /**
     *
     * @param cardname
     * The cardname
     */
    public void setCardname(String cardname) {
        this.cardname = cardname;
    }

    /**
     *
     * @return
     * The cardno
     */
    public String getCardno() {
        return cardno;
    }

    /**
     *
     * @param cardno
     * The cardno
     */
    public void setCardno(String cardno) {
        this.cardno = cardno;
    }

    /**
     *
     * @return
     * The cvv
     */
    public String getCvv() {
        return cvv;
    }

    /**
     *
     * @param cvv
     * The cvv
     */
    public void setCvv(String cvv) {
        this.cvv = cvv;
    }

    /**
     *
     * @return
     * The expdate
     */
    public String getExpdate() {
        return expdate;
    }

    /**
     *
     * @param expdate
     * The expdate
     */
    public void setExpdate(String expdate) {
        this.expdate = expdate;
    }

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this);
    }

}