package com.example.kenzo.da;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.example.kenzo.da.AlarmReminder.data.AlarmReminderContract;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class DatabaseHelper extends SQLiteOpenHelper {
    public static final String DATABASE_NAME = "DA.db";
    public static final String TABLE_NAME_BSS = "DA_BSS_table";
    public static final String COL_BSS_1 = "ID";
    public static final String COL_BSS_2 = "Value_of_blood_sugar";
    public static final String COL_BSS_3 = "Date";
    public static final String COL_BSS_4 = "Timespan";
    public static final String COL_BSS_5 = "Time";

    public static final String TABLE_NAME_BT = "DA_BT_table";
    public static final String COL_BT_1 = "ID";
    public static final String COL_BT_2 = "Lab_name";
    public static final String COL_BT_3 = "Date";
    public static final String COL_BT_4 = "HbA1C";
    public static final String COL_BT_5 = "WBC";
    public static final String COL_BT_6 = "RBC";
    public static final String COL_BT_7 = "HGB";
    public static final String COL_BT_8 = "HCT";
    public static final String COL_BT_9 = "MCV";
    public static final String COL_BT_10 = "MCH";
    public static final String COL_BT_11 = "MCHC";
    public static final String COL_BT_12 = "PLATELETS";
    public static final String COL_BT_13 = "FBS";
    public static final String COL_BT_14 = "Urea";
    public static final String COL_BT_15 = "Creatinine";
    public static final String COL_BT_16 = "Uric_acid";
    public static final String COL_BT_17 = "Total_cholesterol";
    public static final String COL_BT_18 = "Triglycerides";
    public static final String COL_BT_19 = "HDL_cholesterol";
    public static final String COL_BT_20 = "LDL_cholesterol";
    public static final String COL_BT_21 = "SGOT";
    public static final String COL_BT_22 = "SGPT";
    public static final String COL_BT_23 = "Alkaline_phosphatase";
    public static final String COL_BT_24 = "Ca";
    public static final String COL_BT_25 = "P";
    public static final String COL_BT_26 = "Fe";
    public static final String COL_BT_27 = "VitD";
    public static final String COL_BT_28 = "B12";
    public static final String COL_BT_29 = "T3";
    public static final String COL_BT_30 = "T4";
    public static final String COL_BT_31 = "TSH";



    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {


        db.execSQL("create table " +TABLE_NAME_BSS+" (ID INTEGER PRIMARY KEY AUTOINCREMENT,Value_of_blood_sugar INTEGER,Date TEXT DEFAULT (DATE('now','localtime')),Timespan TEXT,Time TEXT)");


        db.execSQL("create table " +TABLE_NAME_BT+" (ID INTEGER PRIMARY KEY AUTOINCREMENT,Lab_name TEXT,Date TEXT,HbA1C INTEGER ,WBC INTEGER,RBC INTEGER" +
                ",HGB INTEGER,HCT INTEGER,MCV INTEGER,MCH INTEGER,MCHC INTEGER,PLATELETS INTEGER,FBS INTEGER,Urea INTEGER,Creatinine INTEGER,Uric_acid INTEGER,Total_cholesterol INTEGER" +
                ",Triglycerides INTEGER,HDL_cholesterol INTEGER,LDL_cholesterol INTEGER,SGOT INTEGER,SGPT INTEGER,Alkaline_phosphatase INTEGER,Ca INTEGER,P INTEGER,Fe INTEGER,VitD INTEGER,B12 INTEGER" +
                ",T3 INTEGER,T4 INTEGER,TSH INTEGER)");


        db.execSQL("CREATE TABLE " + AlarmReminderContract.AlarmReminderEntry.TABLE_NAME + " ("
                + AlarmReminderContract.AlarmReminderEntry._ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                + AlarmReminderContract.AlarmReminderEntry.KEY_TITLE + " TEXT, "
                + AlarmReminderContract.AlarmReminderEntry.KEY_DATE + " TEXT, "
                + AlarmReminderContract.AlarmReminderEntry.KEY_TIME + " TEXT, "
                + AlarmReminderContract.AlarmReminderEntry.KEY_REPEAT + " TEXT, "
                + AlarmReminderContract.AlarmReminderEntry.KEY_REPEAT_NO + " TEXT, "
                + AlarmReminderContract.AlarmReminderEntry.KEY_REPEAT_TYPE + " TEXT, "
                + AlarmReminderContract.AlarmReminderEntry.KEY_ACTIVE + " TEXT " + " );");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS "+TABLE_NAME_BSS);
        db.execSQL("DROP TABLE IF EXISTS "+TABLE_NAME_BT);
        onCreate(db);
    }
    public boolean insertDataBss(int value,String timeSpan){
        SQLiteDatabase db = this.getWritableDatabase();
        Calendar calendar = Calendar.getInstance();
        Date time=calendar.getTime();
        DateFormat timeFormat = new SimpleDateFormat("HH:mm:ss");
        String formattedTime = timeFormat.format(time);
        ContentValues contentValues = new ContentValues();
        contentValues.put(COL_BSS_2,value);
        contentValues.put(COL_BSS_4,timeSpan);
        contentValues.put(COL_BSS_5,formattedTime);
        long result = db.insert(TABLE_NAME_BSS,null,contentValues);
        if(result== -1)
            return false;
        else
            return true;
    }
    public Cursor getAllBssData(){
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor res = db.rawQuery("select * from "+TABLE_NAME_BSS,null);
        return res;
    }
    public boolean updateBssData(int id,int value,String timeSpan){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(COL_BSS_1,id);
        contentValues.put(COL_BSS_2,value);
        contentValues.put(COL_BSS_4,timeSpan);
        db.update(TABLE_NAME_BSS,contentValues,"ID = ?",new String[] { Integer.toString(id) });
        return true;
    }
    public Integer deleteBssData(int id){
       SQLiteDatabase db = this.getWritableDatabase();
       return db.delete(TABLE_NAME_BSS,"ID = ?",new String[] {Integer.toString(id)});
    }
    public  boolean insertDataBtData(int[] v,String ln,String d){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(COL_BT_2,ln);
        contentValues.put(COL_BT_3,d);
        contentValues.put(COL_BT_4,v[0]);
        contentValues.put(COL_BT_5,v[1]);
        contentValues.put(COL_BT_6,v[2]);
        contentValues.put(COL_BT_7,v[3]);
        contentValues.put(COL_BT_8,v[4]);
        contentValues.put(COL_BT_9,v[5]);
        contentValues.put(COL_BT_10,v[6]);
        contentValues.put(COL_BT_11,v[7]);
        contentValues.put(COL_BT_12,v[8]);
        contentValues.put(COL_BT_13,v[9]);
        contentValues.put(COL_BT_14,v[10]);
        contentValues.put(COL_BT_15,v[11]);
        contentValues.put(COL_BT_16,v[12]);
        contentValues.put(COL_BT_17,v[13]);
        contentValues.put(COL_BT_18,v[14]);
        contentValues.put(COL_BT_19,v[15]);
        contentValues.put(COL_BT_20,v[16]);
        contentValues.put(COL_BT_21,v[17]);
        contentValues.put(COL_BT_22,v[18]);
        contentValues.put(COL_BT_23,v[19]);
        contentValues.put(COL_BT_24,v[20]);
        contentValues.put(COL_BT_25,v[21]);
        contentValues.put(COL_BT_26,v[22]);
        contentValues.put(COL_BT_27,v[23]);
        contentValues.put(COL_BT_28,v[24]);
        contentValues.put(COL_BT_29,v[25]);
        contentValues.put(COL_BT_30,v[26]);
        contentValues.put(COL_BT_31,v[27]);
        long result = db.insert(TABLE_NAME_BT,null,contentValues);
        if(result== -1)
            return false;
        else
            return true;
    }
    public Cursor getAllBTData(){
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor res = db.rawQuery("select * from "+TABLE_NAME_BT,null);
        return res;
    }
    public Cursor getBTData(int id){
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor res = db.rawQuery("select * from "+TABLE_NAME_BT+" WHERE ID="+id,null);
        return res;
    }
    public boolean updateBTData(int [] v,int id,String ln,String d){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(COL_BT_2,ln);
        contentValues.put(COL_BT_3,d);
        contentValues.put(COL_BT_4,v[0]);
        contentValues.put(COL_BT_5,v[1]);
        contentValues.put(COL_BT_6,v[2]);
        contentValues.put(COL_BT_7,v[3]);
        contentValues.put(COL_BT_8,v[4]);
        contentValues.put(COL_BT_9,v[5]);
        contentValues.put(COL_BT_10,v[6]);
        contentValues.put(COL_BT_11,v[7]);
        contentValues.put(COL_BT_12,v[8]);
        contentValues.put(COL_BT_13,v[9]);
        contentValues.put(COL_BT_14,v[10]);
        contentValues.put(COL_BT_15,v[11]);
        contentValues.put(COL_BT_16,v[12]);
        contentValues.put(COL_BT_17,v[13]);
        contentValues.put(COL_BT_18,v[14]);
        contentValues.put(COL_BT_19,v[15]);
        contentValues.put(COL_BT_20,v[16]);
        contentValues.put(COL_BT_21,v[17]);
        contentValues.put(COL_BT_22,v[18]);
        contentValues.put(COL_BT_23,v[19]);
        contentValues.put(COL_BT_24,v[20]);
        contentValues.put(COL_BT_25,v[21]);
        contentValues.put(COL_BT_26,v[22]);
        contentValues.put(COL_BT_27,v[23]);
        contentValues.put(COL_BT_28,v[24]);
        contentValues.put(COL_BT_29,v[25]);
        contentValues.put(COL_BT_30,v[26]);
        contentValues.put(COL_BT_31,v[27]);
        db.update(TABLE_NAME_BT,contentValues,"ID = ?",new String[] { Integer.toString(id) });
        return true;
    }
    public Integer deleteBTData(int id){
        SQLiteDatabase db = this.getWritableDatabase();
        return db.delete(TABLE_NAME_BT,"ID = ?",new String[] {Integer.toString(id)});
    }
    public Cursor get30AvgBssData() {
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor res = null;
        try {
            res = db.rawQuery("select avg(Value_of_blood_sugar) from " + TABLE_NAME_BSS + " WHERE Date >= DATE('now', '-30 days')", null);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            return res;
        }
    }
    public Cursor get14AvgBssData() {
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor res = null;
        try {
            res = db.rawQuery("select avg(Value_of_blood_sugar) from " + TABLE_NAME_BSS + " WHERE Date >= DATE('now', '-14 days')", null);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            return res;
        }
    }
}