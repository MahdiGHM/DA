package com.example.kenzo.da;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import com.shinelw.library.ColorArcProgressBar;

public class BmiCalMain_act extends AppCompatActivity {
    EditText editText1;
    EditText editText2;
    TextView textView;
    Button button;
    ColorArcProgressBar progressBar;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.bmi_cal_act);
        textView = (TextView) findViewById(R.id.textView1_bmi);
        editText1 = (EditText) findViewById(R.id.editText1_bmi);
        editText2 = (EditText) findViewById(R.id.editText2_bmi);
        progressBar = (ColorArcProgressBar) findViewById(R.id.bar1);
        button = (Button) findViewById(R.id.button_bmi);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                calculate();
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
}
