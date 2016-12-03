package edu.utdallas.cometbites.view;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import java.util.LinkedList;
import java.util.List;

import edu.utdallas.cometbites.R;
import edu.utdallas.cometbites.adapters.ItemCartAdapter;
import edu.utdallas.cometbites.model.FoodJoint;
import edu.utdallas.cometbites.model.Item;
import edu.utdallas.cometbites.model.LineItem;
import edu.utdallas.cometbites.util.CometbitesAPI;
import edu.utdallas.cometbites.util.Constants;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class YourCartActivity extends AppCompatActivity {
    private static final String PREFS_NAME = "my_cart";
    private ListView listView;
    private TextView subtotalView;
    private TextView taxView;
    private TextView totalView;
    private TextView restaurantView;
    private TextView waitTimeView;
    FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        final String TAG = "CartActivity";
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_your_cart);

        listView = (ListView) findViewById(R.id.items_in_cart_list_view);
        subtotalView = (TextView) findViewById(R.id.subTotalValue);
        taxView = (TextView) findViewById(R.id.taxValue);
        totalView = (TextView) findViewById(R.id.totalValue);
        restaurantView = (TextView) findViewById(R.id.restaurant_name);
        waitTimeView = (TextView) findViewById(R.id.restaurant_wait_time);
        final Button checkoutButton = (Button) findViewById(R.id.checkoutButton);


        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar_cart_page);
        toolbar.setTitle("Your Cart");
        setSupportActionBar(toolbar);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        final Intent intent = getIntent();
        Bundle bundle = intent.getExtras();
        final String fjid = bundle.getString("fjid");


        CometbitesAPI cometbitesAPI = Constants.getCometbitesAPI();
        Call<List<LineItem>> callLineItemList = cometbitesAPI.viewCartItems(user.getUid());

        Call<List<FoodJoint>> getFoodJoints = cometbitesAPI.getFoodJointList(user.getUid(), user.getEmail().substring(0, 9));


        getFoodJoints.enqueue(new Callback<List<FoodJoint>>() {
            @Override
            public void onResponse(Call<List<FoodJoint>> call, Response<List<FoodJoint>> response) {
                FoodJoint foodJoint = response.body().get(Integer.parseInt(fjid) - 1);
                restaurantView.setText(foodJoint.getName());
                waitTimeView.setText("Estimated wait time: " + foodJoint.getWaitTime() + " min");
            }

            @Override
            public void onFailure(Call<List<FoodJoint>> call, Throwable t) {
                Toast.makeText(YourCartActivity.this, "Error: " + t, Toast.LENGTH_SHORT).show();
            }
        });


        callLineItemList.enqueue(new Callback<List<LineItem>>() {
            @Override
            public void onResponse(Call<List<LineItem>> call, Response<List<LineItem>> response) {
                List<LineItem> lineItemList = response.body();


                if(lineItemList==null){
                    lineItemList = new LinkedList<LineItem>();
                    lineItemList.add(new LineItem(0, new Item("No items in Cart", "0")));
                    checkoutButton.setEnabled(false);
                }
                else{
                    checkoutButton.setEnabled(true);
                }
                    Log.d(TAG, "onCreate: " + lineItemList);

                ItemCartAdapter itemCartAdapter = new ItemCartAdapter(lineItemList, getApplicationContext());
                    listView.setAdapter(itemCartAdapter);

                    Log.d(TAG, "onCreate: " + "Successfully set the adapter");
                    String subTotal = calculateSubtotal(lineItemList);
                    String tax = calculateTax(subTotal);
                    String total = calculateTotal(subTotal, tax);
                    subtotalView.setText("$ " + subTotal);
                    taxView.setText("$ " + tax);
                    totalView.setText("$ " + total);
            }

            @Override
            public void onFailure(Call<List<LineItem>> call, Throwable t) {

            }
        });

        TextView cancelButton = (TextView) findViewById(R.id.cancelOrderButton);
        cancelButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(getApplicationContext(), "Order Cancelled", Toast.LENGTH_SHORT).show();
                Intent i = new Intent(YourCartActivity.this, BrowseFoodJointsActivity.class);
                startActivity(i);
                finish();
            }
        });

        checkoutButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(YourCartActivity.this, OrderConfirmationActivity.class);
                startActivity(i);
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


    private String calculateSubtotal(List<LineItem> list) {
        Double ip_subtotal = 0.0;

        for (LineItem lineItem : list) {
            ip_subtotal += lineItem.getTotal();
        }

        String subtotal = String.format("%.2f", ip_subtotal);
        return subtotal;
    }

    private String calculateTax(String ip_subtotal) {

        double subtotal = Double.parseDouble(ip_subtotal);

        String tax = String.format("%.2f", 0.0825 * subtotal);
        return tax;

    }

    private String calculateTotal(String ip_subtotal, String ip_tax) {

        double subtotal = Double.parseDouble(ip_subtotal);
        double tax = Double.parseDouble(ip_tax);

        String total = String.format("%.2f", subtotal + tax);
        return total;
    }
}
