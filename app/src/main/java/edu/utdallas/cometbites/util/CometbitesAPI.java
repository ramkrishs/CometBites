package edu.utdallas.cometbites.util;

import java.util.List;

import edu.utdallas.cometbites.model.Card;
import edu.utdallas.cometbites.model.CreditCard;
import edu.utdallas.cometbites.model.Customer;
import edu.utdallas.cometbites.model.FoodJoint;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;

/**
 * Created by twinklesharma on 11/15/16.
 */

public interface CometbitesAPI{

    @GET("foodjoints")
    Call<List<FoodJoint>> getFoodJointList();

    @GET("foodjoints/{fjid}")
    Call<List<FoodJoint>> getFoodJoint(@Path("fjid") String fjid);

    @FormUrlEncoded
    @POST("users")
    Call<Integer> addCustomer(@Field("firstname") String first_name, @Field("lastname") String last_name, @Field("netid") String netid, @Field("emailid") String emailid, @Field("phone") String phone);

    @FormUrlEncoded
    @POST("users/{netid}/payment")
    Call<ResponseBody> addCard(@Path("netid") String netid, @Field("cardname") String card_name, @Field("cardno") String card_number, @Field("cvv") String cvv, @Field("expdate") String expDate);

}