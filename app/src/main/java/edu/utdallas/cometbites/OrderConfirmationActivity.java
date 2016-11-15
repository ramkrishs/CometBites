package edu.utdallas.cometbites;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.Toast;

public class OrderConfirmationActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order_confirmation);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar_order_payment_page);
        toolbar.setTitle("Enter Payment Details");
        setSupportActionBar(toolbar);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        Spinner spinner = (Spinner) findViewById(R.id.cards_spinner);
// Create an ArrayAdapter using the string array and a default spinner layout
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
                R.array.cards_spinner, android.R.layout.simple_spinner_item);
// Specify the layout to use when the list of choices appears
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
// Apply the adapter to the spinner
        spinner.setAdapter(adapter);


        Button placeOrder= (Button)findViewById(R.id.placeOrder);
        placeOrder.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Toast.makeText(getApplicationContext(),"Order placed!!!",Toast.LENGTH_SHORT).show();
                Intent i=new Intent(OrderConfirmationActivity.this, EticketActivity.class);
                i.putExtra("paid", true);
                startActivity(i);
                finish();
            }
        });

        Button getEticket= (Button)findViewById(R.id.getEticket);
        getEticket.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(getApplicationContext(),"Generating E-ticket...",Toast.LENGTH_SHORT).show();
                Intent i=new Intent(OrderConfirmationActivity.this, EticketActivity.class);
                i.putExtra("paid", false);
                startActivity(i);
                finish();
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
