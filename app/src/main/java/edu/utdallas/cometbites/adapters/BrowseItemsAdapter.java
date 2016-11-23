package edu.utdallas.cometbites.adapters;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.koushikdutta.urlimageviewhelper.UrlImageViewHelper;

import java.util.List;

import edu.utdallas.cometbites.R;
import edu.utdallas.cometbites.model.FoodJoint;
import edu.utdallas.cometbites.model.Item;
import edu.utdallas.cometbites.util.Constants;

/**
 * Created by twinklesharma on 10/26/16.
 */

public class BrowseItemsAdapter extends BaseAdapter {

    FoodJoint foodJoint;
    Context mcontext;

    public BrowseItemsAdapter(FoodJoint foodJoint, Context mcontext) {
        this.foodJoint = foodJoint;
        this.mcontext = mcontext;
    }

    @Override
    public int getCount() {
        return foodJoint.getMenu().size();
    }

    @Override
    public Object getItem(int i) {
        return foodJoint.getMenu().get(i);
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
        EditText itemID = (EditText) v.findViewById(R.id.item_list_view_id);

        Item item = foodJoint.getMenu().get(i);
        itemID.setText(String.valueOf(item.getId()));
        name.setText(item.getName());
        price.setText(Constants.UNIT +  String.valueOf(item.getPrice()));
        UrlImageViewHelper.setUrlDrawable(image, item.getImage());
        return v;
    }
}
