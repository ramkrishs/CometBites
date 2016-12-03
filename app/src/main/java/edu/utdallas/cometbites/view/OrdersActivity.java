package edu.utdallas.cometbites.view;

import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import java.util.List;

import edu.utdallas.cometbites.R;
import edu.utdallas.cometbites.adapters.OrderAdapter;
import edu.utdallas.cometbites.model.Order;
import edu.utdallas.cometbites.util.CometbitesAPI;
import edu.utdallas.cometbites.util.Constants;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class OrdersActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_orders);


        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar_orders);
        toolbar.setTitle("List of Orders placed");
        setSupportActionBar(toolbar);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        String netid=user.getEmail().substring(0,9);
        CometbitesAPI cometbitesAPI= Constants.getCometbitesAPI();
        Call<List<Order>> call=cometbitesAPI.getOrderByNetid(netid);
        call.enqueue(new Callback<List<Order>>() {
            @Override
            public void onResponse(Call<List<Order>> call, Response<List<Order>> response) {
                List<Order> orderList = response.body();
                ListView listView = (ListView) findViewById(R.id.viewOrderList);
                OrderAdapter orderAdapter=new OrderAdapter(orderList,getApplicationContext());
                listView.setAdapter(orderAdapter);

            }

            @Override
            public void onFailure(Call<List<Order>> call, Throwable t) {
                Toast.makeText(OrdersActivity.this, "t" + t.toString(), Toast.LENGTH_SHORT).show();
            }
        });

        ListView listView = (ListView) findViewById(R.id.viewOrderList);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Order order = (Order) view.getTag();
                Intent intent=new Intent(OrdersActivity.this,OrderDescriptionActivity.class);
                intent.putExtra("order", order);
                startActivity(intent);
            }
        });

    }
}
