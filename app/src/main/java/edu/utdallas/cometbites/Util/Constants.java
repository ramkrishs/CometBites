package edu.utdallas.cometbites.util;

import edu.utdallas.cometbites.CometbitesAPI;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by twinklesharma on 11/18/16.
 */

public class Constants {

    public static final String BASEURL="http://35.162.230.252:8080/api/v1/cometbites/";

    public static CometbitesAPI getCometbitesAPI() {
        Retrofit retrofit=new Retrofit.Builder().baseUrl(BASEURL).addConverterFactory(GsonConverterFactory.create()).build();
        return retrofit.create(CometbitesAPI.class);
    }
}
