package com.example.kenzo.da;

import android.animation.Animator;
import android.animation.ValueAnimator;
import android.app.ActivityOptions;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.RequiresApi;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GestureDetectorCompat;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.GestureDetector;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ScrollView;

import com.example.kenzo.da.AlarmReminder.AlarmReminderMain_act;
import com.example.kenzo.da.BenefitInfo.Network;
import com.example.kenzo.da.BenefitInfo.UsefulArticlesMain_act;
import com.example.kenzo.da.Calories.Calories_act;
import com.example.kenzo.da.bloodSugarStore.BloodSugarStoreMain_act;
import com.example.kenzo.da.bloodTest.BloodTestMain_act;
import com.example.kenzo.da.insulinDose.InsulinDose_act;
import com.example.kenzo.da.settings.SettingsActivity;
import com.example.kenzo.da.util.HelperView;

import java.io.File;

public class MainActivity extends AppCompatActivity implements View.OnClickListener, GestureDetector.OnGestureListener, NavigationView.OnNavigationItemSelectedListener {
    Intent intent;
    Network network;
    private boolean noInternet;
    Context mContext = MainActivity.this;
    private LinearLayout tilesContainer;
    private ScrollView mainScrollView;
    private int ANIMATION_DURATION = 300; //in milliseconds
    private int firstChildHeight;
    private int defaultChildHeight;
    private boolean toAnimate = true;
    private boolean toFantasticScroll = true;
    private GestureDetectorCompat detector;
    private Toolbar appBar;
    private String[] messages = {
            "دفترچه ثبت قند خون",
            "محاسبه دوز انسولین",
            "محاسبه BMI",
            "آزمایش ها",
            "کالری مواد غذایی",
            "رویداد ها",
            "مطالب مفید"
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_act);

