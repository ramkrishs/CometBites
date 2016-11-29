package edu.utdallas.cometbites.view;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import edu.utdallas.cometbites.R;
import edu.utdallas.cometbites.adapters.ItemCartAdapter;
import edu.utdallas.cometbites.model.LineItem;
import edu.utdallas.cometbites.util.CometbitesAPI;
import edu.utdallas.cometbites.util.Constants;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class YourCartActivity extends AppCompatActivity {
    private static final String PREFS_NAME="my_cart";
    private ListView listView;
    private TextView subtotalView;
    private TextView taxView;
    private TextView totalView;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        final String TAG = "CartActivity";
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_your_cart);

        listView = (ListView) findViewById(R.id.items_in_cart_list_view);
        subtotalView= (TextView) findViewById(R.id.subTotalValue);
        taxView= (TextView) findViewById(R.id.taxValue);
        totalView= (TextView) findViewById(R.id.totalValue);


        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar_cart_page);
        toolbar.setTitle("Your Cart");
        setSupportActionBar(toolbar);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);


        CometbitesAPI cometbitesAPI= Constants.getCometbitesAPI();
        Call<List<LineItem>> callLineItemList=cometbitesAPI.viewOrder();



        callLineItemList.enqueue(new Callback<List<LineItem>>() {
            @Override
            public void onResponse(Call<List<LineItem>> call, Response<List<LineItem>> response) {
                List<LineItem> lineItemList =response.body();

                Log.d(TAG, "onCreate: " + lineItemList);
                ItemCartAdapter itemCartAdapter = new ItemCartAdapter(lineItemList, getApplicationContext());
                listView.setAdapter(itemCartAdapter);

                Log.d(TAG, "onCreate: " + "Successfully set the adapter");
                String subTotal=calculateSubtotal(lineItemList);
                String tax=calculateTax(subTotal);
                String total=calculateTotal(subTotal,tax);
                subtotalView.setText("$ "+subTotal);
                taxView.setText("$ "+tax);
                totalView.setText("$ "+total);

            }

            @Override
            public void onFailure(Call<List<LineItem>> call, Throwable t) {

            }
        });

        TextView cancelButton= (TextView) findViewById(R.id.cancelOrderButton);
        cancelButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(getApplicationContext(),"Order Cancelled",Toast.LENGTH_SHORT).show();
                Intent i=new Intent(YourCartActivity.this, BrowseFoodJointsActivity.class);
                startActivity(i);
                finish();
            }
        });

        Button checkoutButton= (Button)findViewById(R.id.checkoutButton);
        checkoutButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i=new Intent(YourCartActivity.this, OrderConfirmationActivity.class);
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


    private String calculateSubtotal(List<LineItem> list)
    {
        Double ip_subtotal=0.0;

            for(LineItem lineItem : list)
            {
                ip_subtotal+=lineItem.getTotal();
            }

        String subtotal=String.format("%.2f",ip_subtotal);
        return subtotal;
    }

    private String calculateTax(String ip_subtotal)
    {

        double subtotal=Double.parseDouble(ip_subtotal);

        String tax=String.format("%.2f",0.0825*subtotal);
        return tax;

    }

    private String calculateTotal(String ip_subtotal, String ip_tax)
    {

        double subtotal=Double.parseDouble(ip_subtotal);
        double tax=Double.parseDouble(ip_tax);

        String total=String.format("%.2f",subtotal+tax);
        return total;
    }
}
