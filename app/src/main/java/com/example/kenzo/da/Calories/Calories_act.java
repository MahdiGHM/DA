package com.example.kenzo.da.Calories;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;

import com.example.kenzo.da.Calories.fragments.foodCategory;
import com.example.kenzo.da.R;
import com.example.kenzo.da.settings.BaseThemedActivity;
import com.example.kenzo.da.settings.ConfigTheme;

public class Calories_act extends BaseThemedActivity {
    public FragmentManager fragmentManager;
    private foodCategory foodCategory;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.calories_act);
        setTitle("کالری مواد غذایی");
        ConfigTheme configTheme = new ConfigTheme(this);
        configTheme.configIt();
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        fragmentManager=getSupportFragmentManager();
        foodCategory=new foodCategory();
        FragmentTransaction ft = fragmentManager.beginTransaction();
        ft.replace(R.id.content_frame, foodCategory);
        ft.commit();
    }

}
