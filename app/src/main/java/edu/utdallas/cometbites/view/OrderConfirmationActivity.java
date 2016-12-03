package edu.utdallas.cometbites.view;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import java.util.List;

import edu.utdallas.cometbites.R;
import edu.utdallas.cometbites.adapters.PaymentAdapter;
import edu.utdallas.cometbites.model.PaymentOptions;
import edu.utdallas.cometbites.model.Ticket;
import edu.utdallas.cometbites.util.CometbitesAPI;
import edu.utdallas.cometbites.util.Constants;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class OrderConfirmationActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order_confirmation);

        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();


        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar_order_payment_page);
        toolbar.setTitle("Enter Payment Details");
        setSupportActionBar(toolbar);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        CometbitesAPI cometbitesAPI = Constants.getCometbitesAPI();
        Call<List<PaymentOptions>> call = cometbitesAPI.getPaymentOptionsWhileCheckout(user.getUid());
        final Spinner spinner = (Spinner) findViewById(R.id.cards_spinner);

        call.enqueue(new Callback<List<PaymentOptions>>() {
            @Override
            public void onResponse(Call<List<PaymentOptions>> call, Response<List<PaymentOptions>> response) {

                PaymentAdapter paymentAdapter = new PaymentAdapter(getApplicationContext(), response.body());
                spinner.setAdapter(paymentAdapter);

            }

            @Override
            public void onFailure(Call<List<PaymentOptions>> call, Throwable t) {

            }
        });


        Button placeOrderButton = (Button) findViewById(R.id.placeOrder);
        placeOrderButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                PaymentOptions selectedPayment = (PaymentOptions) spinner.getSelectedView().getTag();

                placeOrder(selectedPayment);

            }
        });

        Button getEticket = (Button) findViewById(R.id.getEticket);
        getEticket.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getETicketMethod();


            }
        });


    }
    private void getETicketMethod()
    {
        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        CometbitesAPI cometbitesAPI1=Constants.getCometbitesAPI();
        Call<Ticket> call=cometbitesAPI1.getETicket(user.getUid());
        call.enqueue(new Callback<Ticket>() {
            @Override
            public void onResponse(Call<Ticket> call, Response<Ticket> response) {
                onGetETicketSuccess(response.body());
            }

            @Override
            public void onFailure(Call<Ticket> call, Throwable t) {
                onGetETicketFailure(t);
            }
        });

    }

    private void onGetETicketSuccess(Ticket ticket){
        Toast.makeText(getApplicationContext(), "Generating E-ticket...", Toast.LENGTH_SHORT).show();
        Intent i = new Intent(OrderConfirmationActivity.this, EticketActivity.class);
        i.putExtra("paid", false);
        i.putExtra("code",ticket.getCode());
        i.putExtra("waitTime",ticket.getWaitTime());
        startActivity(i);
        finish();
    }
    private void onGetETicketFailure(Throwable t){
        Toast.makeText(this, "Unable to generate ticket: "+t.toString(), Toast.LENGTH_SHORT).show();
    }


    private void placeOrder(PaymentOptions selectedPayment) {
        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        CometbitesAPI cometbitesAPI =Constants.getCometbitesAPI();
        Call<Ticket> call=cometbitesAPI.placeOrderWithCardDetails(user.getUid(),selectedPayment.getCardname(),selectedPayment.getCardno(),selectedPayment.getCvv(),selectedPayment.getExpdate());
        call.enqueue(new Callback<Ticket>() {
            @Override
            public void onResponse(Call<Ticket> call, Response<Ticket> response) {
                onPlaceOrderSuccess(response.body());
            }

            @Override
            public void onFailure(Call<Ticket> call, Throwable t) {
                onPlaceOrderFailure(t);

            }
        });

    }

    private void onPlaceOrderSuccess(Ticket ticket) {
        Toast.makeText(getApplicationContext(), "Order placed!!!", Toast.LENGTH_SHORT).show();
        Intent i = new Intent(OrderConfirmationActivity.this, EticketActivity.class);
        i.putExtra("paid", true);
        i.putExtra("code",ticket.getCode());
        i.putExtra("waitTime",ticket.getWaitTime());
        startActivity(i);
        finish();
    }

    private void onPlaceOrderFailure(Throwable t) {
        Toast.makeText(this, "Unable to place order: "+t.toString(), Toast.LENGTH_SHORT).show();
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
