package com.example.kenzo.da;

import android.graphics.Color;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.kenzo.da.settings.BaseThemedActivity;
import com.example.kenzo.da.settings.ConfigTheme;
import com.shinelw.library.ColorArcProgressBar;

public class BmiCalMain_act extends BaseThemedActivity {
    EditText editText1;
    EditText editText2;
    TextView textView,textView1,textView2;
    Button button;
    ColorArcProgressBar progressBar;
    boolean isClick;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.bmi_cal_act);
        ConfigTheme configTheme = new ConfigTheme(this);
        configTheme.configIt();
        setTitle("BMI Calculator");
        Typeface tvtf = Typeface.createFromAsset(getAssets(),"fonts/Samim.ttf");
        textView = (TextView) findViewById(R.id.textView1_bmi);
        editText1 = (EditText) findViewById(R.id.editText1_bmi);
        editText2 = (EditText) findViewById(R.id.editText2_bmi);
        textView1 = (TextView) findViewById(R.id.textView3_bmi);
        textView2 = (TextView) findViewById(R.id.textView2_bmi);
        textView.setTypeface(tvtf);
        textView1.setTypeface(tvtf);
        textView2.setTypeface(tvtf);
        editText1.setTextColor(Color.BLACK);
        editText2.setTextColor(Color.BLACK);
        progressBar = (ColorArcProgressBar) findViewById(R.id.bar1);
        button = (Button) findViewById(R.id.button_bmi);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(checkInputs())
                    if(!isClick){
                        afterClick();
                        calculate();
                    }
                    else {
                        reset();
                    }
                if(!checkInputs()) {
                    Toast.makeText(getBaseContext(), "ورودیها را درست وارد کنید!", Toast.LENGTH_SHORT).show();

                }

            }
        });

    }
    public void calculate(){
        String s="";
        double f = ((double)(Integer.parseInt(editText2.getText().toString()))/(((double)(Integer.parseInt(editText1.getText().toString()))/100)*((double)(Integer.parseInt(editText1.getText().toString()))/100)));
        progressBar.setCurrentValues((float) f);
        if(Double.compare(f,18.5)<0)
            s = "طبق این عدد شما دارای کمبود وزن می باشید.";
        else if(Double.compare(f,24.9)<=0 && Double.compare(f,18.5)>=0)
            s = "طبق این عدد شما دارای وزن طبیعی می باشید.";
        else if(Double.compare(f,29.9)<=0 && Double.compare(f,25.0)>=0)
            s = "طبق این عدد شما دارای اضافه وزن می باشید.";
        else if(Double.compare(f,30.0)>=0)
            s = "طبق این عدد شما دارای بیماری چاقی می باشید.";
        textView.setText("شاخص توده بدنی (BMI) شما "+String.format("%.1f",f)+"می باشد."+s);
    }
    public void afterClick(){
        textView.setVisibility(View.VISIBLE);
        editText1.setVisibility(View.INVISIBLE);
        editText2.setVisibility(View.INVISIBLE);
        textView1.setVisibility(View.INVISIBLE);
        textView2.setVisibility(View.INVISIBLE);
        button.setText("محاسبه مجدد");
        isClick = true;
    }
    public void reset(){
        textView.setVisibility(View.INVISIBLE);
        editText1.setVisibility(View.VISIBLE);
        editText2.setVisibility(View.VISIBLE);
        textView1.setVisibility(View.VISIBLE);
        textView2.setVisibility(View.VISIBLE);
        button.setText("محاسبه");
        isClick = false;
    }
    public boolean checkInputs(){
        if(editText1.getText().toString().equals("")||
                editText2.getText().toString().equals("")||
                editText1.getText().toString().equals(null)||
                editText2.getText().toString().equals(null)||
                Integer.valueOf(editText1.getText().toString())>500 ||
                Integer.valueOf(editText2.getText().toString())>500){
            return false;
        }
        else {
            return true;
        }
    }

}
