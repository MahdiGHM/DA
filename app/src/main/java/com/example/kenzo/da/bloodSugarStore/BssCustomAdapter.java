package com.example.kenzo.da.bloodSugarStore;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageButton;
import android.widget.TextView;

import com.example.kenzo.da.DatabaseHelper;
import com.example.kenzo.da.R;

import java.util.ArrayList;

public class BssCustomAdapter extends BaseAdapter {
    private DatabaseHelper myDb;
    private Intent intent;
    private Context context;
    ArrayList<String[]> data;
    private LayoutInflater layoutInflater;


    public BssCustomAdapter(Context context, ArrayList data) {
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
        public TextView bloodsugarStoreItemValue;
        public TextView bloodsugarStoreItemTime;
        public TextView bloodsugarStoreItemTimespan;
        public TextView bloodsugarStoreItemDate;
        public ImageButton imageButton1;
        public ImageButton imageButton2;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        View vi = convertView;
        viewHolder holder = new viewHolder();
        if(vi == null){
            vi = layoutInflater.inflate(R.layout.bloodsugar_store_item,null);
            holder.bloodsugarStoreItemValue = (TextView) vi.findViewById(R.id.bloodsugar_store_item_value);
            holder.bloodsugarStoreItemDate = (TextView) vi.findViewById(R.id.bloodsugar_store_item_date);
            holder.bloodsugarStoreItemTime = (TextView) vi.findViewById(R.id.bloodsugar_store_item_time);
            holder.bloodsugarStoreItemTimespan = (TextView) vi.findViewById(R.id.bloodsugar_store_item_timespan);
            holder.imageButton1 = (ImageButton) vi.findViewById(R.id.imageButton1);
            holder.imageButton2 = (ImageButton) vi.findViewById(R.id.imageButton2);
            vi.setTag(holder);
        }else
            holder = (viewHolder) vi.getTag();

        if (data.size() > 0){
            String[] tempData = data.get(position);
            holder.bloodsugarStoreItemValue.setText(tempData[0]);
            holder.bloodsugarStoreItemDate.setText(tempData[1]);
            holder.bloodsugarStoreItemTime.setText(tempData[2]);
            holder.bloodsugarStoreItemTimespan.setText(tempData[3]);
        }
        final AlertDialog.Builder alertBox = new AlertDialog.Builder(context);
        alertBox.setTitle("حذف یک سطر!                       ");
        alertBox.setMessage("آیا از حذف این سطر اطمینان دارید؟!");
        alertBox.setIcon(R.drawable.warning);

        holder.imageButton1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                alertBox.setNegativeButton("خیر", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.cancel();
                    }
                });
                alertBox.setPositiveButton("بله",new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        myDb.deleteBssData(Integer.parseInt(data.get(position)[4]));
                        data.remove(position);
                        notifyDataSetChanged();
                    }
                });
                alertBox.show();
            }
        });
        holder.imageButton2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                intent = new Intent(context,BloodSugarStoreUpdate_act.class);
                intent.putExtra("id",data.get(position)[4]);
                context.startActivity(intent);
            }
        });

        return vi;
    }
}
