package com.example.hithesh.leakter;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.telephony.SmsManager;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Random;
import java.util.Date;
import java.util.HashMap;

public class plorder extends AppCompatActivity {
    public dbhelper db;
    session s;
    public TextView txt1,txt2;
    public static String vend,vnam;
    public static String phoneNumber;
    public static int num;
    public static Button ob;
    public orhelper or;
    public String cons;
    public String od;
    public String dd;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_plorder);
        db=new dbhelper(this);
        s = new session(getApplicationContext());//<-- this is what you missed
        HashMap<String, String> user = s.getUserDetails();
        String name = user.get(session.n);
        vend=db.getv(name);
        vnam=db.getvn(name);
        cons=db.getcon(name);
        or=new orhelper(this);
        txt1=(TextView)findViewById(R.id.dven);
        txt1.setText("VENDOR NUMBER: "+vend);
        txt2=(TextView)findViewById(R.id.dvnam);
        txt2.setText("YOUR VENDOR: "+vnam);
        ob=(Button)findViewById(R.id.obtn);
        ob.setOnClickListener(
                new View.OnClickListener(){
                    @Override
                    public void onClick(View v){

                        try {
                            Random rand = new Random();
                            num = rand.nextInt(9000000) + 1000000;
                            DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
                            Date date = new Date();
                            dd=(dateFormat.format(date)).toString();
                            phoneNumber = "+91"+vend.toString();
                            SmsManager.getDefault().sendTextMessage(phoneNumber, null,cons+" "+dd+" "+Integer.toString(num) , null, null);

                            or.addUser(cons,dd,"Not yet Delivered",Integer.toString(num));
                        } catch (Exception e){
                            Intent intent = new Intent(plorder.this,predict.class);
                            startActivity(intent);
                            //sndmsg();
                        }}});
    }
}
