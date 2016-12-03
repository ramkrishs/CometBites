package edu.utdallas.cometbites.util;

import java.util.List;


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
import retrofit2.http.POST;
import retrofit2.http.Path;

/**
 * Created by twinklesharma on 11/15/16.
 */

public interface CometbitesAPI{



    // endpoints for getting the user details and creating a new user
    @GET("users/{netid}")
    Call<List<Customer>> getUserDetails(@Path("netid") String netid);

    @GET("users/{netid}/payment")
    Call<List<PaymentOptions>> getPaymentMethods(@Path("netid") String netid);

    @FormUrlEncoded
    @POST("users")
    Call<Integer> addCustomer(@Field("firstname") String first_name, @Field("lastname") String last_name, @Field("netid") String netid, @Field("emailid") String emailid, @Field("phone") String phone);

    @GET("auth/{netid}/send_token")
    Call<ResponseBody> sendToken(@Path("netid") String netid);

    @FormUrlEncoded
    @POST("auth/{netid}/verify_phone")
    Call<ResponseBody> verifyPhoneByCode(@Path("netid") String netid, @Field("code") String code);

    @FormUrlEncoded
    @POST("users/{netid}/payment")
    Call<ResponseBody> addPaymentMethod(@Path("netid") String netid, @Field("cardname") String card_name, @Field("cardno") String card_number, @Field("cvv") String cvv, @Field("expdate") String expDate);

    @GET("orders/user/{netid}")
    Call<List<Order>> getOrdersForUser(@Path("netid") String netid);




    // endpoints for getting food joints details
    @GET("register/{netid}")
    Call<List<FoodJoint>> getFoodJointList(@Header("UID") String UID,  @Path("netid") String netid);

    @GET("register/foodjoint/{fjid}")
    Call<List<Item>> getFoodJoint(@Path("fjid") String fjid);


    //endpoints needed for cart and placing orders
    @FormUrlEncoded
    @POST("register/addquantity/{itemid}")
    Call<String> addItemToCart(@Header("UID") String UID, @Path("itemid") String itemid, @Field("name") String itemName, @Field("description") String desc, @Field("price") String price, @Field("quantity") String quantity, @Field("fjID") String fjID);

    @GET("register/order")
    Call<List<LineItem>> viewCartItems(@Header("UID") String UID);

    @GET("register/checkout")
    Call<List<PaymentOptions>> getPaymentOptionsWhileCheckout(@Header("UID") String UID);

    @FormUrlEncoded
    @POST("register/order/payment")
    Call<Ticket> placeOrderWithCardDetails(@Header("UID") String UID, @Field("cardname") String cardname, @Field("cardno") String cardno, @Field("cvv") String cvv, @Field("expdate") String expdate);

    @GET("register/order/eticket")
    Call<Ticket> getETicket(@Header("UID") String UID);

}