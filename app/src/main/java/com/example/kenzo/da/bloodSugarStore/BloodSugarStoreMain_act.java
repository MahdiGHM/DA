package com.example.kenzo.da.bloodSugarStore;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.NavUtils;
import android.support.v4.view.ViewPager;
import android.view.MenuItem;

import com.example.kenzo.da.R;
import com.example.kenzo.da.bloodSugarStore.Fragments.OneFragment;
import com.example.kenzo.da.bloodSugarStore.Fragments.ThreeFragment;
import com.example.kenzo.da.bloodSugarStore.Fragments.TwoFragment;
import com.example.kenzo.da.settings.BaseThemedActivity;
import com.example.kenzo.da.settings.ConfigTheme;
import com.example.kenzo.da.util.Util;

public class BloodSugarStoreMain_act extends BaseThemedActivity {
    TabLayout tbLayout;
    ViewPager vPager;
    OneFragment oneFragment = new OneFragment();
    TwoFragment twoFragment = new TwoFragment();
    ThreeFragment threeFragment = new ThreeFragment();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.bloodsugar_store_act);
        setTitle("دفترچه ثبت قند خون");
        ConfigTheme configTheme = new ConfigTheme(this);
        configTheme.configIt();
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        vPager = findViewById(R.id.view_pager);
        tbLayout = findViewById(R.id.tab_layout);
        setupViewPager(vPager);
        tbLayout.setupWithViewPager(vPager);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        NavUtils.navigateUpFromSameTask(BloodSugarStoreMain_act.this);
        return true;
    }
    private void setupViewPager(ViewPager viewPager){
        Util.ViewPagerAdapter adapter = new Util.ViewPagerAdapter(getSupportFragmentManager());
        adapter.addFragment(oneFragment,"دفترچه");
        adapter.addFragment(twoFragment,"نمودار");
        adapter.addFragment(threeFragment,"میانگین");
        viewPager.setAdapter(adapter);
    }
}
