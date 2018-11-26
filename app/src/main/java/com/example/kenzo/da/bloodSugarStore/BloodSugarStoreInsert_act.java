package com.example.kenzo.da.bloodSugarStore;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.kenzo.da.DatabaseHelper;
import com.example.kenzo.da.R;

public class BloodSugarStoreInsert_act extends AppCompatActivity {
    Button button;
    String time;
    DatabaseHelper myDb;
    Spinner spinner;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.bloodsugar_store_insert_act);
        spinner = (Spinner) findViewById(R.id.spinner_update);
        button = (Button) findViewById(R.id.button13);
        ArrayAdapter adapter = ArrayAdapter.createFromResource(BloodSugarStoreInsert_act.this,R.array.bloodsugar_store_insert_array,android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);
        myDb = new DatabaseHelper(this);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                boolean isInserted = myDb.insertDataBss(Integer.parseInt(((EditText)(findViewById(R.id.editText_insert))).getText().toString()),spinner.getSelectedItem().toString());
                if(isInserted) {
                    Toast.makeText(BloodSugarStoreInsert_act.this, "Data Inserted", Toast.LENGTH_SHORT).show();
                    finish();
                }
                else
                    Toast.makeText(BloodSugarStoreInsert_act.this,"Data Not Inserted",Toast.LENGTH_SHORT).show();
            }
        });
    }
}
