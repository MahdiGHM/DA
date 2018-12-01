package com.example.kenzo.da.Calories;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

import com.example.kenzo.da.Calories.fragments.foodCategory;
import com.example.kenzo.da.R;

public class Calories_act extends AppCompatActivity {
    public FragmentManager fragmentManager;
    private foodCategory foodCategory;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.calories_act);
        getSupportActionBar().setDisplayOptions(ActionBar.DISPLAY_SHOW_CUSTOM);
        getSupportActionBar().setCustomView(R.layout.actionbar);
        ((TextView)findViewById(R.id.action_bar_title)).setText("کربوهیدرات شمار");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        fragmentManager=getSupportFragmentManager();
        foodCategory=new foodCategory();
        FragmentTransaction ft = fragmentManager.beginTransaction();
        ft.replace(R.id.content_frame, foodCategory);
        ft.commit();
    }

}
