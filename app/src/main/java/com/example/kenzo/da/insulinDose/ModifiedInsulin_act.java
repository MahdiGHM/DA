package com.example.kenzo.da.insulinDose;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.NavUtils;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.example.kenzo.da.R;

public class ModifiedInsulin_act extends AppCompatActivity {
    EditText editText1;
    EditText editText2;
    EditText editText3;
    TextView textView;
    Button button;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.modified_insulin_act);
        getSupportActionBar().setDisplayOptions(ActionBar.DISPLAY_SHOW_CUSTOM);
        getSupportActionBar().setCustomView(R.layout.actionbar);
        ((TextView)findViewById(R.id.action_bar_title)).setText("محاسبه دوز انسولین");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        textView = (TextView) findViewById(R.id.textView_modified_insulin);
        editText1 = (EditText) findViewById(R.id.editText1_modified_insulin);
        editText2 = (EditText) findViewById(R.id.editText2_modified_insulin);
        editText3 = (EditText) findViewById(R.id.editText3_modified_insulin);
        button = (Button) findViewById(R.id.button_modified_insulin);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                calculate();
            }
        });
    }
    public void calculate(){
        float f= (float)(((Integer.parseInt(editText2.getText().toString()))-(Integer.parseInt(editText3.getText().toString())))/(1800/(Integer.parseInt(editText1.getText().toString()))));
        textView.setText(String.format("%.1f",f)+" واحد انسولین (نوو رپید) برای اصلاح قند خون شما نیاز است.");
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        NavUtils.navigateUpFromSameTask(ModifiedInsulin_act.this);
        return true;
    }
}
