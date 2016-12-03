package edu.utdallas.cometbites.util;

import java.util.List;

import edu.utdallas.cometbites.model.Card;
import edu.utdallas.cometbites.model.CreditCard;
import edu.utdallas.cometbites.model.Customer;
import edu.utdallas.cometbites.model.FoodJoint;
import edu.utdallas.cometbites.model.Item;
import edu.utdallas.cometbites.model.LineItem;
import edu.utdallas.cometbites.model.Order;
import edu.utdallas.cometbites.model.PaymentOptions;
import edu.utdallas.cometbites.model.Ticket;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Headers;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;

/**
 * Created by twinklesharma on 11/15/16.
 */

public interface CometbitesAPI{
    @GET("auth/{netid}/send_token")
    Call<ResponseBody> sendToken(@Path("netid") String netid);

    @FormUrlEncoded
    @POST("auth/{netid}/verify_phone")
    Call<ResponseBody> verifyPhone(@Path("netid") String netid, @Field("code") String code);

    @GET("register/{netid}")
    Call<List<FoodJoint>> getFoodJointList(@Header("UID") String UID,  @Path("netid") String netid);


    @GET("register/foodjoint/{fjid}")
    Call<List<Item>> getFoodJoint(@Path("fjid") String fjid);

    @FormUrlEncoded
    @POST("users")
    Call<Integer> addCustomer(@Field("firstname") String first_name, @Field("lastname") String last_name, @Field("netid") String netid, @Field("emailid") String emailid, @Field("phone") String phone);

    @FormUrlEncoded
    @POST("users/{netid}/payment")
    Call<ResponseBody> addCard(@Path("netid") String netid, @Field("cardname") String card_name, @Field("cardno") String card_number, @Field("cvv") String cvv, @Field("expdate") String expDate);


    @FormUrlEncoded
    @POST("register/addquantity/{itemid}")
    Call<String> informQuantity(@Header("UID") String UID,@Path("itemid") String itemid, @Field("name") String itemName, @Field("description") String desc, @Field("price") String price, @Field("quantity") String quantity, @Field("fjID") String fjID);

    @GET("register/order")
    Call<List<LineItem>> viewOrder(@Header("UID") String UID);

    @GET("users/{netid}/payment")
    Call<List<PaymentOptions>> getPaymentMethods(@Path("netid") String netid);

    @GET("register/checkout")
    Call<List<PaymentOptions>> checkOut(@Header("UID") String UID);

    @FormUrlEncoded
    @POST("register/order/payment")
    Call<Ticket> placeOrder(@Header("UID") String UID,@Field("cardname") String cardname,@Field("cardno") String cardno, @Field("cvv") String cvv, @Field("expdate") String expdate);

    @GET("register/order/eticket")
    Call<Ticket> getETicket(@Header("UID") String UID);

    @GET("orders/user/{netid}")
    Call<List<Order>> getOrderByNetid(@Path("netid") String netid);

}