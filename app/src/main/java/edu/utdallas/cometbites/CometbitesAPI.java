package edu.utdallas.cometbites;

import java.util.List;

import edu.utdallas.cometbites.model.FoodJoint;
import retrofit2.Call;
import retrofit2.http.GET;

/**
 * Created by twinklesharma on 11/15/16.
 */

public interface CometbitesAPI{

    @GET("foodjoints")
    Call<List<FoodJoint>> getFoodJointList();



}