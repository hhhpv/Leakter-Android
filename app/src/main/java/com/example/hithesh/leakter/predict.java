package com.example.hithesh.leakter;

import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.database.sqlite.SQLiteException;
import android.net.Uri;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.ScrollView;
import android.widget.TextView;

import java.util.HashMap;

import static java.security.AccessController.getContext;

public class predict extends AppCompatActivity {
    public session s;
    public dbhelper db;
    public TextView dispn;
    public Button pla;
    public Button ord;
    //public orhelper or;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_predict);
        dispn = (TextView) findViewById(R.id.dispn);
        pla=(Button) findViewById(R.id.place);
        ord=(Button)findViewById(R.id.hist);
        db = new dbhelper(this);
        s = new session(getApplicationContext());//<-- this is what you missed
        HashMap<String, String> user = s.getUserDetails();
        String name = user.get(session.n);
        String dname = "Hello, " + db.getname(name);
        dispn.setText(dname);
        pla.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent intent = new Intent(predict.this,plorder.class);
                        startActivity(intent);
                    }
                });

        ord.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {

                        Intent intent = new Intent(predict.this,order.class);
                        startActivity(intent);
                    }
                });
    }
}

