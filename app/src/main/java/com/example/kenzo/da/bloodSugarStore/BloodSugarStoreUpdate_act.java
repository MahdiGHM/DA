package com.example.kenzo.da.bloodSugarStore;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.NavUtils;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.kenzo.da.DatabaseHelper;
import com.example.kenzo.da.R;
import com.example.kenzo.da.settings.BaseThemedActivity;
import com.example.kenzo.da.settings.ConfigTheme;

public class BloodSugarStoreUpdate_act extends BaseThemedActivity {
    EditText editText;
    Button button;
    Spinner spinner;
    DatabaseHelper myDb;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.bloodsugar_store_update_act);
        setTitle("دفترچه ثبت قند خون");
        ConfigTheme configTheme = new ConfigTheme(this);
        configTheme.configIt();
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        myDb = new DatabaseHelper(this);
        spinner = (Spinner) findViewById(R.id.spinner_update);
        editText = (EditText) findViewById(R.id.editText_update);
        SpinnerAdapter adapter = new SpinnerAdapter(this);
        spinner.setAdapter(adapter);
        editText.setText(getIntent().getStringExtra("value"));
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            spinner.setAutofillHints(getIntent().getStringExtra("time_span"));
        }
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
            case R.id.save_reminder:


                if (editText.getText().toString().length() == 0){
                    editText.setError("Reminder Title cannot be blank!");
                }

                else {
                    boolean isUpdate = myDb.updateBssData(Integer.parseInt(getIntent().getStringExtra("id")),Integer.parseInt(editText.getText().toString()),spinner.getSelectedItem().toString());
                    if(isUpdate) {
                        Toast.makeText(BloodSugarStoreUpdate_act.this, "Data Updated", Toast.LENGTH_SHORT).show();
                        finish();
                    }
                    else
                        Toast.makeText(BloodSugarStoreUpdate_act.this,"Data Not Updated",Toast.LENGTH_SHORT).show();
                    finish();
                }
                return true;
            case R.id.discard_reminder:

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
                                myDb.deleteBssData(Integer.parseInt(getIntent().getStringExtra("id")));
                                finish();
                            }
                        });
                        alertBox.show();
                return true;
            // Respond to a click on the "Up" arrow button in the app bar
            case android.R.id.home:
                NavUtils.navigateUpFromSameTask(BloodSugarStoreUpdate_act.this);
                return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
