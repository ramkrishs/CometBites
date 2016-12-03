package edu.utdallas.cometbites.view;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import java.util.List;

import edu.utdallas.cometbites.R;
import edu.utdallas.cometbites.adapters.OrderAdapter;
import edu.utdallas.cometbites.adapters.OrderDescriptionAdapter;
import edu.utdallas.cometbites.model.FoodJoint;
import edu.utdallas.cometbites.model.Order;
import edu.utdallas.cometbites.util.CometbitesAPI;
import edu.utdallas.cometbites.util.Constants;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class OrderDescriptionActivity extends AppCompatActivity {


    private TextView orderNumberView;
    private TextView orderDateView;
    private TextView orderFoodJointNameView;
    private TextView orderTotalView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order_description);

        final Intent intent = getIntent();
        Bundle bundle = intent.getExtras();
        final Order order = (Order) bundle.get("order");

        orderNumberView= (TextView) findViewById(R.id.orderNumberValue);
        orderDateView= (TextView) findViewById(R.id.orderDateValue);
        orderFoodJointNameView=(TextView) findViewById(R.id.fjNameValue);
        orderTotalView=(TextView) findViewById(R.id.total_value);



        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        CometbitesAPI cometbitesAPI = Constants.getCometbitesAPI();



        Call<List<FoodJoint>> getFoodJoints = cometbitesAPI.getFoodJointList(user.getUid(), user.getEmail().substring(0, 9));
            getFoodJoints.enqueue(new Callback<List<FoodJoint>>() {
                @Override
                public void onResponse(Call<List<FoodJoint>> call, Response<List<FoodJoint>> response) {
                    FoodJoint foodJoint = response.body().get(Integer.parseInt(order.getFjID()) - 1);
                    orderNumberView.setText(order.getInvoice());
                    orderDateView.setText(order.getDate());
                    orderFoodJointNameView.setText(foodJoint.getName());
                    orderTotalView.setText(order.getTotal());
                    ListView listView = (ListView) findViewById(R.id.list_view_orders_placed);

                    OrderDescriptionAdapter orderDescriptionAdapter = new OrderDescriptionAdapter(order, getBaseContext());
                    listView.setAdapter(orderDescriptionAdapter);




                }

                @Override
                public void onFailure(Call<List<FoodJoint>> call, Throwable t) {
                    Toast.makeText(OrderDescriptionActivity.this, "Error: " + t, Toast.LENGTH_SHORT).show();
                }
            });



        Toast.makeText(this, order.toString(), Toast.LENGTH_SHORT).show();




    }
}
