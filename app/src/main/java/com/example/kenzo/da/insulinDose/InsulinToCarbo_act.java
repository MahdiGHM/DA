package com.example.kenzo.da.insulinDose;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.example.kenzo.da.R;

public class InsulinToCarbo_act extends AppCompatActivity {
    EditText editText;
    TextView textView;
    Button button;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.insulin_to_carbo_act);
        textView = (TextView) findViewById(R.id.textView_modified_insulin);
        editText = (EditText) findViewById(R.id.editText_insulin_to_carbo);
        button = (Button) findViewById(R.id.button_insulin_to_carbo);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                calculate();
            }
        });
    }
    public void calculate(){
        float f= (float)(Integer.parseInt(editText.getText().toString()))/30;
        textView.setText("برای هر واحد کربوهیدرات "+String.format("%.1f",f)+" واحد انسولین باید تزریق گردد.");
    }
}