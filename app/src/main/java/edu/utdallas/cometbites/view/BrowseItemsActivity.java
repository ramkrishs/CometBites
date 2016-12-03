package edu.utdallas.cometbites.view;

import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import com.google.android.gms.appindexing.Action;
import com.google.android.gms.appindexing.AppIndex;
import com.google.android.gms.appindexing.Thing;
import com.google.android.gms.common.api.GoogleApiClient;

import java.util.List;

import edu.utdallas.cometbites.R;
import edu.utdallas.cometbites.adapters.BrowseItemsAdapter;
import edu.utdallas.cometbites.model.FoodJoint;
import edu.utdallas.cometbites.model.Item;
import edu.utdallas.cometbites.util.CometbitesAPI;
import edu.utdallas.cometbites.util.Constants;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static edu.utdallas.cometbites.R.id.fjid;

public class BrowseItemsActivity extends AppCompatActivity {

    /**
     * ATTENTION: This was auto-generated to implement the App Indexing API.
     * See https://g.co/AppIndexing/AndroidStudio for more information.
     */
    private GoogleApiClient client;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_browse_items);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar_items);
        toolbar.setTitle("Select Item");
        setSupportActionBar(toolbar);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        final Intent intent = getIntent();
        Bundle bundle = intent.getExtras();
        final String fjid = bundle.getString("fjid");

        CometbitesAPI cometbitesAPI = Constants.getCometbitesAPI();
        Call<List<Item>> call = cometbitesAPI.getFoodJoint(fjid);

        call.enqueue(new Callback<List<Item>>() {
            @Override
            public void onResponse(Call<List<Item>> call, Response<List<Item>> response) {
                FoodJoint foodJoint=new FoodJoint();
                foodJoint.setMenu(response.body());
                ListView listView = (ListView) findViewById(R.id.browseItemListView);
                BrowseItemsAdapter adapter = new BrowseItemsAdapter(foodJoint, getApplicationContext());
                listView.setAdapter(adapter);
            }

            @Override
            public void onFailure(Call<List<Item>> call, Throwable t) {
                Toast.makeText(BrowseItemsActivity.this, "t" + t.toString(), Toast.LENGTH_SHORT).show();

            }
        });

        ListView listView = (ListView) findViewById(R.id.browseItemListView);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Intent intent1 = new Intent(BrowseItemsActivity.this, ItemDescriptionActivity.class);
                EditText itemIDEditText = (EditText) view.findViewById(R.id.item_list_view_id);

               String itemid = String.valueOf(itemIDEditText.getText());
               intent1.putExtra("fjid", fjid);
                intent1.putExtra("itemId", itemid);

               startActivity(intent1);
            }
        });


    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.action_bar, menu);

        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // handle arrow click here
        final Intent intent = getIntent();
        Bundle bundle = intent.getExtras();
        final String fjid = bundle.getString("fjid");
        if (item.getItemId() == android.R.id.home) {
            finish(); // close this activity and return to preview activity (if there is any)
        } else if (item.getItemId() == R.id.action_bar_cart) {

            Intent i = new Intent(BrowseItemsActivity.this, YourCartActivity.class);
            i.putExtra("fjid", fjid);
            startActivity(i);
        }

        return super.onOptionsItemSelected(item);
    }



    @Override
    public void onStart() {
        super.onStart();


    }

    @Override
    public void onStop() {
        super.onStop();


    }
}
