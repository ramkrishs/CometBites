package edu.utdallas.cometbites;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Build;
import android.support.annotation.RequiresApi;
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

import edu.utdallas.cometbites.models.CartItem;

public class YourCartActivity extends AppCompatActivity {
    private static final String PREFS_NAME="my_cart";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        final String TAG = "CartActivity";
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_your_cart);

        ListView listView = (ListView) findViewById(R.id.items_in_cart_list_view);
        TextView subtotalView= (TextView) findViewById(R.id.subTotalValue);
        TextView taxView= (TextView) findViewById(R.id.taxValue);
        TextView totalView= (TextView) findViewById(R.id.totalValue);


        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar_cart_page);
        toolbar.setTitle("Your Cart");
        setSupportActionBar(toolbar);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);


        SharedPreferences myPrefs = getSharedPreferences(PREFS_NAME, 0);
        Map<String,String> cart_items_map= (Map<String, String>) myPrefs.getAll();
        Log.d(TAG, "Cart items map: "+cart_items_map);

        List<CartItem> cartItemList = new LinkedList<>();

        for(Map.Entry<String,String> items:cart_items_map.entrySet()){
            String name=items.getKey();
            String quantity=items.getValue().split(" ")[0];
            String price=items.getValue().split(" ")[1];

            String new_price=calculateItemPrice(quantity,price);
            CartItem cartItem=new CartItem(quantity,name,new_price);
            cartItemList.add(cartItem);

        }



        Log.d(TAG, "onCreate: " + cartItemList);
        ItemCartAdapter itemCartAdapter = new ItemCartAdapter(cartItemList, getApplicationContext());
        listView.setAdapter(itemCartAdapter);

        Log.d(TAG, "onCreate: " + "Successfully set the adapter");

        String subTotal=calculateSubtotal(cartItemList);
        String tax=calculateTax(subTotal);
        String total=calculateTotal(subTotal,tax);
        subtotalView.setText(subTotal);
        taxView.setText(tax);
        totalView.setText(total);


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


    private String calculateItemPrice(String ip_quantity, String ip_price)
    {
        int qty=Integer.parseInt(ip_quantity);
        ip_price=ip_price.substring(1);
        double price=Double.parseDouble(ip_price);

        String item_price=String.format("%.2f", qty*price);
        return "$ "+item_price;

    }

    private String calculateSubtotal(List<CartItem> list)
    {
        Double ip_subtotal=0.0;

            for(CartItem cartItem: list)
            {
                ip_subtotal+=Double.parseDouble(cartItem.getPrice().substring(1));
            }

        String subtotal=String.format("%.2f",ip_subtotal);
        return "$ "+subtotal;
    }

    private String calculateTax(String ip_subtotal)
    {
        ip_subtotal=ip_subtotal.substring(1);
        double subtotal=Double.parseDouble(ip_subtotal);

        String tax=String.format("%.2f",0.0825*subtotal);
        return "$ "+tax;

    }

    private String calculateTotal(String ip_subtotal, String ip_tax)
    {
        ip_subtotal=ip_subtotal.substring(1);
        ip_tax=ip_tax.substring(1);
        double subtotal=Double.parseDouble(ip_subtotal);
        double tax=Double.parseDouble(ip_tax);

        String total=String.format("%.2f",subtotal+tax);
        return "$ "+total;
    }
}
