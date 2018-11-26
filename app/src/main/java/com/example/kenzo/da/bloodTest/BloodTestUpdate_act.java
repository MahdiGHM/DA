package com.example.kenzo.da.bloodTest;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.NavUtils;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.Toast;

import com.example.kenzo.da.DatabaseHelper;
import com.example.kenzo.da.R;

public class BloodTestUpdate_act extends AppCompatActivity {
    EditText editText1;
    EditText editText2;
    DatabaseHelper myDb;
    int[] b;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        setContentView(R.layout.blood_test_update);
        myDb = new DatabaseHelper(this);
        editText1 = (EditText) findViewById(R.id.et_lab_name_u);
        editText2 = (EditText) findViewById(R.id.et_date_u);
        b = new int[29];
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
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_add_reminder, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        switch (item.getItemId()) {
            // Respond to a click on the "Save" menu option
            case R.id.save_reminder :
                asign();
                boolean isUpdate = myDb.updateBTData(b,Integer.parseInt(getIntent().getStringExtra("id")), editText1.getText().toString(), editText2.getText().toString());
                if (isUpdate)
                    Toast.makeText(BloodTestUpdate_act.this, "داده ها بروز رسانی شد", Toast.LENGTH_SHORT).show();
                else
                    Toast.makeText(BloodTestUpdate_act.this, "خطا در بروز رسانی داده ها", Toast.LENGTH_SHORT).show();
                finish();
                return true;
            case R.id.discard_reminder :
                final AlertDialog.Builder alertBox = new AlertDialog.Builder(this);
                alertBox.setTitle("حذف یک سطر!                       ");
                alertBox.setMessage("آیا از حذف این سطر اطمینان دارید؟!");
                alertBox.setIcon(R.drawable.warning);

                alertBox.setNegativeButton("خیر", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.cancel();
                    }
                });
                alertBox.setPositiveButton("بله",new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        myDb.deleteBTData(Integer.parseInt(getIntent().getStringExtra("id")));
                        finish();
                    }
                });
                alertBox.show();
                return true;
            // Respond to a click on the "Up" arrow button in the app bar
            case android.R.id.home:
                NavUtils.navigateUpFromSameTask(BloodTestUpdate_act.this);
                return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