        initView();
        setUpNavigationDrawer();
    }

    private void setUpNavigationDrawer() {
        detector = new GestureDetectorCompat(this, this);
        int displayHeight = getWindowManager().getDefaultDisplay().getHeight();
        firstChildHeight = (displayHeight * 60) / 100;
        defaultChildHeight = displayHeight / 6;

        tilesContainer = (LinearLayout) findViewById(R.id.tileContainer);

        mainScrollView = (ScrollView) findViewById(R.id.mainScrollView);
        mainScrollView.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                return true;
            }
        });

        addTilesToContainer();
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.mainDrawerLayout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, appBar, R.string.action_save_reminder, R.string.action_save_reminder);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
    }

    private void initView() {
        appBar = (Toolbar) findViewById(R.id.landingPageAppBar);
        setSupportActionBar(appBar);
        if (getSupportActionBar() != null) {
            getSupportActionBar().setTitle("");
        }
    }
    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {
        if (toFantasticScroll) {
            detector.onTouchEvent(ev);
        }
        return super.dispatchTouchEvent(ev);
    }

    public void addTilesToContainer() {

        View tileView;

        int[] images = {
                R.drawable.bloodsugarpic,
                R.drawable.insulin,
                R.drawable.bmipic,
                R.drawable.test,
                R.drawable.carbo,
                R.drawable.events,
                R.drawable.health
        };

        int numberOfTiles = 7;
        for (int i = 0; i < numberOfTiles; i++) {
            if (i == 0) {
                tileView = LayoutInflater.from(mContext).inflate(R.layout.chanel_layout, null);
                tileView.setLayoutParams(new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,
                        firstChildHeight));
                tileView.setTag("tile - " + messages[i]);
                tileView.setOnClickListener(this);

                ImageView imageView = (ImageView) tileView.findViewById(R.id.ivBackground);
                imageView.setImageResource(images[i]);
                imageView.setOnClickListener(this);
                imageView.setTag(i);

                Button button = (Button) tileView.findViewById(R.id.btnTileMessage);
                button.setText(messages[i]);
                button.setTag(i);
                button.setOnClickListener(this);

                tilesContainer.addView(tileView);
            } else {
                tileView = LayoutInflater.from(mContext).inflate(R.layout.chanel_layout, null);
                tileView.setLayoutParams(new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,
                        defaultChildHeight));
                tileView.setTag("tile - " + messages[i]);
                tileView.setOnClickListener(this);

                ImageView imageView = (ImageView) tileView.findViewById(R.id.ivBackground);
                imageView.setImageResource(images[i]);
                imageView.setOnClickListener(this);
                imageView.setTag(i);

                Button button = (Button) tileView.findViewById(R.id.btnTileMessage);
                button.setText(messages[i]);
                button.setTag(i);
                button.setOnClickListener(this);


                tilesContainer.addView(tileView);
            }
        }

        HelperView.setPrecedingView(null);
        HelperView.setCurrentView(tilesContainer.getChildAt(0));
        HelperView.setFollowingView(tilesContainer.getChildAt(1));

    }


    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN)
    @Override
    public void onClick(final View v) {

        if (v.getTag().toString().contains("tile")) {
            if (v.getLayoutParams().height != firstChildHeight) {
                expandView(v);
            } else {
                switch (tilesContainer.indexOfChild(v)) {
                    case 0:
                        if (tilesContainer.getChildAt(0).getLayoutParams().height != firstChildHeight) {
                            downToUpScroll(HelperView.getCurrentView(), HelperView.getFollowingView());
                        } else {


                        }
                        break;

                    case 1:
                        if (tilesContainer.getChildAt(1).getLayoutParams().height != firstChildHeight) {
                            downToUpScroll(HelperView.getCurrentView(), HelperView.getFollowingView());
                        }
                        break;

                    case 2:
                        if (tilesContainer.getChildAt(2).getLayoutParams().height != firstChildHeight) {
                            downToUpScroll(HelperView.getCurrentView(), HelperView.getFollowingView());
                        }
                        break;

                    case 3:
                        break;

                    case 4:
                        break;
                    case 5:
                        break;
                }
            }
        }
        Bundle bndlanimation;
        switch (v.getTag().toString()) {
            case "0":
                intent = new Intent(MainActivity.this,BloodSugarStoreMain_act.class);

                break;

            case "1":
                if (tilesContainer.getChildAt(1).getLayoutParams().height != firstChildHeight) {
                    downToUpScroll(HelperView.getCurrentView(), HelperView.getFollowingView());
                }
                intent = new Intent(MainActivity.this,InsulinDose_act.class);

                break;

            case "2":
                if (tilesContainer.getChildAt(2).getLayoutParams().height != firstChildHeight) {
                    downToUpScroll(HelperView.getCurrentView(), HelperView.getFollowingView());
                }
                intent = new Intent(MainActivity.this,BmiCalMain_act.class);
                break;

            case "3":
                intent = new Intent(MainActivity.this,BloodTestMain_act.class);
                break;

            case "4":
                intent = new Intent(MainActivity.this,Calories_act.class);
                break;
            case "5":
                intent = new Intent(MainActivity.this,AlarmReminderMain_act.class);
                break;
            case "6":
                network = new Network(this);
                if(network.isOnline()) {
                    intent = new Intent(MainActivity.this, UsefulArticlesMain_act.class);
                }
                else{
                    noInternetM();
                    noInternet = true;
                }

                break;
        }

        if(v.getTag().toString().equals("6")&&!noInternet) {
            bndlanimation = ActivityOptions.makeCustomAnimation(getApplicationContext(), R.anim.fab_scale_up, R.anim.fab_scale_down).toBundle();
            startActivity(intent, bndlanimation);

        }
        else if(!v.getTag().toString().equals("6")){
            bndlanimation = ActivityOptions.makeCustomAnimation(getApplicationContext(), R.anim.fab_scale_up, R.anim.fab_scale_down).toBundle();
            startActivity(intent, bndlanimation);
        }

    }

    private void noInternetM() {
        new AlertDialog.Builder(this)
                .setTitle("شما به اینترنت دسترسی ندارید!")
                .setMessage("اتصالات خود را بررسی کنید ، سپس مجدد امتحان کنید.")
                .setPositiveButton("باشه", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        if(network.isOnline()) {
                            intent = new Intent(MainActivity.this, UsefulArticlesMain_act.class);
                            startActivity(intent);
                        }
                        else
                            noInternetM();
                    }
                })
                .setNegativeButton("خیر", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Log.d("MainActivity", "Aborting mission...");
                    }
                })
                .show();
    }

    public void expandView(final View view) {
        int currentScrollPosition = mainScrollView.getScrollY();
        int finalScrollPosition = view.getTop();

        ValueAnimator scrollAnimator = ValueAnimator.ofInt(currentScrollPosition, finalScrollPosition);
        scrollAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                int amount = (int) animation.getAnimatedValue();
                mainScrollView.scrollTo(0, amount);
            }
        });
        scrollAnimator.setDuration(ANIMATION_DURATION);

        ValueAnimator heightAnimator = ValueAnimator.ofInt(view.getHeight(), firstChildHeight);
        heightAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                int height = (int) animation.getAnimatedValue();
                view.getLayoutParams().height = height;
                view.requestLayout();
            }
        });
        heightAnimator.setDuration(ANIMATION_DURATION);

        scrollAnimator.start();
        heightAnimator.start();

        if (tilesContainer.indexOfChild(view) == 0) {
            //do nothing
        } else {
            HelperView.setPrecedingView(tilesContainer.getChildAt(tilesContainer.indexOfChild(view) - 1));
        }
        HelperView.setCurrentView(view);
        HelperView.setFollowingView(tilesContainer.getChildAt(tilesContainer.indexOfChild(view) + 1));
    }


    @Override
    public boolean onDown(MotionEvent e) {
        //return true because every gesture start with onDown
        return true;
    }

    @Override
    public void onShowPress(MotionEvent e) {

    }

    @Override
    public boolean onSingleTapUp(MotionEvent e) {
        return false;
    }

    @Override
    public boolean onScroll(MotionEvent e1, MotionEvent e2, float distanceX, float distanceY) {
        return false;
    }

    @Override
    public void onLongPress(MotionEvent e) {

    }

    @Override
    public boolean onFling(MotionEvent e1, MotionEvent e2, float velocityX, float velocityY) {

        final int SWIPE_MIN_DISTANCE = 50;
        final int SWIPE_THRESHOLD_VELOCITY = 200;

        if (e1.getY() - e2.getY() > SWIPE_MIN_DISTANCE &&
                Math.abs(velocityY) > SWIPE_THRESHOLD_VELOCITY) {
            //Toast.makeText(this, "Bottom to top", Toast.LENGTH_SHORT).show();
            if (HelperView.getFollowingView() != null) {
                downToUpScroll(HelperView.getCurrentView(), HelperView.getFollowingView());
            }
            //From Bottom to top
            return true;
        } else if (e2.getY() - e1.getY() > SWIPE_MIN_DISTANCE &&
                Math.abs(velocityY) > SWIPE_THRESHOLD_VELOCITY) {
            //Toast.makeText(this, "top to Bottom", Toast.LENGTH_SHORT).show();
            if (HelperView.getPrecedingView() != null) {
                upToDownScroll(HelperView.getPrecedingView(), HelperView.getCurrentView());
            }
            //From top to Bottom
            return true;
        }

        return true;
    }

    public void upToDownScroll(final View precedingView, final View currentView) {

        if (toAnimate) {

            toAnimate = false;

            if (tilesContainer.indexOfChild(currentView) == 0) {
                //do-nothing
            } else {
                int currentScrollPosition = mainScrollView.getScrollY();
                int toScrollPosition = precedingView.getTop();

                ValueAnimator scrollAnimator = ValueAnimator.ofInt(currentScrollPosition, toScrollPosition);
                scrollAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
                    @Override
                    public void onAnimationUpdate(ValueAnimator animation) {
                        int amount = (int) animation.getAnimatedValue();
                        mainScrollView.scrollTo(0, amount);
                    }
                });
                scrollAnimator.setDuration(ANIMATION_DURATION);

                ValueAnimator heightAnimator = ValueAnimator.ofInt(currentView.getLayoutParams().height, defaultChildHeight);
                heightAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
                    @Override
                    public void onAnimationUpdate(ValueAnimator animation) {
                        int height = (int) animation.getAnimatedValue();
                        currentView.getLayoutParams().height = height;
                        currentView.requestLayout();
                    }
                });
                heightAnimator.setDuration(ANIMATION_DURATION);

                scrollAnimator.start();
                heightAnimator.start();

                HelperView.setCurrentView(precedingView);
                HelperView.setPrecedingView(tilesContainer.getChildAt(tilesContainer.indexOfChild(precedingView) - 1));
                HelperView.setFollowingView(currentView);

                scrollAnimator.addListener(new Animator.AnimatorListener() {
                    @Override
                    public void onAnimationStart(Animator animation) {

                        toAnimate = false;
                    }

                    @Override
                    public void onAnimationEnd(Animator animation) {
                        toAnimate = true;
                    }

                    @Override
                    public void onAnimationCancel(Animator animation) {

                    }

                    @Override
                    public void onAnimationRepeat(Animator animation) {

                    }
                });
            }
        }
    }

    public void downToUpScroll(View currentView, final View followingView) {

        if (toAnimate) {

            toAnimate = false;

            int currentScrollPosition = mainScrollView.getScrollY();
            int toScrollPosition = followingView.getTop();

            ValueAnimator scrollAnimator = ValueAnimator.ofInt(currentScrollPosition, toScrollPosition);
            scrollAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
                @Override
                public void onAnimationUpdate(ValueAnimator animation) {
                    int amount = (int) animation.getAnimatedValue();
                    mainScrollView.scrollTo(0, amount);
                }
            });
            scrollAnimator.setDuration(ANIMATION_DURATION);

            ValueAnimator heightAnimator = ValueAnimator.ofInt(followingView.getHeight(), firstChildHeight);
            heightAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
                @Override
                public void onAnimationUpdate(ValueAnimator animation) {
                    int height = (int) animation.getAnimatedValue();
                    followingView.getLayoutParams().height = height;
                    followingView.requestLayout();
                }
            });
            heightAnimator.setDuration(ANIMATION_DURATION);

            scrollAnimator.start();
            heightAnimator.start();

            scrollAnimator.addListener(new Animator.AnimatorListener() {
                @Override
                public void onAnimationStart(Animator animation) {
                }

                @Override
                public void onAnimationEnd(Animator animation) {
                    toAnimate = true;
                }

                @Override
                public void onAnimationCancel(Animator animation) {

                }

                @Override
                public void onAnimationRepeat(Animator animation) {

                }
            });

            HelperView.setPrecedingView(currentView);
            HelperView.setCurrentView(followingView);
            HelperView.setFollowingView(tilesContainer.getChildAt(tilesContainer.indexOfChild(followingView) + 1));
        }
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        int id = item.getItemId();
        switch (id){
            case R.id.bss:
                intent = new Intent(MainActivity.this,BloodSugarStoreMain_act.class);
                startActivity(intent);
                break;
            case R.id.idc:
                intent = new Intent(MainActivity.this,InsulinDose_act.class);
                startActivity(intent);
                break;
            case R.id.bmic:
                intent = new Intent(MainActivity.this,BmiCalMain_act.class);
                startActivity(intent);
                break;
            case R.id.bt:
                intent = new Intent(MainActivity.this,BloodTestMain_act.class);
                startActivity(intent);
                break;
            case R.id.colories:
                intent = new Intent(MainActivity.this,Calories_act.class);
                startActivity(intent);
                break;
            case R.id.usefularticle:
                intent = new Intent(MainActivity.this,UsefulArticlesMain_act.class);
                startActivity(intent);
                break;
            case R.id.event:
                intent = new Intent(MainActivity.this,AlarmReminderMain_act.class);
                startActivity(intent);
                break;
            case R.id.settings:
                intent = new Intent(MainActivity.this,SettingsActivity.class);
                startActivity(intent);
                break;
            case R.id.nav_share:
                try
                {
                    PackageManager pm = getPackageManager();
                    ApplicationInfo ai = pm.getApplicationInfo(getPackageName(), 0);
                    File srcFile = new File(ai.publicSourceDir);
                    Intent share = new Intent();
                    share.setAction(Intent.ACTION_SEND);
                    share.setType("application/vnd.android.package-archive");
                    share.putExtra(Intent.EXTRA_STREAM, Uri.fromFile(srcFile));
                    startActivity(Intent.createChooser(share, "share DA"));
                } catch (Exception e) {
                    Log.e("ShareApp", e.getMessage());
                }
                break;
            case R.id.nav_send:
                try {
                    Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse("tg://resolve?domain=MahdiGHM"));
                    startActivity(intent);
                }catch (Exception e1){
                    Log.e("TelegramE1", e1.getMessage());
                }catch (ExceptionInInitializerError e2){
                    Log.e("TelegramE2", e2.getMessage());
                }
                break;
            case R.id.exit:
                finish();
                System.exit(0);
                break;
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.mainDrawerLayout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
}