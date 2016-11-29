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

/**
 * Created by twinklesharma on 10/26/16.
 */

public class BrowseFoodJointsListAdapter extends BaseAdapter {
    Context mContext;
    List<FoodJoint> foodJointList;

    public BrowseFoodJointsListAdapter(Context mContext, List<FoodJoint> foodJointList) {
        this.mContext = mContext;
        this.foodJointList = foodJointList;
    }

    @Override
    public int getCount() {
        return foodJointList.size();
    }

    @Override
    public Object getItem(int i) {
        return foodJointList.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        View v = View.inflate(mContext, R.layout.joints_list_view, null);
        ImageView logo = (ImageView) v.findViewById(R.id.joint_image);
        TextView wait_time= (TextView) v.findViewById(R.id.wait_time);
        EditText fjid= (EditText) v.findViewById(R.id.fjid);


        FoodJoint foodJoint = foodJointList.get(i);
        fjid.setText(String.valueOf(foodJoint.getFjID()));
        wait_time.setText(foodJoint.getWaitTime()+" min");
        UrlImageViewHelper.setUrlDrawable(logo, foodJoint.getLogo());
        return v;
    }
}
