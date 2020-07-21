package com.example.hithesh.leakter;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.telephony.SmsManager;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.app.Activity;
import android.app.AlertDialog;
import android.widget.TextView;
import android.widget.Toast;

import java.util.HashMap;

public class infrd extends AppCompatActivity {
    public static Button num1, al;
    public static String phoneNumber;
    public static TextView txt1, txt2, dispn;
    public String name, email, password, emer1;
    public static String mname;
    public dbhelper db;
    public static String numb1, numb2;
    session s;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_infrd);
        db = new dbhelper(this);
        s = new session(getApplicationContext());// <-- this is what you missed
        HashMap<String, String> user = s.getUserDetails();
        String name = user.get(session.n);
        numb1 = db.gete1(name);
        numb2 = db.gete2(name);
        txt1 = (TextView) findViewById(R.id.dn1);
        txt2 = (TextView) findViewById(R.id.dn2);
        dispn = (TextView) findViewById(R.id.dispn);
        txt1.setText(numb1);
        txt2.setText(numb2);
        db = new dbhelper(this);
        s = new session(getApplicationContext());// <-- this is what you missed
        HashMap<String, String> duser = s.getUserDetails();
        String dsname = duser.get(session.n);
        String dname = "Hello, " + db.getname(dsname);
        mname = db.getname(dsname);
        dispn.setText(dname);
        sndmsg();

    }

    public void sndmsg() {
        num1 = (Button) findViewById(R.id.snd1);
        al = (Button) findViewById(R.id.alert);
        num1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                try {
                    phoneNumber = "+91" + ((EditText) findViewById(R.id.num1)).getText().toString(); // Replace +91 with
                                                                                                     // the suitable
                                                                                                     // extension code
                    SmsManager.getDefault().sendTextMessage(phoneNumber, null, "LEAKTER ALERT! GAS LEAKAGE AT " + mname,
                            null, null);
                } catch (Exception e) {
                    Intent intent = new Intent(infrd.this, MainActivity.class);
                    startActivity(intent);
                    // sndmsg();
                }
            }
        });
        al.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                try {
                    phoneNumber = "+91" + numb1.toString();
                    SmsManager.getDefault().sendTextMessage(phoneNumber, null, "LEAKTER ALERT! GAS LEAKAGE AT " + mname,
                            null, null);
                    phoneNumber = "+91" + numb2.toString();
                    SmsManager.getDefault().sendTextMessage(phoneNumber, null, "LEAKTER ALERT! GAS LEAKAGE AT " + mname,
                            null, null);
                } catch (Exception e) {
                    Intent intent = new Intent(infrd.this, MainActivity.class);
                    startActivity(intent);
                    // sndmsg();
                }
            }
        });

    }

}
