package com.example.kenzo.da.bloodTest;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.NavUtils;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;

import com.example.kenzo.da.DatabaseHelper;
import com.example.kenzo.da.R;

import java.util.ArrayList;

public class BloodTestMain_act extends AppCompatActivity {
    ListView listView;
    BtCustomAdapter adapter;
    Intent intent;
    DatabaseHelper myDb;
    ArrayList<String[]> data = new ArrayList<String[]>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.blood_test_act);
        getSupportActionBar().setDisplayOptions(ActionBar.DISPLAY_SHOW_CUSTOM);
        getSupportActionBar().setCustomView(R.layout.actionbar);
        ((TextView)findViewById(R.id.action_bar_title)).setText("آزمایش ها");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        myDb = new DatabaseHelper(this);
        listView = (ListView) findViewById(R.id.blood_test_list);
        viewAll();
        adapter = new BtCustomAdapter(BloodTestMain_act.this, data);
        listView.setAdapter(adapter);
        FloatingActionButton fab = findViewById(R.id.blood_test_floating_actionButton);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                intent = new Intent(BloodTestMain_act.this,BloodTestInsert_act.class);
                startActivity(intent);
            }
        });
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> a, View v, int position,long id) {
                Cursor res = myDb.getBTData(Integer.parseInt(((TextView)(v.findViewById(R.id.blood_test))).getText().toString()));
                if(res.getCount()== 0){
                    showMessage("Error","Nothing found");
                    return;
                }
                StringBuffer buffer = new StringBuffer();
                while (res.moveToNext()){
                    buffer.append("Lab name : "+res.getString(1)+"\n");
                    buffer.append("Date : "+res.getString(2)+"\n");
                    buffer.append("HbA1C : "+res.getString(3)+"\n");
                    buffer.append("W.B.C. : "+res.getString(4)+"\n");
                    buffer.append("R.B.C. : "+res.getString(5)+"\n");
                    buffer.append("HGB : "+res.getString(6)+"\n");
                    buffer.append("HCT : "+res.getString(7)+"\n");
                    buffer.append("M.C.V. : "+res.getString(8)+"\n");
                    buffer.append("M.C.H. : "+res.getString(9)+"\n");
                    buffer.append("M.C.H.C. : "+res.getString(10)+"\n");
                    buffer.append("Platelets : "+res.getString(11)+"\n");
                    buffer.append("FBS : "+res.getString(12)+"\n");
                    buffer.append("Urea : "+res.getString(13)+"\n");
                    buffer.append("Creatinine : "+res.getString(14)+"\n");
                    buffer.append("Uric acid : "+res.getString(15)+"\n");
                    buffer.append("Total cholesterol : "+res.getString(16)+"\n");
                    buffer.append("Triglycerides : "+res.getString(17)+"\n");
                    buffer.append("HDL cholesterol : "+res.getString(18)+"\n");
                    buffer.append("LDL cholesterol : "+res.getString(19)+"\n");
                    buffer.append("S.G.O.T. : "+res.getString(20)+"\n");
                    buffer.append("S.G.P.T. : "+res.getString(21)+"\n");
                    buffer.append("Alkaline phosphatase : "+res.getString(22)+"\n");
                    buffer.append("Ca : "+res.getString(23)+"\n");
                    buffer.append("P : "+res.getString(24)+"\n");
                    buffer.append("Fe : "+res.getString(25)+"\n");
                    buffer.append("Vit D : "+res.getString(26)+"\n");
                    buffer.append("B12 : "+res.getString(27)+"\n");
                    buffer.append("T3 : "+res.getString(28)+"\n");
                    buffer.append("T4 : "+res.getString(29)+"\n");
                    buffer.append("TSH : "+res.getString(30)+"\n");
                    showMessage("Data",buffer.toString());
                }

            }
        });
        listView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                Cursor res = myDb.getBTData(Integer.parseInt(((TextView)(view.findViewById(R.id.blood_test))).getText().toString()));
                int temp[] = new int[30];
                String s = "";
                if(res.getCount()== 0){
                    showMessage("Error","Nothing found");
                    return false;
                }
                while (res.moveToNext()){
                    s=res.getString(1);
                    temp[1]=res.getInt(2);
                    temp[2]=res.getInt(3);
                    temp[3]=res.getInt(4);
                    temp[4]=res.getInt(5);
                    temp[5]=res.getInt(6);
                    temp[6]=res.getInt(7);
                    temp[7]=res.getInt(8);
                    temp[8]=res.getInt(9);
                    temp[9]=res.getInt(10);
                    temp[10]=res.getInt(11);
                    temp[11]=res.getInt(12);
                    temp[12]=res.getInt(13);
                    temp[13]=res.getInt(14);
                    temp[14]=res.getInt(15);
                    temp[15]=res.getInt(16);
                    temp[16]=res.getInt(17);
                    temp[17]=res.getInt(18);
                    temp[18]=res.getInt(19);
                    temp[19]=res.getInt(20);
                    temp[20]=res.getInt(21);
                    temp[21]=res.getInt(22);
                    temp[22]=res.getInt(23);
                    temp[23]=res.getInt(24);
                    temp[24]=res.getInt(25);
                    temp[25]=res.getInt(26);
                    temp[26]=res.getInt(27);
                    temp[27]=res.getInt(28);
                    temp[28]=res.getInt(29);
                    temp[29]=res.getInt(30);
                }
                Intent intent = new Intent(BloodTestMain_act.this, BloodTestUpdate_act.class);
                intent.putExtra("id",((TextView)(view.findViewById(R.id.blood_test))).getText().toString());
                intent.putExtra("ln",s);
                intent.putExtra("value",temp);
                startActivity(intent);
                return true;
            }
        });
    }
    public void showMessage(String title,String message){
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setCancelable(true);
        builder.setTitle(title);
        builder.setMessage(message);
        builder.show();
    }
    @Override
    public void onResume(){
        super.onResume();
        data.clear();
        viewAll();
        adapter.data=data;
        adapter.notifyDataSetChanged();
    }
    public void viewAll(){
        Cursor res = myDb.getAllBTData();
        if(res.getCount() == 0){
            return;
        }
        while(res.moveToNext()){
            String [] tempData = new String[4];
            tempData[0] = ("آزمایشگاه : "+res.getString(1));
            tempData[1] = ("Hba1C : "+res.getString(3));
            tempData[2] = ("تاریخ : "+res.getString(2));
            tempData[3] = res.getString(0);
            data.add(tempData);
        }
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        NavUtils.navigateUpFromSameTask(BloodTestMain_act.this);
        return true;
    }
}
