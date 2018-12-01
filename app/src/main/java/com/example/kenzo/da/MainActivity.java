package com.example.kenzo.da;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;

import com.example.kenzo.da.AlarmReminder.AlarmReminderMain_act;
import com.example.kenzo.da.Calories.Calories_act;
import com.example.kenzo.da.bloodSugarStore.BloodSugarStoreMain_act;
import com.example.kenzo.da.bloodTest.BloodTestMain_act;
import com.example.kenzo.da.insulinDose.InsulinDose_act;
import com.example.kenzo.da.usefulArticles.UsefulArticlesMain_act;

public class MainActivity extends AppCompatActivity {
    Intent intent;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        getSupportActionBar().setDisplayOptions(ActionBar.DISPLAY_SHOW_CUSTOM);
        getSupportActionBar().setCustomView(R.layout.actionbar);
        ((TextView)findViewById(R.id.action_bar_title)).setText("منوی اصلی");
    }
    public void runActivityMain(View view){
        int ID = view.getId();
        switch (ID) {
            case R.id.button1 : intent = new Intent(MainActivity.this,BloodSugarStoreMain_act.class); break;
            case R.id.button2 : intent = new Intent(MainActivity.this,InsulinDose_act.class); break;
            case R.id.button3 : intent = new Intent(MainActivity.this,BmiCalMain_act.class); break;
            case R.id.button4 : intent = new Intent(MainActivity.this,BloodTestMain_act.class); break;
            case R.id.button5 : intent = new Intent(MainActivity.this,Calories_act.class); break;
            case R.id.button6 : intent = new Intent(MainActivity.this,AlarmReminderMain_act.class); break;
            case R.id.button7 : intent = new Intent(MainActivity.this,UsefulArticlesMain_act.class); break;
        }
        startActivity(intent);
    }
}