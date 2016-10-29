package edu.utdallas.cometbites;

import android.app.Activity;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;

public class EticketActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_eticket);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar_eticket_page);
        toolbar.setTitle("E-ticket Generated");
        setSupportActionBar(toolbar);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);


        Intent intent = getIntent();
        Bundle bundle = intent.getExtras();
        boolean paid = bundle.getBoolean("paid");

        if (!paid) {
            AlertDialog.Builder builder = new AlertDialog.Builder(this);
            builder.setTitle("Thank You");
            builder.setMessage("Kindly, Show this e-ticket to the restaurant counter to pay for your order.");
            builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialogInterface, int i) {
                    dialogInterface.dismiss();
                }
            });

            AlertDialog dialog = builder.create();
            dialog.show();
        }


        Button eatMoreButton = (Button) findViewById(R.id.eatMoreButton);
        eatMoreButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(EticketActivity.this, BrowseFoodJointsActivity.class);
                startActivity(intent);
                finish();
            }
        });

    }

    @Override
    public void onBackPressed() {
        Intent intent = new Intent(EticketActivity.this, BrowseFoodJointsActivity.class);
        startActivity(intent);
        finish();
    }


    @Override
    public boolean onOptionsItemSelected(android.view.MenuItem item) {
        Intent intent = new Intent(EticketActivity.this, BrowseFoodJointsActivity.class);
        startActivity(intent);
        finish();
        return true;
    }
}
