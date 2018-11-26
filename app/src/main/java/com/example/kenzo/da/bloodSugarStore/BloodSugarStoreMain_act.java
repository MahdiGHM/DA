package com.example.kenzo.da.bloodSugarStore;

import android.content.Intent;
import android.database.Cursor;
import android.graphics.Color;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;

import com.example.kenzo.da.DatabaseHelper;
import com.example.kenzo.da.R;
import com.example.kenzo.da.Roozh;

import java.util.ArrayList;
import java.util.List;

import lecho.lib.hellocharts.model.Axis;
import lecho.lib.hellocharts.model.AxisValue;
import lecho.lib.hellocharts.model.Line;
import lecho.lib.hellocharts.model.LineChartData;
import lecho.lib.hellocharts.model.PointValue;
import lecho.lib.hellocharts.view.LineChartView;

public class BloodSugarStoreMain_act extends AppCompatActivity {
    ListView listView;
    BssCustomAdapter adapter;
    Intent intent;
    TextView textView1;
    TextView textView2;
    DatabaseHelper myDb;
    LineChartView lineChartView;
    ArrayList<String[]> data = new ArrayList<String[]>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.bloodsugar_store_act);
        myDb = new DatabaseHelper(this);
        textView1 = (TextView) findViewById(R.id.bloodsugar_store_30avg);
        textView2 = (TextView) findViewById(R.id.bloodsugar_store_14avg);
        listView = (ListView) findViewById(R.id.bloodsugar_store_list);
        viewAll();
        adapter = new BssCustomAdapter(BloodSugarStoreMain_act.this, data);
        listView.setAdapter(adapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(BloodSugarStoreMain_act.this, BloodSugarStoreUpdate_act.class);
                intent.putExtra("id",data.get(position)[4]);
                intent.putExtra("value",data.get(position)[0].replaceAll("میزان قند خون : ",""));
                intent.putExtra("time_span",data.get(position)[3]);
                startActivity(intent);
            }
        });
        FloatingActionButton fab = findViewById(R.id.floatingActionButton);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                intent = new Intent(BloodSugarStoreMain_act.this,BloodSugarStoreInsert_act.class);
                startActivity(intent);
            }
        });
        average();
        lineChartView = findViewById(R.id.chart);
        drawGraph();
    }
    @Override
    public void onResume(){
        super.onResume();
        average();
        data.clear();
        viewAll();
        adapter.data=data;
        adapter.notifyDataSetChanged();
        drawGraph();
    }
    public void average(){
        Cursor res = myDb.get30AvgBssData();
        Cursor res2 = myDb.get14AvgBssData();
        while (res.moveToNext() && res2.moveToNext()) {
            textView1.setText(String.format("میانگین قند 30 روزه : %.1f",res.getFloat(0)));
            textView2.setText(String.format("میانگین قند 14 روزه : %.1f",res2.getFloat(0)));
        }
    }
    public void viewAll(){
        Cursor res = myDb.getAllBssData();
        Roozh jCal = new Roozh();
        if(res.getCount() == 0){
            return;
        }
        while(res.moveToNext()){
            String [] tempData = new String[5];
            tempData[0] = ("میزان قند خون : "+res.getString(1));
            String array[] = res.getString(2).split("-");
            jCal.GregorianToPersian(Integer.valueOf(array[0]),Integer.valueOf(array[1]), Integer.valueOf(array[2]));
            String date = String.valueOf(jCal.getYear());
            date += "/"+String.valueOf(jCal.getMonth());
            date += "/"+String.valueOf(jCal.getDay());
            tempData[1] = ("تاریخ : "+date);
            tempData[2] = ("زمان : "+res.getString(4));
            tempData[3] = ("محدوده زمانی : "+"\n"+res.getString(3));
            tempData[4] = res.getString(0);
            data.add(tempData);
        }
    }
    public void drawGraph(){
        String[] axisData = new String[data.size()];
        int[] yAxisData = new int[data.size()];
        for(int i=0; i < data.size(); i++){
            axisData[i] = data.get(i)[1].replaceAll("تاریخ : ","");
            yAxisData[i] = Integer.valueOf(data.get(i)[0].replaceAll("میزان قند خون : ",""));
        }
        List yAxisValues = new ArrayList();
        List axisValues = new ArrayList();
        Line line = new Line(yAxisValues);
        line.setColor(Color.parseColor("#9C27B0"));
        for(int i = 0; i < axisData.length; i++){
            axisValues.add(i, new AxisValue(i).setLabel(axisData[i]));
        }

        for (int i = 0; i < yAxisData.length; i++){
            yAxisValues.add(new PointValue(i, yAxisData[i]));
        }
        List lines = new ArrayList();
        lines.add(line);
        LineChartData data = new LineChartData();
        data.setLines(lines);
        lineChartView.setLineChartData(data);
        Axis axis = new Axis();
        axis.setValues(axisValues);
        data.setAxisXBottom(axis);
        Axis yAxis = new Axis();
        data.setAxisYLeft(yAxis);
    }
}
