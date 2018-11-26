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

public class BloodTestUpdate_act extends AppCompatActivity {
    EditText editText1;
    EditText editText2;
    Button button;
    DatabaseHelper myDb;
    int[] b;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.blood_test_update);
        myDb = new DatabaseHelper(this);
        editText1 = (EditText) findViewById(R.id.et_lab_name_u);
        editText2 = (EditText) findViewById(R.id.et_date_u);
        button = (Button) findViewById(R.id.blood_test_button_u);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                asign();
                boolean isUpdate = myDb.updateBTData(b,Integer.parseInt(getIntent().getStringExtra("id")), editText1.getText().toString(), editText2.getText().toString());
                if (isUpdate) {
                    Toast.makeText(BloodTestUpdate_act.this, "Data Updated", Toast.LENGTH_SHORT).show();
                    finish();
                } else
                    Toast.makeText(BloodTestUpdate_act.this, "Data Not Updated", Toast.LENGTH_SHORT).show();
            }
        });
    }
    public void asign(){
        try {
            b[0] = Integer.parseInt(((EditText) (findViewById(R.id.et_hba1c_u))).getText().toString());
            b[1] = Integer.parseInt(((EditText) (findViewById(R.id.et_wbc_u))).getText().toString());
            b[2] = Integer.parseInt(((EditText) (findViewById(R.id.et_rbc_u))).getText().toString());
            b[3] = Integer.parseInt(((EditText) (findViewById(R.id.et_hgb_u))).getText().toString());
            b[4] = Integer.parseInt(((EditText) (findViewById(R.id.et_hct_u))).getText().toString());
            b[5] = Integer.parseInt(((EditText) (findViewById(R.id.et_mcv_u))).getText().toString());
            b[6] = Integer.parseInt(((EditText) (findViewById(R.id.et_mch_u))).getText().toString());
            b[7] = Integer.parseInt(((EditText) (findViewById(R.id.et_mchc_u))).getText().toString());
            b[8] = Integer.parseInt(((EditText) (findViewById(R.id.et_platelets_u))).getText().toString());
            b[9] = Integer.parseInt(((EditText) (findViewById(R.id.et_fbs_u))).getText().toString());
            b[10] = Integer.parseInt(((EditText) (findViewById(R.id.et_urea_u))).getText().toString());
            b[11] = Integer.parseInt(((EditText) (findViewById(R.id.et_creatinine_u))).getText().toString());
            b[12] = Integer.parseInt(((EditText) (findViewById(R.id.et_uric_acid_u))).getText().toString());
            b[13] = Integer.parseInt(((EditText) (findViewById(R.id.et_total_cholesterol_u))).getText().toString());
            b[14] = Integer.parseInt(((EditText) (findViewById(R.id.et_triglycerides_u))).getText().toString());
            b[15] = Integer.parseInt(((EditText) (findViewById(R.id.et_hdl_cholesterol_u))).getText().toString());
            b[16] = Integer.parseInt(((EditText) (findViewById(R.id.et_ldl_cholesterol_u))).getText().toString());
            b[17] = Integer.parseInt(((EditText) (findViewById(R.id.et_sgot_u))).getText().toString());
            b[18] = Integer.parseInt(((EditText) (findViewById(R.id.et_sgpt_u))).getText().toString());
            b[19] = Integer.parseInt(((EditText) (findViewById(R.id.et_alkaline_phosphatase_u))).getText().toString());
            b[20] = Integer.parseInt(((EditText) (findViewById(R.id.et_ca_u))).getText().toString());
            b[21] = Integer.parseInt(((EditText) (findViewById(R.id.et_p_u))).getText().toString());
            b[22] = Integer.parseInt(((EditText) (findViewById(R.id.et_fe_u))).getText().toString());
            b[24] = Integer.parseInt(((EditText) (findViewById(R.id.et_vit_d_u))).getText().toString());
            b[25] = Integer.parseInt(((EditText) (findViewById(R.id.et_b12_u))).getText().toString());
            b[26] = Integer.parseInt(((EditText) (findViewById(R.id.et_t3_u))).getText().toString());
            b[27] = Integer.parseInt(((EditText) (findViewById(R.id.et_t4_u))).getText().toString());
            b[28] = Integer.parseInt(((EditText) (findViewById(R.id.et_tsh_u))).getText().toString());
        }catch (Exception e){}
    }
}
