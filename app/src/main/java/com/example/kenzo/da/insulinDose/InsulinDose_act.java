package com.example.kenzo.da.insulinDose;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.NavUtils;
import android.view.MenuItem;
import android.view.View;

import com.example.kenzo.da.R;
import com.example.kenzo.da.settings.BaseThemedActivity;
import com.example.kenzo.da.settings.ConfigTheme;

public class InsulinDose_act extends BaseThemedActivity {
    Intent intent;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.insulin_dose_act);
        setTitle("محاسبه دوز انسولین");
        ConfigTheme configTheme = new ConfigTheme(this);
        configTheme.configIt();
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }
    public void runInsulinActivity(View view){
        int ID = view.getId();
        switch (ID){
            case R.id.button12 : intent = new Intent(InsulinDose_act.this,InsulinToCarbo_act.class); break;
            case R.id.button15 : intent = new Intent(InsulinDose_act.this,InsulinEffect_act.class); break;
            case R.id.button16 : intent = new Intent(InsulinDose_act.this,ModifiedInsulin_act.class); break;
        }
        startActivity(intent);
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        NavUtils.navigateUpFromSameTask(InsulinDose_act.this);
        return true;
    }
}
