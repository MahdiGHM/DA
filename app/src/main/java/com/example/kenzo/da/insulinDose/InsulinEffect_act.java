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

public class InsulinEffect_act extends BaseThemedActivity {
    EditText editText;
    TextView textView;
    Button button;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.insulin_effect_act);
        setTitle("محاسبه دوز انسولین");
        ConfigTheme configTheme = new ConfigTheme(this);
        configTheme.configIt();
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        textView = (TextView) findViewById(R.id.textView_insulin_effect);
        editText = (EditText) findViewById(R.id.editText_insulin_effect);
        button = (Button) findViewById(R.id.button_insulin_effect);
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
        float f= (float)1800/(Integer.parseInt(editText.getText().toString()));
        textView.setText("هر یک واحد انسولین کوتاه اثر (نوو رپید) "+String.format("%.1f",f)+" تا قند خون شما را پایین می آورد.");
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        NavUtils.navigateUpFromSameTask(InsulinEffect_act.this);
        return true;
    }
    public boolean checkInputs(){
        if(editText.getText().toString().equals("")||
                editText.getText().toString().equals(null)||
                Integer.valueOf(editText.getText().toString())>500){
            return false;
        }
        else {
            return true;
        }
    }
}
