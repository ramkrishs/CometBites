package edu.utdallas.cometbites;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
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


        List<Item> itemList = new LinkedList<>();

        itemList.add(new Item(R.drawable.sub1, "Chicken Sub", "$8.99"));
        itemList.add(new Item(R.drawable.sub2, "Bacon Sub", "$10.99"));

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
}
