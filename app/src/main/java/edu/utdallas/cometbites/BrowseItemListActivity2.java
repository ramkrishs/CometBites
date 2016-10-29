package edu.utdallas.cometbites;

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
import android.widget.ListView;

import com.google.android.gms.appindexing.Action;
import com.google.android.gms.appindexing.AppIndex;
import com.google.android.gms.appindexing.Thing;
import com.google.android.gms.common.api.GoogleApiClient;

import java.util.LinkedList;
import java.util.List;

import edu.utdallas.cometbites.models.Item;

public class BrowseItemListActivity2 extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.browse_item_activity2);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar_items2);
        toolbar.setTitle("Select Item");
        setSupportActionBar(toolbar);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);


        List<Item> itemList = new LinkedList<>();

        itemList.add(new Item(R.drawable.sub1, "Rotisserie-Style Chicken ", "$7.95"));
        itemList.add(new Item(R.drawable.sub2, "Subway Club", "$7.95"));

        ListView listView = (ListView) findViewById(R.id.browseItemListView2);
        BrowseItemsAdapter adapter = new BrowseItemsAdapter(itemList, getApplicationContext());

        listView.setAdapter(adapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                startActivity(new Intent(BrowseItemListActivity2.this, ItemDescriptionActivity.class));
            }
        });


    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.action_bar2, menu);

        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // handle arrow click here
        if (item.getItemId() == android.R.id.home) {
            finish(); // close this activity and return to preview activity (if there is any)
        } else if (item.getItemId() == R.id.action_bar_cart2) {
            Intent intent = new Intent(BrowseItemListActivity2.this, YourCartActivity.class);
            startActivity(intent);
        }

        return super.onOptionsItemSelected(item);
    }
}
