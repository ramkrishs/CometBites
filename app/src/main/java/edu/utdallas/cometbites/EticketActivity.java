package edu.utdallas.cometbites;

import android.app.Activity;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class EticketActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_eticket);

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
    }
}
