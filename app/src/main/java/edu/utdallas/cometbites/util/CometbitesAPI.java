package edu.utdallas.cometbites.util;

import java.util.List;

import edu.utdallas.cometbites.model.FoodJoint;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

/**
 * Created by twinklesharma on 11/15/16.
 */

public interface CometbitesAPI{

    @GET("foodjoints")
    Call<List<FoodJoint>> getFoodJointList();

    @GET("foodjoints/{fjid}")
    Call<List<FoodJoint>> getFoodJoint(@Path("fjid") String fjid);



}