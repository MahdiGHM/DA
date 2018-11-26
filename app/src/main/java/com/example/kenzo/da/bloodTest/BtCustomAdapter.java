package com.example.kenzo.da.bloodTest;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.kenzo.da.DatabaseHelper;
import com.example.kenzo.da.R;

import java.util.ArrayList;

public class BtCustomAdapter extends BaseAdapter {
    private DatabaseHelper myDb;
    private Intent intent;
    private Context context;
    ArrayList<String[]> data;
    private LayoutInflater layoutInflater;


    public BtCustomAdapter(Context context, ArrayList data) {
        this.context = context;
        this.data = data;
        myDb = new DatabaseHelper(context);
        layoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public int getCount() {
        return data.size();
    }

    @Override
    public Object getItem(int position) {
        return position;
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    public static class viewHolder{
        public TextView hba1c;
        public TextView labName;
        public TextView date;
        public TextView id;
}

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        View vi = convertView;
        BtCustomAdapter.viewHolder holder = new BtCustomAdapter.viewHolder();
        if(vi == null){
            vi = layoutInflater.inflate(R.layout.blood_test_item,null);
            holder.labName = (TextView) vi.findViewById(R.id.blood_test_lab_name);
            holder.hba1c = (TextView) vi.findViewById(R.id.blood_test_hba1c);
            holder.date = (TextView) vi.findViewById(R.id.blood_test_date);
            holder.id = (TextView) vi.findViewById(R.id.blood_test);
            vi.setTag(holder);
        }else
            holder = (BtCustomAdapter.viewHolder) vi.getTag();

        if (data.size() > 0){
            String[] tempData = data.get(position);
            holder.labName.setText(tempData[0]);
            holder.hba1c.setText(tempData[1]);
            holder.date.setText(tempData[2]);
            holder.id.setText(tempData[3]);
        }
        return vi;
    }
}
