package edu.utdallas.cometbites.adapters;

import android.content.Context;
import android.support.v4.content.ContextCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;
import android.widget.TwoLineListItem;

import java.util.List;

import edu.utdallas.cometbites.R;
import edu.utdallas.cometbites.model.Order;

/**
 * Created by twinklesharma on 12/2/16.
 */

public class OrderAdapter extends BaseAdapter {

    List<Order> orderList;
    Context mcontext;

    public OrderAdapter(List<Order> orderList, Context mcontext) {
        this.orderList = orderList;
        this.mcontext = mcontext;
    }

    @Override
    public int getCount() {
        return orderList.size();
    }

    @Override
    public Object getItem(int i) {
        return orderList.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        LayoutInflater inflater = (LayoutInflater) mcontext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        TwoLineListItem twoLineListItem = (TwoLineListItem) inflater.inflate(android.R.layout.simple_list_item_2, null);

        TextView textView1 = twoLineListItem.getText1();
        TextView textView2 = twoLineListItem.getText2();

        Order order = orderList.get(i);
        textView1.setText(order.getInvoice());
        textView2.setText(order.getDate());
        textView1.setTextColor(ContextCompat.getColor(mcontext, R.color.textColor));
        textView2.setTextColor(ContextCompat.getColor(mcontext, R.color.textColor));

        twoLineListItem.setTag(order);

        return twoLineListItem;
    }
}
