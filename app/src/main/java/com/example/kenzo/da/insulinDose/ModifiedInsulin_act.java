package com.example.kenzo.da.insulinDose;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.NavUtils;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.kenzo.da.R;
import com.example.kenzo.da.settings.BaseThemedActivity;
import com.example.kenzo.da.settings.ConfigTheme;

public class ModifiedInsulin_act extends BaseThemedActivity {
    EditText editText1;
    EditText editText2;
    EditText editText3;
    TextView textView;
    Button button;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.modified_insulin_act);
        setTitle("محاسبه دوز انسولین");
        ConfigTheme configTheme = new ConfigTheme(this);
        configTheme.configIt();
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        textView = (TextView) findViewById(R.id.textView_modified_insulin);
        editText1 = (EditText) findViewById(R.id.editText1_modified_insulin);
        editText2 = (EditText) findViewById(R.id.editText2_modified_insulin);
        editText3 = (EditText) findViewById(R.id.editText3_modified_insulin);
        button = (Button) findViewById(R.id.button_modified_insulin);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(checkInputs())
                    calculate();
                else if(!checkInputs()) {
                    Toast.makeText(getBaseContext(), "ورودیها را درست وارد کنید!", Toast.LENGTH_SHORT).show();

                }
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
    public boolean checkInputs(){
        if(editText1.getText().toString().equals("")||
                editText2.getText().toString().equals("")||
                editText3.getText().toString().equals("")||
                editText1.getText().toString().equals(null)||
                editText2.getText().toString().equals(null)||
                editText3.getText().toString().equals(null)||
                Integer.valueOf(editText1.getText().toString())>500||
                Integer.valueOf(editText2.getText().toString())>500||
                Integer.valueOf(editText3.getText().toString())>500){
            return false;
        }
        else {
            return true;
        }
    }
}
