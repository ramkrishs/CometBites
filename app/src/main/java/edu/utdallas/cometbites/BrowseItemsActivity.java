package edu.utdallas.cometbites;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import java.util.LinkedList;
import java.util.List;

import edu.utdallas.cometbites.models.Item;

public class BrowseItemsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_browse_items);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar_items);
        toolbar.setTitle("Select Item");
        setSupportActionBar(toolbar);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);


        List<Item> itemList = new LinkedList<>();

        itemList.add(new Item(R.drawable.sub1, "Rotisserie-Style Chicken ", "$7.95"));
        itemList.add(new Item(R.drawable.sub2, "Subway Club", "$7.95"));

        ListView listView = (ListView) findViewById(R.id.browseItemListView);
        BrowseItemsAdapter adapter = new BrowseItemsAdapter(itemList, getApplicationContext());

        listView.setAdapter(adapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                startActivity(new Intent(BrowseItemsActivity.this, ItemDescriptionActivity.class));
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
