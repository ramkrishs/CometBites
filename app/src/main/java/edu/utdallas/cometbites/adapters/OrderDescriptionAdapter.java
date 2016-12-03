package edu.utdallas.cometbites.adapters;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import edu.utdallas.cometbites.R;
import edu.utdallas.cometbites.model.Order;
import edu.utdallas.cometbites.model.OrderItem;

/**
 * Created by twinklesharma on 12/2/16.
 */

public class OrderDescriptionAdapter extends BaseAdapter {
    Order order;
    Context mcontext;

    public OrderDescriptionAdapter(Order order, Context mcontext) {
        this.order = order;
        this.mcontext = mcontext;
    }

    @Override
    public int getCount() {
        return this.order.getItems().size();
    }

    @Override
    public Object getItem(int i) {
        return this.order.getItems().get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        View v = View.inflate(mcontext, R.layout.cart_list_layout, null);

        TextView quantity = (TextView) v.findViewById(R.id.quantity_textview_cart);
        TextView itemName = (TextView) v.findViewById(R.id.item_name_cart_textview);
        TextView price = (TextView) v.findViewById(R.id.item_price_cart_textview);

        OrderItem item = this.order.getItems().get(i);

        itemName.setText(item.getName());
        price.setText(item.getPrice());
        quantity.setText("X " + item.getQuantity());
        return v;
    }
}
