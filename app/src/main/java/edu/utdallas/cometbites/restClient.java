package edu.utdallas.cometbites;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.LinkedList;
import java.util.List;

import edu.utdallas.cometbites.models.FoodJoint;
import edu.utdallas.cometbites.models.Item;
import retrofit2.Call;
import retrofit2.http.GET;

/**
 * Created by twinklesharma on 11/15/16.
 */

public class restClient {

    public static final String URL = "http://cometbites.store";

    public static boolean authLoginDetails(String username, String password) {
        return true;
    }


    public static String getWaitTime(String foodJoint) {
        //call the callRESTService method and process the JSON we get
        return null;
    }


    public static List<FoodJoint> getFoodJointsList() {
        //call the callRESTService method and process the JSON we get
        JSONArray jsonArray = callRESTService("/foodjoins");
        List<FoodJoint> foodJointList = new LinkedList<>();

        try {
            for (int i = 0; i < jsonArray.length(); i++) {
                JSONObject jointInfo = jsonArray.getJSONObject(i);
                String logoURL = jointInfo.getString("logo");
                String wait_time = jointInfo.getString("wait_time") + " min";

                FoodJoint fj = new FoodJoint(logoURL, wait_time);
                foodJointList.add(fj);
            }

        } catch (JSONException ex) {
            ex.printStackTrace();
        }

        return foodJointList;
    }

    public static List<Item> getItemsList(String logoURL) {
        //call the callRESTService method and process the JSON we get
        JSONArray jsonArray = callRESTService("/foodjoins");
        List<Item> itemsList = new LinkedList<>();

        try {
            for (int i = 0; i < jsonArray.length(); i++) {
                JSONObject jointInfo = jsonArray.getJSONObject(i);
                String currURL = jointInfo.getString("logo");
                if (logoURL.equals(currURL)) {
                    JSONArray menuArray = jointInfo.getJSONArray("menu");
                    for (int j = 0; j < menuArray.length(); j++) {
                        JSONObject currItem = menuArray.getJSONObject(j);
                        String price = currItem.getString("price");
                        String name = currItem.getString("name");
                        String imageURL = currItem.getString("image");
                        String description = currItem.getString("description");
                        Item item = new Item(name, price, imageURL, description);
                        itemsList.add(item);
                    }
                } else {
                    continue;
                }
            }

        } catch (JSONException ex) {
            ex.printStackTrace();
        }
        return itemsList;
    }

    public static JSONArray callRESTService(String uri) {
        //return static strings for now. Depending on input.
        //if input is /foodjoints then return the json you expect from rest service.
        JSONArray jsonArray = null;

        try {
            jsonArray = new JSONArray("[{\"fjID\":1,\"wait_time\":\"15\",\"name\":\"Panda Express\",\"logo\":\"http://cometbites.com/uploads/panda.jpg\",\"open_time\":\"1479164897171\",\"closed_time\":\"1479164897171\",\"menu\":[{\"image\":\"http://cometbites.com/uploads/orangechicken.jpg\",\"price\":\"$10.49\",\"qty\":\"20\",\"name\":\"Orange Chicken\",\"description\":\"Our signature dish. Crispy chicken wok-tossed in a sweet and spicy orange sauce.\",\"id\":\"1\"},{\"image\":\"http://cometbites.com/uploads/friedrice.jpg\",\"price\":\"$8.49\",\"qty\":\"20\",\"name\":\"fried-rice\",\"description\":\"Prepared steamed white rice with soy sauce, eggs, peas, carrots and green onions.\",\"id\":\"2\"}]},{\"fjID\":2,\"wait_time\":\"11\",\"name\":\"Subway\",\"logo\":\"http://cometbites.com/uploads/subway.jpg\",\"open_time\":\"1479164891171\",\"closed_time\":\"1479164898171\",\"menu\":[{\"image\":\"http://cometbites.com/uploads/sub1.png\",\"price\":\"$7.49\",\"qty\":\"20\",\"name\":\"Subway Club\",\"description\":\"Tender sliced turkey breast, lean roast beef and tasty Black Forest ham come together with your choice of fresh veggies for a low-fat flavor fiesta. Try it today on freshly baked bread and experience all the deliciousity for 6 grams of fat.\",\"id\":\"1\"},{\"image\":\"http://cometbites.com/uploads/sub2.png\",\"price\":\"$3.49\",\"qty\":\"20\",\"name\":\"Veggie Delite\",\"description\":\" The Veggie Delite® is tangible proof that a sandwich can be high in flavor without being high in fat. Try a delicious combination of lettuce, tomatoes, green peppers, cucumbers and onions with your choice of fat-free condiments on freshly baked bread.\",\"id\":\"2\"}]}]");
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return jsonArray;

    }
}

interface CometbitesAPI{

    @GET("api/v1/cometbites/foodjoints")
    Call<List<FoodJoint>> getFoodJointList();

}