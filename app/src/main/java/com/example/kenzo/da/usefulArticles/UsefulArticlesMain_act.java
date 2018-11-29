package com.example.kenzo.da.usefulArticles;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.NavUtils;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import com.example.kenzo.da.R;

public class UsefulArticlesMain_act extends AppCompatActivity {
    Intent intent;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.useful_articles_act);
        getSupportActionBar().setDisplayOptions(ActionBar.DISPLAY_SHOW_CUSTOM);
        getSupportActionBar().setCustomView(R.layout.actionbar);
        ((TextView)findViewById(R.id.action_bar_title)).setText("مطالب مفید");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        NavUtils.navigateUpFromSameTask(UsefulArticlesMain_act.this);
        return true;
    }
    public void runUseful(View view){
        intent = new Intent(UsefulArticlesMain_act.this,UsefulArticlesView_act.class);
        int ID = view.getId();
        switch (ID) {
            case R.id.useful_articles_act_button1 : intent.putExtra("which",R.id.useful_articles_act_button1);break;
            case R.id.useful_articles_act_button2 : intent.putExtra("which",R.id.useful_articles_act_button2);break;
            case R.id.useful_articles_act_button3 : intent.putExtra("which",R.id.useful_articles_act_button3);break;
            case R.id.useful_articles_act_button4 : intent.putExtra("which",R.id.useful_articles_act_button4);break;
            case R.id.useful_articles_act_button5 : intent.putExtra("which",R.id.useful_articles_act_button5);break;
            case R.id.useful_articles_act_button6 : intent.putExtra("which",R.id.useful_articles_act_button6);break;
            case R.id.useful_articles_act_button7 : intent.putExtra("which",R.id.useful_articles_act_button7);break;
            case R.id.useful_articles_act_button8 : intent.putExtra("which",R.id.useful_articles_act_button8);break;
        }
        startActivity(intent);
    }

}
