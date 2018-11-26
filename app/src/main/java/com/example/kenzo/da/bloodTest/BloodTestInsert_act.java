package com.example.kenzo.da.bloodTest;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.kenzo.da.DatabaseHelper;
import com.example.kenzo.da.R;

public class BloodTestInsert_act extends AppCompatActivity {
    Button button;
    DatabaseHelper myDb;
    int[] b;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.blood_test_insert_act);
        button = (Button) findViewById(R.id.blood_test_button);
        myDb = new DatabaseHelper(this);
        b = new int[29];
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                asign();
                boolean isInserted = myDb.insertDataBtData(b,((EditText)(findViewById(R.id.et_lab_name))).getText().toString(),((EditText)(findViewById(R.id.et_date))).getText().toString());
                if(isInserted) {
                    Toast.makeText(BloodTestInsert_act.this, "Data Inserted", Toast.LENGTH_SHORT).show();
                    finish();
                }
                else
                    Toast.makeText(BloodTestInsert_act.this,"Data Not Inserted",Toast.LENGTH_SHORT).show();
            }
        });

    }
    public void asign(){
        try {
            b[0] = Integer.parseInt(((EditText) (findViewById(R.id.et_hba1c))).getText().toString());
            b[1] = Integer.parseInt(((EditText) (findViewById(R.id.et_wbc))).getText().toString());
            b[2] = Integer.parseInt(((EditText) (findViewById(R.id.et_rbc))).getText().toString());
            b[3] = Integer.parseInt(((EditText) (findViewById(R.id.et_hgb))).getText().toString());
            b[4] = Integer.parseInt(((EditText) (findViewById(R.id.et_hct))).getText().toString());
            b[5] = Integer.parseInt(((EditText) (findViewById(R.id.et_mcv))).getText().toString());
            b[6] = Integer.parseInt(((EditText) (findViewById(R.id.et_mch))).getText().toString());
            b[7] = Integer.parseInt(((EditText) (findViewById(R.id.et_mchc))).getText().toString());
            b[8] = Integer.parseInt(((EditText) (findViewById(R.id.et_platelets))).getText().toString());
            b[9] = Integer.parseInt(((EditText) (findViewById(R.id.et_fbs))).getText().toString());
            b[10] = Integer.parseInt(((EditText) (findViewById(R.id.et_urea))).getText().toString());
            b[11] = Integer.parseInt(((EditText) (findViewById(R.id.et_creatinine))).getText().toString());
            b[12] = Integer.parseInt(((EditText) (findViewById(R.id.et_uric_acid))).getText().toString());
            b[13] = Integer.parseInt(((EditText) (findViewById(R.id.et_total_cholesterol))).getText().toString());
            b[14] = Integer.parseInt(((EditText) (findViewById(R.id.et_triglycerides))).getText().toString());
            b[15] = Integer.parseInt(((EditText) (findViewById(R.id.et_hdl_cholesterol))).getText().toString());
            b[16] = Integer.parseInt(((EditText) (findViewById(R.id.et_ldl_cholesterol))).getText().toString());
            b[17] = Integer.parseInt(((EditText) (findViewById(R.id.et_sgot))).getText().toString());
            b[18] = Integer.parseInt(((EditText) (findViewById(R.id.et_sgpt))).getText().toString());
            b[19] = Integer.parseInt(((EditText) (findViewById(R.id.et_alkaline_phosphatase))).getText().toString());
            b[20] = Integer.parseInt(((EditText) (findViewById(R.id.et_ca))).getText().toString());
            b[21] = Integer.parseInt(((EditText) (findViewById(R.id.et_p))).getText().toString());
            b[22] = Integer.parseInt(((EditText) (findViewById(R.id.et_fe))).getText().toString());
            b[24] = Integer.parseInt(((EditText) (findViewById(R.id.et_vit_d))).getText().toString());
            b[25] = Integer.parseInt(((EditText) (findViewById(R.id.et_b12))).getText().toString());
            b[26] = Integer.parseInt(((EditText) (findViewById(R.id.et_t3))).getText().toString());
            b[27] = Integer.parseInt(((EditText) (findViewById(R.id.et_t4))).getText().toString());
            b[28] = Integer.parseInt(((EditText) (findViewById(R.id.et_tsh))).getText().toString());
        }catch (Exception E){}
    }
}
