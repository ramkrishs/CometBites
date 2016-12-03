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
import edu.utdallas.cometbites.model.PaymentOptions;

/**
 * Created by twinklesharma on 12/3/16.
 */

public class PaymentMethodsAdapter extends BaseAdapter {
    Context mContext;
    List<PaymentOptions> paymentOptionsList;


    public PaymentMethodsAdapter(Context mContext, List<PaymentOptions> paymentOptionsList) {
        this.mContext = mContext;
        this.paymentOptionsList = paymentOptionsList;
    }


    @Override
    public int getCount() {
        return this.paymentOptionsList.size();
    }

    @Override
    public Object getItem(int i) {
        return this.paymentOptionsList.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        LayoutInflater inflater = (LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        TwoLineListItem twoLineListItem = (TwoLineListItem) inflater.inflate(android.R.layout.simple_list_item_2, null);

        TextView textView1 = twoLineListItem.getText1();
        TextView textView2 = twoLineListItem.getText2();

        PaymentOptions paymentOptions = paymentOptionsList.get(i);

        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("****");
        stringBuilder.append(paymentOptions.getCardno().substring(paymentOptions.getCardno().length()-4));
        textView1.setText(paymentOptions.getCardname());
        textView2.setText(stringBuilder.toString());
        textView1.setTextColor(ContextCompat.getColor(mContext, R.color.textColor));
        textView2.setTextColor(ContextCompat.getColor(mContext, R.color.textColor));

        return twoLineListItem;
    }
}
