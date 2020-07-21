package com.example.hithesh.leakter;

import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageInstaller;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.database.sqlite.SQLiteException;
import android.net.Uri;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import java.util.HashMap;

public class order extends AppCompatActivity {
    final private int REQUEST_CODE_ASK_PERMISSIONS = 123;
    public TextView mbd;
    public dbhelper db;
    public orhelper or;
    public String cons;
    public String cn;
    public String r=" ";
    plorder b= new plorder();
    public String vend;
    session s;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order);
        //ActiveAndroid.initialize(this);
        mbd=(TextView)findViewById(R.id.d);
        db=new dbhelper(this);
        s = new session(getApplicationContext());//<-- this is what you missed
        HashMap<String, String> user = s.getUserDetails();
        String name = user.get(session.n);
        cons=db.getcon(name);
        or=new orhelper(this);
        cn=or.getd(cons, Integer.toString(b.num));
        vend=db.getv(name);
        orHist();
    }
    public void orHist() {
        ActivityCompat.requestPermissions(order.this, new String[]{"android.permission.READ_SMS"}, REQUEST_CODE_ASK_PERMISSIONS);
        if (ContextCompat.checkSelfPermission(getBaseContext(), "android.permission.READ_SMS") == PackageManager.PERMISSION_GRANTED) {
            StringBuilder smsBuilder = new StringBuilder();
            final String SMS_URI_INBOX = "content://sms/inbox";
            final String SMS_URI_ALL = "content://sms/";
            String strbody;
            try {
                Uri uri = Uri.parse(SMS_URI_INBOX);
                String[] projection = new String[]{"_id", "address", "person", "body", "date", "type"};
                String ph="address="+"'+91"+vend+"'";
                Cursor cur = getContentResolver().query(uri, projection, ph, null, "date desc");
                if (cur.moveToFirst()) {
                    int index_Address = cur.getColumnIndex("address");
                    int index_Person = cur.getColumnIndex("person");
                    int index_Body = cur.getColumnIndex("body");
                    int index_Date = cur.getColumnIndex("date");
                    int index_Type = cur.getColumnIndex("type");
                    //do {
                    String strAddress = cur.getString(index_Address);
                    int intPerson = cur.getInt(index_Person);
                    strbody = cur.getString(index_Body);
                    long longDate = cur.getLong(index_Date);
                    int int_Type = cur.getInt(index_Type);
                    smsBuilder.append("[ ");
                    smsBuilder.append(strAddress + ", ");
                    smsBuilder.append(intPerson + ", ");
                    smsBuilder.append(strbody + ", ");
                    smsBuilder.append(longDate + ", ");
                    smsBuilder.append(int_Type);
                    smsBuilder.append(" ]\n\n");
                    //} while (cur.moveToNext());
                    //cn=or.getd(String.valueOf(cons));
                    cn=strbody;
                    mbd.setText(cn);
                    if(cn!="Not yet Delivered")
                    {
                        or.deliver(cons,cn);
                    }
                    if (!cur.isClosed()) {
                        cur.close();
                        cur = null;
                    }

                } else {
                    smsBuilder.append("no result!");
                    //mbd.setText("No result");
                } // end if
            } catch (SQLiteException ex) {
                Log.d("SQLiteException", ex.getMessage());
                //mbd.setText("No result");
            }

        }
        //String d=or.getTableAsString();
        mbd.setText(or.printTableData(cons));
    }

}
