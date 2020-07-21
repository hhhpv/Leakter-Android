package com.example.hithesh.leakter;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by Hithesh on 21-04-2018.
 */

public class orhelper extends SQLiteOpenHelper {
    public static final String TAG = orhelper.class.getSimpleName();
    public static final String DB_NAME = "ordersl.db";
    public static final int DB_VERSION = 3;
    public static final String USER_TABLE = "orders";
    public static final String COLUMN_ID = "_id";
    public static final String COLUMN_CO = "con";
    public static final String COLUMN_OD = "oD";
    public static final String COLUMN_DD = "dD";
    public static final String COLUMN_REF = "ref";

    public orhelper(Context context) {
        super(context, DB_NAME, null, DB_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        final String CREATE_TABLE_USERS = "CREATE TABLE " + USER_TABLE + "(" + COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " + COLUMN_CO + " TEXT, " + COLUMN_OD + " TEXT, " + COLUMN_DD + " TEXT, " + COLUMN_REF + " TEXT);";
        db.execSQL(CREATE_TABLE_USERS);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + USER_TABLE);
        onCreate(db);

    }

    public void addUser(String co, String o, String d, String r) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(COLUMN_CO, co);
        values.put(COLUMN_OD, o);
        values.put(COLUMN_DD, d);
        values.put(COLUMN_REF, r);
        long id = db.insert(USER_TABLE, null, values);
        Log.d(TAG, "user inserted" + id);
    }

    public String getd(String name, String r) {
        String num1 = "";
        String qre1 = "select dD from " + USER_TABLE + " where " + COLUMN_CO + " = " + "'" + name + "'" + " and " + COLUMN_REF + " = " + "'" + r + "'";
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(qre1, null);
        if (cursor.moveToFirst()) {
            String item = cursor.getString(cursor.getColumnIndex("dD"));
            num1 = item;

        }
        return num1;
    }

    public void deliver(String c, String ref) {
        String num1 = "";
        DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
        Date date = new Date();
        String dd = (dateFormat.format(date)).toString();
        String qre1 = "update " + USER_TABLE + " set " + COLUMN_DD + " = " + "'" + dd + "'" + " where " + COLUMN_CO + " = " + "'" + c + "'" + " and " + COLUMN_REF + " = " + "'" + ref + "';";
        SQLiteDatabase db = this.getReadableDatabase();
        db.execSQL(qre1);
    }

    public String fetch(String c) {
        String num1=" ";
        String qre1 = "select * from " + USER_TABLE + " where " + COLUMN_CO + " = " + "'" + c + "'";
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(qre1, null);

        return num1;
    }


    public String printTableData(String c) {
        SQLiteDatabase db = getReadableDatabase();

        Cursor cur = db.rawQuery("SELECT * FROM " +USER_TABLE + " where " + COLUMN_CO + " = " + "'" + c + "'" , null);
        String it=" ";
        if (cur.getCount() != 0) {
            cur.moveToFirst();

            do {
                String row_values = "";

                for (int i = 1; i < cur.getColumnCount(); i++) {
                    row_values = row_values + " || \t\t\t" + cur.getString(i);
                }
                it=it+row_values+"\n\n";
                Log.d("LOG_TAG_HERE", row_values);

            } while (cur.moveToNext());
        }
        return it;
    }
}
