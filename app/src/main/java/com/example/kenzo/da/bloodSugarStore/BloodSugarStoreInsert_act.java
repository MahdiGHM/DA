package com.example.kenzo.da.bloodSugarStore;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.NavUtils;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.text.SpannableStringBuilder;
import android.text.style.ForegroundColorSpan;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.example.kenzo.da.DatabaseHelper;
import com.example.kenzo.da.R;

public class BloodSugarStoreInsert_act extends AppCompatActivity {
    String time;
    DatabaseHelper myDb;
    Spinner spinner;
    EditText editText;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.bloodsugar_store_insert_act);
        getSupportActionBar().setDisplayOptions(ActionBar.DISPLAY_SHOW_CUSTOM);
        getSupportActionBar().setCustomView(R.layout.actionbar);
        ((TextView)findViewById(R.id.action_bar_title)).setText("دفترچه ثبت قند خون");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        spinner = (Spinner) findViewById(R.id.spinner_update);
        editText = (EditText) findViewById(R.id.editText_insert) ;
        ArrayAdapter adapter = ArrayAdapter.createFromResource(BloodSugarStoreInsert_act.this,R.array.bloodsugar_store_insert_array,android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);
        myDb = new DatabaseHelper(this);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_add_reminder, menu);
        return true;
    }

    @Override
    public boolean onPrepareOptionsMenu(Menu menu) {
        super.onPrepareOptionsMenu(menu);
        MenuItem menuItem = menu.findItem(R.id.discard_reminder);
        menuItem.setVisible(false);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        // User clicked on a menu option in the app bar overflow menu
        switch (item.getItemId()) {
            // Respond to a click on the "Save" menu option
            case R.id.save_reminder:

                if (editText.getText().toString().length() == 0){
                    String estring = "میزان قند خون نباید خالی باشد!";
                    ForegroundColorSpan fgcspan = new ForegroundColorSpan(getResources().getColor(R.color.white));
                    SpannableStringBuilder ssbuilder = new SpannableStringBuilder(estring);
                    ssbuilder.setSpan(fgcspan, 0, estring.length(), 0);
                    editText.requestFocus();
                    editText.setError(ssbuilder);
                } else {
                    boolean isInserted = myDb.insertDataBss(Integer.parseInt(((EditText)(findViewById(R.id.editText_insert))).getText().toString()),spinner.getSelectedItem().toString());
                    if(isInserted) {
                        Toast.makeText(BloodSugarStoreInsert_act.this, "Data Inserted", Toast.LENGTH_SHORT).show();
                        finish();
                    }
                    else
                        Toast.makeText(BloodSugarStoreInsert_act.this,"Data Not Inserted",Toast.LENGTH_SHORT).show();
                    finish();
                }
                return true;

            case android.R.id.home:
                NavUtils.navigateUpFromSameTask(BloodSugarStoreInsert_act.this);
                return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
