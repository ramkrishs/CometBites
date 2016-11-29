package edu.utdallas.cometbites.util;

import java.util.List;

import edu.utdallas.cometbites.model.Card;
import edu.utdallas.cometbites.model.CreditCard;
import edu.utdallas.cometbites.model.Customer;
import edu.utdallas.cometbites.model.FoodJoint;
import edu.utdallas.cometbites.model.Item;
import edu.utdallas.cometbites.model.LineItem;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;

/**
 * Created by twinklesharma on 11/15/16.
 */

public interface CometbitesAPI{

    @GET("register/{netid}")
    Call<List<FoodJoint>> getFoodJointList(@Path("netid") String netid);

    @GET("register/foodJoint/{fjid}")
    Call<List<Item>> getFoodJoint(@Path("fjid") String fjid);

    @FormUrlEncoded
    @POST("users")
    Call<Integer> addCustomer(@Field("firstname") String first_name, @Field("lastname") String last_name, @Field("netid") String netid, @Field("emailid") String emailid, @Field("phone") String phone);

    @FormUrlEncoded
    @POST("users/{netid}/payment")
    Call<ResponseBody> addCard(@Path("netid") String netid, @Field("cardname") String card_name, @Field("cardno") String card_number, @Field("cvv") String cvv, @Field("expdate") String expDate);


    @FormUrlEncoded
    @PUT("register/item/{itemid}")
    Call<String> selectItem(@Path("itemid") String itemid, @Field("name") String itemName, @Field("description") String desc, @Field("price") String price);

    @FormUrlEncoded
    @POST("register/item/{itemid}")
    Call<String> informQuantity(@Path("itemid") String itemid, @Field("name") String itemName, @Field("description") String desc, @Field("price") String price, @Field("quantity") String quantity);


    @GET("register/order")
    Call<List<LineItem>> viewOrder();
}