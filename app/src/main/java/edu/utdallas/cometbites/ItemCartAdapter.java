package edu.utdallas.cometbites;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.List;

import edu.utdallas.cometbites.models.CartItem;

/**
 * Created by salilkansal on 10/28/16.
 */

public class ItemCartAdapter extends BaseAdapter {
    private List<CartItem> itemsInCart;
    private Context mContext;

    public ItemCartAdapter(List<CartItem> itemsInCart, Context mContext) {
        this.itemsInCart = itemsInCart;
        this.mContext = mContext;
    }


    @Override
    public int getCount() {
        return itemsInCart.size();
    }

    @Override
    public Object getItem(int i) {
        return itemsInCart.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        View v = View.inflate(mContext, R.layout.cart_list_layout, null);

        TextView quantity = (TextView) v.findViewById(R.id.quantity_textview_cart);
        TextView itemName = (TextView) v.findViewById(R.id.item_name_cart_textview);
        TextView price = (TextView) v.findViewById(R.id.item_price_cart_textview);

        CartItem cartItem = itemsInCart.get(i);
        quantity.setText(cartItem.getQuantity());
        itemName.setText(cartItem.getItem_name());
        price.setText(cartItem.getPrice());

        return v;
    }
}
