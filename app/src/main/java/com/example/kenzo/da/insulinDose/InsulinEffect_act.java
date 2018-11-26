package com.example.kenzo.da.insulinDose;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.example.kenzo.da.R;

public class InsulinEffect_act extends AppCompatActivity {
    EditText editText;
    TextView textView;
    Button button;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.insulin_effect_act);
        textView = (TextView) findViewById(R.id.textView_insulin_effect);
        editText = (EditText) findViewById(R.id.editText_insulin_effect);
        button = (Button) findViewById(R.id.button_insulin_effect);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                calculate();
            }
        });
    }
    public void calculate(){
        float f= (float)1800/(Integer.parseInt(editText.getText().toString()));
        textView.setText("هر یک واحد انسولین کوتاه اثر (نوو رپید) "+String.format("%.1f",f)+" تا قند خون شما را پایین می آورد.");
    }
}
