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

public class BloodSugarStoreUpdate_act extends AppCompatActivity {
    EditText editText;
    Button button;
    Spinner spinner;
    DatabaseHelper myDb;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.bloodsugar_store_update_act);
        myDb = new DatabaseHelper(this);
        button = (Button) findViewById(R.id.button14);
        spinner = (Spinner) findViewById(R.id.spinner_update);
        editText = (EditText) findViewById(R.id.editText_update);
        ArrayAdapter adapter = ArrayAdapter.createFromResource(BloodSugarStoreUpdate_act.this,R.array.bloodsugar_store_insert_array,android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                boolean isUpdate = myDb.updateBssData(Integer.parseInt(getIntent().getStringExtra("id")),Integer.parseInt(editText.getText().toString()),spinner.getSelectedItem().toString());
                if(isUpdate) {
                    Toast.makeText(BloodSugarStoreUpdate_act.this, "Data Updated", Toast.LENGTH_SHORT).show();
                    finish();
                }
                else
                    Toast.makeText(BloodSugarStoreUpdate_act.this,"Data Not Updated",Toast.LENGTH_SHORT).show();
            }
        });
    }
}
