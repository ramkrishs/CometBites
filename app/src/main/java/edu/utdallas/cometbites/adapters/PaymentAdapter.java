package edu.utdallas.cometbites.adapters;


import android.content.Context;
import android.support.v4.content.ContextCompat;
import android.text.Layout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.List;

import edu.utdallas.cometbites.R;
import edu.utdallas.cometbites.model.PaymentOptions;

/**
 * Created by twinklesharma on 11/30/16.
 */

public class PaymentAdapter extends BaseAdapter {

    Context mContext;

    List<PaymentOptions> paymentOptionsList;
    LayoutInflater inflter;


    public PaymentAdapter(Context mContext, List<PaymentOptions> paymentOptionsList) {
        this.mContext = mContext;
        this.paymentOptionsList = paymentOptionsList;
        inflter = (LayoutInflater.from(mContext));
    }

    @Override
    public int getCount() {
        return paymentOptionsList.size();
    }

    @Override
    public Object getItem(int i) {
        return null;
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }



    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        view = inflter.inflate(android.R.layout.simple_spinner_item, null);
        TextView textView = (TextView) view.findViewById(android.R.id.text1);

        PaymentOptions paymentOptions = paymentOptionsList.get(i);

        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(paymentOptions.getCardname());

        stringBuilder.append(" ****");
        String cardNo = paymentOptions.getCardno();
        stringBuilder.append(cardNo.substring(cardNo.length()-4));
        textView.setText(stringBuilder.toString());
        textView.setTextColor(ContextCompat.getColor(mContext, R.color.textColor));
        view.setTag(paymentOptions);



        return view;
    }
}
