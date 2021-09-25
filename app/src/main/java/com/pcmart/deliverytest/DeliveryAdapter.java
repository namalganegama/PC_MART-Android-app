package com.pcmart.deliverytest;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.List;

public class DeliveryAdapter extends ArrayAdapter<Delivery_Model> {

    private Context context;
    private int resource;
    List<Delivery_Model> deliveries;


    DeliveryAdapter(Context context, int resource, List<Delivery_Model> deliveries){
        super(context,resource,deliveries);
        this.context = context;
        this.resource = resource;
        this.deliveries = deliveries;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View row = inflater.inflate(resource,parent,false);

        TextView title = row.findViewById(R.id.heading);
        TextView description = row.findViewById(R.id.description);
        TextView t1 = (TextView) row.findViewById(R.id.total);

        //deliveries [obj1,obj2,obj3]
        Delivery_Model delivery = deliveries.get(position);
        title.setText(delivery.getName());
        description.setText(delivery.getAddress());

        t1.setText("Rs: "+""+delivery.getQuentity()*100);
        return row;

    }
}
