package edu.utdallas.cometbites.view;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.koushikdutta.urlimageviewhelper.UrlImageViewHelper;

import java.util.List;

import edu.utdallas.cometbites.R;
import edu.utdallas.cometbites.model.FoodJoint;
import edu.utdallas.cometbites.model.Item;
import edu.utdallas.cometbites.util.CometbitesAPI;
import edu.utdallas.cometbites.util.Constants;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ItemDescriptionActivity extends AppCompatActivity {

    private static final String PREFS_NAME = "my_cart";
    FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_item_description);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar_item_description);
        toolbar.setTitle("Item Description");
        setSupportActionBar(toolbar);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        final ImageView itemImage = (ImageView) findViewById(R.id.item_image1);
        final TextView itemNameTextView = (TextView) findViewById(R.id.item_name1);
        final TextView priceTextView = (TextView) findViewById(R.id.item_price);
        final TextView itemDescriptionTextView = (TextView) findViewById(R.id.item_description1);

        Intent intent = getIntent();
        Bundle bundle = intent.getExtras();

        final String fjid=bundle.getString("fjid");
        final String itemid=bundle.getString("itemId");

        final CometbitesAPI cometbitesAPI= Constants.getCometbitesAPI();

        Call<List<Item>> call=cometbitesAPI.getFoodJoint(fjid);
        call.enqueue(new Callback<List<Item>>() {
            @Override
            public void onResponse(Call<List<Item>> call, Response<List<Item>> response) {
                Item item = response.body().get(Integer.parseInt(itemid) -1);
                UrlImageViewHelper.setUrlDrawable(itemImage, item.getImage(),R.drawable.image_not_available);
                itemNameTextView.setText(item.getName());
                priceTextView.setText(Constants.UNIT +  String.valueOf(item.getPrice()));
                itemDescriptionTextView.setText(item.getDescription());



                Call<String> call1=cometbitesAPI.selectItem(user.getUid(),item.getId(),item.getName(),item.getDescription(),item.getPrice(),fjid);
                call1.enqueue(new Callback<String>() {
                    @Override
                    public void onResponse(Call<String> call, Response<String> response) {

                    }

                    @Override
                    public void onFailure(Call<String> call, Throwable t) {

                    }
                });

            }

            @Override
            public void onFailure(Call<List<Item>> call, Throwable t) {
                Toast.makeText(ItemDescriptionActivity.this, "Error: "+t.toString(), Toast.LENGTH_SHORT).show();
            }
        });

        final String logoURL = bundle.getString("logoURL");


        ImageButton minusButton = (ImageButton) findViewById(R.id.minus_button);
        ImageButton plusButton = (ImageButton) findViewById(R.id.add_button);

        minusButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                TextView quantityTextView = (TextView) findViewById(R.id.quantityTextView);
                int quantity = Integer.parseInt(quantityTextView.getText().toString());
                if (quantity > 1) {
                    quantityTextView.setText(String.valueOf(--quantity));
                }
            }
        });


        plusButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                TextView quantityTextView = (TextView) findViewById(R.id.quantityTextView);
                int quantity = Integer.parseInt(quantityTextView.getText().toString());
                quantityTextView.setText(String.valueOf(++quantity));
            }
        });


        Button addToCartButton = (Button) findViewById(R.id.addToCartButton);
        addToCartButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                TextView quantityTextView = (TextView) findViewById(R.id.quantityTextView);
                String item_quantity = quantityTextView.getText().toString();

                String item_price = priceTextView.getText().toString().substring(1);

                String item_name = itemNameTextView.getText().toString();
                String item_desc=itemDescriptionTextView.getText().toString();

                if(!item_quantity.equals("0")){
                    CometbitesAPI cometbitesAPI1=Constants.getCometbitesAPI();
                    Call<String> callsubtotal=cometbitesAPI1.informQuantity(user.getUid(),itemid,item_name,item_desc,item_price,item_quantity);
                    callsubtotal.enqueue(new Callback<String>() {
                        @Override
                        public void onResponse(Call<String> call, Response<String> response) {

                        }

                        @Override
                        public void onFailure(Call<String> call, Throwable t) {

                        }
                    });
                }






//                SharedPreferences myPrefs = getSharedPreferences(PREFS_NAME, 0);
//                SharedPreferences.Editor editor = myPrefs.edit();
//
//                //writing the fjid to a shared pref
//                SharedPreferences jointName = getSharedPreferences("jointName", 0);
//                SharedPreferences.Editor ed = jointName.edit();
//                ed.clear();
//                ed.putString("fjid", fjid);
//                ed.apply();
//
//                Log.d("Item Description", "onClick: " + myPrefs.getAll().toString());
//
//
//                if (myPrefs.contains(item_name)) {
//                    //add quantity to the previous quantity stored in item_name
//                    String itemInfo = myPrefs.getString(item_name, null);
//                    int stored_quantity = Integer.parseInt(itemInfo.split(" ")[0]);
//
//                    Log.d("Item Description", "onClick: qty" + stored_quantity);
//                    int selected_new_quantity = Integer.parseInt(item_quantity);
//                    selected_new_quantity += stored_quantity;
//                    item_quantity = String.valueOf(selected_new_quantity);
//                }
//
//
//                String itemInfo = item_quantity + " " + item_price;
//
//
//                editor.putString(item_name, itemInfo);
//                editor.apply();
//                Log.d("Item Description", "onClick: " + myPrefs.getAll().toString());
                Toast.makeText(ItemDescriptionActivity.this, item_name + " is successfully added to the cart", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(ItemDescriptionActivity.this, BrowseItemsActivity.class);
                intent.putExtra("fjid", fjid);
                startActivity(intent);


            }
        });
    }

    @Override
    public boolean onOptionsItemSelected(android.view.MenuItem item) {
        // handle arrow click here
        if (item.getItemId() == android.R.id.home) {
            finish(); // close this activity and return to preview activity (if there is any)
        }

        return super.onOptionsItemSelected(item);
    }
}
