package edu.utdallas.cometbites;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

import edu.utdallas.cometbites.models.FoodJoint;
import edu.utdallas.cometbites.models.Item;

/**
 * Created by twinklesharma on 10/26/16.
 */

public class BrowseItemsAdapter extends BaseAdapter {

    List<Item> itemList;
    Context mcontext;

    public BrowseItemsAdapter(List<Item> itemList, Context mcontext) {
        this.itemList = itemList;
        this.mcontext = mcontext;
    }

    @Override
    public int getCount() {
        return itemList.size();
    }

    @Override
    public Object getItem(int i) {
        return itemList.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        View v = View.inflate(mcontext, R.layout.browse_item_list, null);
        ImageView image = (ImageView) v.findViewById(R.id.itemImage);
        TextView name = (TextView) v.findViewById(R.id.itemName);
        TextView price = (TextView) v.findViewById(R.id.itemPrice);

        Item item = itemList.get(i);
        name.setText(item.getName());
        price.setText(item.getPrice());
        image.setImageResource(item.getImage());
        return v;
    }
}
