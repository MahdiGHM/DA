package com.example.kenzo.da.AlarmReminder;

import android.content.ContentUris;
import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.LoaderManager;
import android.support.v4.app.NavUtils;
import android.support.v4.content.CursorLoader;
import android.support.v4.content.Loader;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.example.kenzo.da.AlarmReminder.data.AlarmReminderContract;
import com.example.kenzo.da.R;
import com.example.kenzo.da.settings.BaseThemedActivity;
import com.example.kenzo.da.settings.ConfigTheme;

public class AlarmReminderMain_act extends BaseThemedActivity implements LoaderManager.LoaderCallbacks<Cursor> {

    private FloatingActionButton mAddReminderButton;
    AlarmCursorAdapter mCursorAdapter;
    ListView reminderListView;

    private String alarmTitle = "";

    private static final int VEHICLE_LOADER = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.alarm_reminder_act);
        setTitle("رویداد ها");
        ConfigTheme configTheme = new ConfigTheme(this);
        configTheme.configIt();
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        reminderListView = (ListView) findViewById(R.id.list);

        View emptyView = findViewById(R.id.empty_view);
        reminderListView.setEmptyView(emptyView);

        mCursorAdapter = new AlarmCursorAdapter(this, null);
        reminderListView.setAdapter(mCursorAdapter);

        reminderListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long id) {
                Intent intent = new Intent(AlarmReminderMain_act.this, AddReminderActivity.class);
                Uri currentVehicleUri = ContentUris.withAppendedId(AlarmReminderContract.AlarmReminderEntry.CONTENT_URI, id);
                intent.setData(currentVehicleUri);
                startActivity(intent);
            }
        });
        mAddReminderButton = (FloatingActionButton) findViewById(R.id.fab);
        mAddReminderButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addReminderTitle();
            }
        });
        getSupportLoaderManager().initLoader(VEHICLE_LOADER, null, this);
    }

    @Override
    public Loader<Cursor> onCreateLoader(int i, Bundle bundle) {
        String[] projection = {
                AlarmReminderContract.AlarmReminderEntry._ID,
                AlarmReminderContract.AlarmReminderEntry.KEY_TITLE,
                AlarmReminderContract.AlarmReminderEntry.KEY_DATE,
                AlarmReminderContract.AlarmReminderEntry.KEY_TIME,
                AlarmReminderContract.AlarmReminderEntry.KEY_REPEAT,
                AlarmReminderContract.AlarmReminderEntry.KEY_REPEAT_NO,
                AlarmReminderContract.AlarmReminderEntry.KEY_REPEAT_TYPE,
                AlarmReminderContract.AlarmReminderEntry.KEY_ACTIVE

        };

        return new CursorLoader(this,   // Parent activity context
                AlarmReminderContract.AlarmReminderEntry.CONTENT_URI,   // Provider content URI to query
                projection,             // Columns to include in the resulting Cursor
                null,                   // No selection clause
                null,                   // No selection arguments
                null);                  // Default sort order

    }

    @Override
    public void onLoadFinished(Loader<Cursor> loader, Cursor cursor) {
        mCursorAdapter.swapCursor(cursor);
    }

    @Override
    public void onLoaderReset(Loader<Cursor> loader) {
        mCursorAdapter.swapCursor(null);

    }

    public void addReminderTitle() {

        ContentValues values = new ContentValues();
        values.put(AlarmReminderContract.AlarmReminderEntry.KEY_TITLE, alarmTitle);
        getContentResolver().insert(AlarmReminderContract.AlarmReminderEntry.CONTENT_URI, values);
        restartLoader();
        Handler handler = new Handler();
        int t = reminderListView.getAdapter().getCount();
        if(t > 0){
            handler.postDelayed(new Runnable() {
                @Override
                public void run() {
                    int t = reminderListView.getAdapter().getCount();
                    t = t - 1;
                    reminderListView.performItemClick(reminderListView.getAdapter().getView(t, null, null), t, reminderListView.getAdapter().getItemId(t));
                }
            }, 100);
        }else{
            handler.postDelayed(new Runnable() {
                @Override
                public void run() {
                    reminderListView.performItemClick(reminderListView.getAdapter().getView(0,null,null), 0, reminderListView.getAdapter().getItemId(0));}
            }, 100);
        }
    }
    public void restartLoader(){
        getSupportLoaderManager().restartLoader(VEHICLE_LOADER, null, this);
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        NavUtils.navigateUpFromSameTask(AlarmReminderMain_act.this);
        return true;
    }
}