package edu.utdallas.cometbites;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.LinkedList;
import java.util.List;

import edu.utdallas.cometbites.models.CartItem;

public class YourCartActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_your_cart);

        ListView listView = (ListView) findViewById(R.id.items_in_cart_list_view);


        List<CartItem> cartItemList = new LinkedList<>();
        cartItemList.add(new CartItem("1X", "Subway Club", "$6.49"));
        ItemCartAdapter itemCartAdapter = new ItemCartAdapter(cartItemList, getApplicationContext());
        listView.setAdapter(itemCartAdapter);


        TextView cancelButton= (TextView) findViewById(R.id.cancelOrderButton);
        cancelButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(getApplicationContext(),"Order Cancelled",Toast.LENGTH_SHORT).show();
                Intent i=new Intent(YourCartActivity.this, BrowseFoodJointsActivity.class);
                startActivity(i);
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
}
