package edu.utdallas.cometbites;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.Set;

public class ItemDescriptionActivity extends AppCompatActivity {

    private static final String PREFS_NAME = "my_cart";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_item_description);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar_item_description);
        toolbar.setTitle("Item Description");
        setSupportActionBar(toolbar);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);


        ImageButton minusButton = (ImageButton) findViewById(R.id.minus_button);
        ImageButton plusButton = (ImageButton) findViewById(R.id.add_button);

        minusButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                TextView quantityTextView = (TextView) findViewById(R.id.quantityTextView);
                int quantity = Integer.parseInt(quantityTextView.getText().toString());
                if (quantity > 1) {
                    quantityTextView.setText(String.valueOf(--quantity));
                }
            }
        });


        plusButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                TextView quantityTextView = (TextView) findViewById(R.id.quantityTextView);
                int quantity = Integer.parseInt(quantityTextView.getText().toString());
                quantityTextView.setText(String.valueOf(++quantity));
            }
        });


        Button addToCartButton = (Button) findViewById(R.id.addToCartButton);
        addToCartButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                TextView quantityTextView = (TextView) findViewById(R.id.quantityTextView);
                String item_quantity = quantityTextView.getText().toString();
                TextView priceTextView = (TextView) findViewById(R.id.item_price);
                String item_price = priceTextView.getText().toString();
                TextView nameTextView = (TextView) findViewById(R.id.item_name1);
                String item_name = nameTextView.getText().toString();


                SharedPreferences myPrefs = getSharedPreferences(PREFS_NAME, 0);
                SharedPreferences.Editor editor = myPrefs.edit();
                Log.d("Item Description", "onClick: " + myPrefs.getAll().toString());


                if (myPrefs.contains(item_name)) {
                    //add quantity to the previous quantity stored in item_name
                    String itemInfo = myPrefs.getString(item_name, null);
                    int stored_quantity = Integer.parseInt(itemInfo.split(" ")[0]);

                    Log.d("Item Description", "onClick: qty" + stored_quantity);
                    int selected_new_quantity = Integer.parseInt(item_quantity);
                    selected_new_quantity += stored_quantity;
                    item_quantity = String.valueOf(selected_new_quantity);
                }


                String itemInfo = item_quantity + " " + item_price;


                editor.putString(item_name, itemInfo);
                editor.commit();
                Log.d("Item Description", "onClick: " + myPrefs.getAll().toString());
                Toast.makeText(ItemDescriptionActivity.this, item_name + " is successfully added to the cart", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(ItemDescriptionActivity.this, BrowseItemsActivity.class);
                startActivity(intent);


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
