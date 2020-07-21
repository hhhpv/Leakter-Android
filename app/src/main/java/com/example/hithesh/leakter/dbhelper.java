package com.example.hithesh.leakter;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import static android.content.ContentValues.TAG;

/**
 * Created by Hithesh on 13-02-2018.
 */

public class dbhelper extends SQLiteOpenHelper {
    public static final String TAG=dbhelper.class.getSimpleName();
    public static final String DB_NAME="myapp.db";
    public static final int DB_VERSION=2;

    public static final String USER_TABLE="users";
    public static final String COLUMN_ID="_id";
    public static final String COLUMN_NAME="name";
    public static final String COLUMN_EMAIL="email";
    public static final String COLUMN_PASS="password";
    public static final String COLUMN_CNUM="consNum";
    public static final String COLUMN_VENDOR="vendor";
    public static final String COLUMN_VNUM="vendNum";
    public static final String COLUMN_PHONE="phone";
    public static final String COLUMN_N1="n1";
    public static final String COLUMN_N2="n2";

    //public static final String CREATE_TABLE_USERS="CREATE TABLE "+USER_TABLE+" ("+COLUMN_ID+" INTEGER PRIMARY KEY AUTOINCREMENT,"+COLUMN_NAME+" TEXT,"+COLUMN_EMAIL+" TEXT,"+COLUMN_PASS+"TEXT,"+COLUMN_CNUM+" TEXT,"+COLUMN_VENDOR+" TEXT,"+COLUMN_VNUM+" TEXT,"+COLUMN_PHONE+" TEXT,"+COLUMN_N1+" TEXT,"+COLUMN_N2+" TEXT);";
//+COLUMN_ID+"INTEGER PRIMARY KEY AUTOINCREMENT,"
    public dbhelper(Context context) {
        super(context, DB_NAME, null, DB_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        final String CREATE_TABLE_USERS="CREATE TABLE "+USER_TABLE+"( "+ COLUMN_ID +" INTEGER PRIMARY KEY AUTOINCREMENT, "+ COLUMN_NAME +" TEXT, "+ COLUMN_EMAIL +" TEXT, " + COLUMN_PASS +" TEXT, "+ COLUMN_CNUM +" TEXT, "+ COLUMN_VENDOR +" TEXT, "+ COLUMN_VNUM +" TEXT, "+ COLUMN_PHONE +" TEXT, "+ COLUMN_N1 +" TEXT,"+ COLUMN_N2 +" TEXT);";
        db.execSQL(CREATE_TABLE_USERS);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS "+USER_TABLE);
        onCreate(db);
    }
    public void addUser(String name,String email,String password,String consNum,String vendor,String vendNum,String phone,String n1,String n2){
        SQLiteDatabase db=this.getWritableDatabase();
        ContentValues values=new ContentValues();
        values.put(COLUMN_NAME,name);
        values.put(COLUMN_EMAIL,email);
        values.put(COLUMN_PASS,password);
        values.put(COLUMN_CNUM,consNum);
        values.put(COLUMN_VENDOR,vendor);
        values.put(COLUMN_VNUM,vendNum);
        values.put(COLUMN_PHONE,phone);
        values.put(COLUMN_N1,n1);
        values.put(COLUMN_N2,n2);
        long id=db.insert(USER_TABLE,null,values);
        Log.d(TAG,"user inserted"+id);
    }
    public boolean getUser(String name,String email,String password){
        String selectquery="select * from "+USER_TABLE+" where "+COLUMN_NAME+" = "+"'"+name+"'"+" and "+COLUMN_EMAIL+" = "+"'"+email+"'"+" and "+COLUMN_PASS+" = "+"'"+password+"'";
        SQLiteDatabase db=this.getReadableDatabase();
        Cursor cursor=db.rawQuery(selectquery,null);
        cursor.moveToFirst();
        if(cursor.getCount()>0){
            return true;
        }
        cursor.close();
        db.close();

        return false;
    }
    public String getid(String name,String email,String password){
    String id="";
        String selectquery="select "+COLUMN_ID+" from "+USER_TABLE+" where "+COLUMN_NAME+" = "+"'"+name+"'"+" and "+COLUMN_EMAIL+" = "+"'"+email+"'"+" and "+COLUMN_PASS+" = "+"'"+password+"'";
        SQLiteDatabase db=this.getReadableDatabase();
        Cursor cursor=db.rawQuery(selectquery,null);
        if(cursor.moveToFirst())
        {
            id=cursor.getString(cursor.getColumnIndex("_id"));
            cursor.close();
            db.close();
        }
        return id;

    }
    public String gete1(String name){
        String num1="";
        //String qre1=" select n1 from "+USER_TABLE+" where "+COLUMN_NAME+" = "+"'"+name+"'"+" and "+COLUMN_EMAIL+" = "+"'"+email+"'"+" and "+COLUMN_PASS+" = "+"'"+password+"'";
        String qre1=" select n1 from "+USER_TABLE+" where "+COLUMN_ID+" = "+"'"+name+"'";
        SQLiteDatabase db=this.getReadableDatabase();
        Cursor  cursor = db.rawQuery(qre1,null);
        if (cursor.moveToFirst()) {
                String item=cursor.getString(cursor.getColumnIndex("n1"));
                num1=item;

        }
        return num1;
    }
    public String getname(String name){
        String num1="";
        //String qre1=" select n1 from "+USER_TABLE+" where "+COLUMN_NAME+" = "+"'"+name+"'"+" and "+COLUMN_EMAIL+" = "+"'"+email+"'"+" and "+COLUMN_PASS+" = "+"'"+password+"'";
        String qre1=" select "+COLUMN_NAME+" from "+USER_TABLE+" where "+COLUMN_ID+" = "+"'"+name+"'";
        SQLiteDatabase db=this.getReadableDatabase();
        Cursor  cursor = db.rawQuery(qre1,null);
        if (cursor.moveToFirst()) {
            String item=cursor.getString(cursor.getColumnIndex("name"));
            num1=item;

        }
        return num1;
    }
    public String gete2(String name){
        String num1="";
        //String qre1=" select n2 from "+USER_TABLE+" where "+COLUMN_NAME+" = "+"'"+name+"'"+" and "+COLUMN_EMAIL+" = "+"'"+email+"'"+" and "+COLUMN_PASS+" = "+"'"+password+"'";
        String qre1=" select n2 from "+USER_TABLE+" where "+COLUMN_ID+" = "+"'"+name+"'";
        SQLiteDatabase db=this.getReadableDatabase();
        Cursor  cursor = db.rawQuery(qre1,null);
        if (cursor.moveToFirst()) {
            String item=cursor.getString(cursor.getColumnIndex("n2"));
            num1=item;

        }
        return num1;
    }
    public String getv(String name){
        String num1="";
        //String qre1=" select n2 from "+USER_TABLE+" where "+COLUMN_NAME+" = "+"'"+name+"'"+" and "+COLUMN_EMAIL+" = "+"'"+email+"'"+" and "+COLUMN_PASS+" = "+"'"+password+"'";
        String qre1=" select vendNum from "+USER_TABLE+" where "+COLUMN_ID+" = "+"'"+name+"'";
        SQLiteDatabase db=this.getReadableDatabase();
        Cursor  cursor = db.rawQuery(qre1,null);
        if (cursor.moveToFirst()) {
            String item=cursor.getString(cursor.getColumnIndex("vendNum"));
            num1=item;

        }
        return num1;
    }
    public String getvn(String name){
        String num1="";
        //String qre1=" select n2 from "+USER_TABLE+" where "+COLUMN_NAME+" = "+"'"+name+"'"+" and "+COLUMN_EMAIL+" = "+"'"+email+"'"+" and "+COLUMN_PASS+" = "+"'"+password+"'";
        String qre1=" select vendor from "+USER_TABLE+" where "+COLUMN_ID+" = "+"'"+name+"'";
        SQLiteDatabase db=this.getReadableDatabase();
        Cursor  cursor = db.rawQuery(qre1,null);
        if (cursor.moveToFirst()) {
            String item=cursor.getString(cursor.getColumnIndex("vendor"));
            num1=item;

        }
        return num1;
    }
    public String getcon(String name){
        String num1="";
        //String qre1=" select n2 from "+USER_TABLE+" where "+COLUMN_NAME+" = "+"'"+name+"'"+" and "+COLUMN_EMAIL+" = "+"'"+email+"'"+" and "+COLUMN_PASS+" = "+"'"+password+"'";
        String qre1=" select consNum from "+USER_TABLE+" where "+COLUMN_ID+" = "+"'"+name+"'";
        SQLiteDatabase db=this.getReadableDatabase();
        Cursor  cursor = db.rawQuery(qre1,null);
        if (cursor.moveToFirst()) {
            String item=cursor.getString(cursor.getColumnIndex("consNum"));
            num1=item;

        }
        return num1;
    }

}
