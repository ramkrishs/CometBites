package edu.utdallas.cometbites.view;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import java.util.List;

import edu.utdallas.cometbites.R;
import edu.utdallas.cometbites.adapters.OrderAdapter;
import edu.utdallas.cometbites.adapters.PaymentMethodsAdapter;
import edu.utdallas.cometbites.model.Order;
import edu.utdallas.cometbites.model.PaymentOptions;
import edu.utdallas.cometbites.util.CometbitesAPI;
import edu.utdallas.cometbites.util.Constants;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class PaymentsActivity extends AppCompatActivity {
    Button addPaymentMethodButton;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_payments);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar_payment_list_inner);
        toolbar.setTitle("List of Payment Methods Added");
        setSupportActionBar(toolbar);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        addPaymentMethodButton = (Button) findViewById(R.id.addPaymentMethodButton);



        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        String netid=user.getEmail().substring(0,9);
        CometbitesAPI cometbitesAPI= Constants.getCometbitesAPI();
        Call<List<PaymentOptions>> call=cometbitesAPI.getPaymentMethods(netid);
        call.enqueue(new Callback<List<PaymentOptions>>() {
            @Override
            public void onResponse(Call<List<PaymentOptions>> call, Response<List<PaymentOptions>> response) {
                List<PaymentOptions> paymentOptionsList = response.body();
                ListView listView = (ListView) findViewById(R.id.listViewPaymentList);
                PaymentMethodsAdapter paymentMethodsAdapter=new PaymentMethodsAdapter(getApplicationContext(), paymentOptionsList );
                listView.setAdapter(paymentMethodsAdapter);


            }

            @Override
            public void onFailure(Call<List<PaymentOptions>> call, Throwable t) {
                Toast.makeText(PaymentsActivity.this, "t" + t.toString(), Toast.LENGTH_SHORT).show();
            }
        });


        addPaymentMethodButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(PaymentsActivity.this, AddPaymentActivity.class);
                i.putExtra("parent", "addPayment");
                startActivity(i);
                finish();
            }
        });



    }



}
