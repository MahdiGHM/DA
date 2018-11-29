package com.example.kenzo.da.bloodSugarStore;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.NavUtils;
import android.support.v4.view.ViewPager;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.widget.TextView;

import com.example.kenzo.da.R;
import com.example.kenzo.da.bloodSugarStore.Fragments.OneFragment;
import com.example.kenzo.da.bloodSugarStore.Fragments.ThreeFragment;
import com.example.kenzo.da.bloodSugarStore.Fragments.TwoFragment;

public class BloodSugarStoreMain_act extends AppCompatActivity {
    TabLayout tbLayout;
    ViewPager vPager;
    OneFragment oneFragment = new OneFragment();
    TwoFragment twoFragment = new TwoFragment();
    ThreeFragment threeFragment = new ThreeFragment();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.bloodsugar_store_act);
        getSupportActionBar().setDisplayOptions(ActionBar.DISPLAY_SHOW_CUSTOM);
        getSupportActionBar().setCustomView(R.layout.actionbar);
        ((TextView)findViewById(R.id.action_bar_title)).setText("دفترچه ثبت قند خون");
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
